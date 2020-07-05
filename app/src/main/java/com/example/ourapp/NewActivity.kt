package com.example.ourapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_new.*

class NewActivity : AppCompatActivity() {

//    private var Nama: EditText? = null
//    private var Email: EditText? = null
//    private var Telp: EditText? = null
//    private var Alamat: EditText? = null
    lateinit var ref: DatabaseReference
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
//
//        Nama = findViewById<EditText>(R.id.nama)
//        Email = findViewById<EditText>(R.id.email)
//        Telp = findViewById<EditText>(R.id.telp)
//        Alamat = findViewById<EditText>(R.id.alamat)
        ref = FirebaseDatabase.getInstance().getReference()
        auth = FirebaseAuth.getInstance()

        simpan.setOnClickListener {
            prosesSave()
        }
    }

    private fun prosesSave() {
        val getNama: String = nama?.text.toString()
        val getTelp: String = telp?.text.toString()
        val getAlamat: String = alamat?.text.toString()
        val getRiwayat: String = riwayat?.text.toString()
        val getUserID: String = auth?.currentUser?.uid.toString()

        if (getNama.isEmpty() && getRiwayat.isEmpty() && getTelp.isEmpty() && getAlamat.isEmpty()) {
            //Jika kosong, maka akan menampilkan pesan singkat berikut ini.
            Toast.makeText(this@NewActivity, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT)
                .show()
        } else {
            val teman = MyModel(getNama, getTelp, getAlamat,getRiwayat, getUserID)
//struktur databasenya adalah: ID >> Teman >> Key >> Data
//            ref.child(getUserID).push().setValue(teman).addOnCompleteListener {
            ref.child("Data").push().setValue(teman).addOnCompleteListener {
                Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
            }
//            val intent = Intent(this, Home::class.java)
//            startActivity(intent)
            finish()
        }
    }
}