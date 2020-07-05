package com.example.ourapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NewUpdate : AppCompatActivity() {

    private var namaBaru: EditText? = null
    private var telpBaru: EditText? = null
    private var alamatBaru: EditText? = null
    private var riwayatBaru: EditText? = null
    lateinit var update: Button
    private var database: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    private var cekNama: String? = null
    private var cekRiwayat: String? = null
    private var cekTelp: String? = null
    private var cekAlamat: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_update)

        getSupportActionBar()?.setTitle("Update Data")
        namaBaru = findViewById(R.id.new_nama)
        riwayatBaru = findViewById(R.id.new_riwayat)
        telpBaru = findViewById(R.id.new_telp)
        alamatBaru = findViewById(R.id.new_alamat)
        update = findViewById(R.id.update)

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference()

        getData()

        update.setOnClickListener {
            cekNama = namaBaru?.getText().toString()
            cekTelp = telpBaru?.getText().toString()
            cekAlamat = alamatBaru?.getText().toString()
            cekRiwayat = riwayatBaru?.getText().toString()

            if (isEmpty(cekNama) || isEmpty(cekTelp) || isEmpty(cekAlamat) || isEmpty(cekRiwayat)) {

                Toast.makeText(this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
            } else {

                val temanBaru = MyModel(cekNama!!, cekTelp!!, cekAlamat!!, cekRiwayat!!, "")

                val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
                val getKey: String = getIntent().getStringExtra("getPrimaryKey").toString()
                database!!.child("Data")
                    .child(getKey).setValue(temanBaru)
                    .addOnCompleteListener {
                        Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
                        finish();

                    }
            }
        }

    }

    private fun getData() {
        //Menampilkan data dari item yang dipilih sebelumnya
        val getNama: String  = intent.getStringExtra("dataNama").toString()
        val getTelp: String  = intent.extras?.getString("dataTelp").toString()
        val getAlamat: String  = intent.extras?.getString("dataAlamat").toString()
        val getRiwayat: String  = intent.extras?.getString("dataRiwayat").toString()
        namaBaru?.setText(getNama);
        telpBaru?.setText(getTelp);
        alamatBaru?.setText(getAlamat);
        riwayatBaru?.setText(getRiwayat);
        Toast.makeText(this, getNama, Toast.LENGTH_SHORT).show()

    }

}