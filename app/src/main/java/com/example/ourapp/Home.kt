package com.example.ourapp

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ourapp.adapter.DataAdapter
import com.example.ourapp.viewmodel.FragmentViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_recyclerview.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.AccessController.getContext

class Home : Fragment(), DataAdapter.dataListener {

    lateinit var ref: DatabaseReference
    lateinit var auth: FirebaseAuth
    var dataData: MutableList<MyModel> = ArrayList()
    private val viewModel by viewModels<FragmentViewModel>()
    private var adapter: DataAdapter? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getData()
        viewModel.init(requireContext())
        viewModel.allMyFriends.observe(viewLifecycleOwner, Observer { myFriends ->
            myFriends?.let { adapter?.setData(it) }
        })
        btadd.setOnClickListener {
            val intent = Intent(activity, NewActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    private fun init() {
        listDeath.layoutManager = LinearLayoutManager(context)
        adapter = DataAdapter(requireContext(), dataData)
        listDeath.adapter = adapter
        adapter?.listener = this
    }

    private fun getData() {
        //Mendapatkan Referensi Database
        Toast.makeText(getContext(), "Mohon Tunggu Sebentar...", Toast.LENGTH_LONG).show()
        auth = FirebaseAuth.getInstance()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
        ref = FirebaseDatabase.getInstance().getReference()
//        ref.child(getUserID).addValueEventListener(object : ValueEventListener {
        ref.child("Data").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(getContext(), "Database Error yaa...", Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataData = ArrayList()
                for (snapshot in dataSnapshot.children) {
                    val data = snapshot.getValue(MyModel::class.java)
                    data?.key = (snapshot.key!!)
                    dataData.add(data!!)
                }
                viewModel.insertAll(dataData)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

    override fun onDeleteData(data: MyModel, position: Int) {
        auth = FirebaseAuth.getInstance()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
        if (ref != null) {
            ref.child("Data")
                .child(data?.key!!.toString())
                .removeValue()
                .addOnSuccessListener {
                    Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                    viewModel.delete(data)
                }
        } else {
            Toast.makeText(context, data!!.key!!.toString(), Toast.LENGTH_LONG).show()
        }
    }

//    private fun callApiGetListDeath(){
//        showLoading(context!!,swipeRefreshLayouthome)
//
//        val httpClient = httpClient()
//        val apiRequest = apiRequest<DeathService>(httpClient)
//
//        val call = apiRequest.getDataDeats()
//        call.enqueue(object : Callback<List<DataDeathsItem>> {
//
//            override fun onFailure(call: Call<List<DataDeathsItem>>, t: Throwable) {
//                dismissLoading(swipeRefreshLayouthome)
//            }
//
//            override fun onResponse(
//                call: Call<List<DataDeathsItem>>,
//                response: Response<List<DataDeathsItem>>
//            ) {
//                dismissLoading(swipeRefreshLayouthome)
//                when {
//                    response.isSuccessful ->
//                        when {
//                            response.body()?.size != 0 -> tampilListDeath(response.body()!!)
//                            else -> {
//                                tampilToast(context!!, "Berhasil")
//                            }
//                        }
//                    else -> {
//                        tampilToast(context!!, "Gagal")
//                    }
//                }
//            }
//        })
//    }

//    private fun tampilListDeath(githubUsers: List<DataDeathsItem>) {
//        listDeath.layoutManager = LinearLayoutManager(context)
//        listDeath.adapter = DeathAdapter(context!!, githubUsers) {
//
//            val githubUser = it
//            tampilToast(context!!, githubUser.countryRegion)
//    }

//    override fun onDestroy() {
//        super.onDestroy()
//        this.clearFindViewByIdCache()
//    }

}

