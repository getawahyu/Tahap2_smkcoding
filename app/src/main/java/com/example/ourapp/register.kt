package com.example.ourapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ourapp.util.tampilToast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    private var mDatabase: FirebaseDatabase? = null
    private var auth: FirebaseAuth? = null
    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        progressBar.visibility = View.GONE

        auth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()
        ref = mDatabase!!.reference.child("users")

        register.setOnClickListener {
            register()
        }
    }

    private fun register() {
        progressBar.visibility = View.VISIBLE
        var username = et_username.text.toString()
        var email = et_email.text.toString()
        var password = et_password.text.toString()

        if (!email.isEmpty() && !password.isEmpty() && !username.isEmpty()) {
            auth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        progressBar.visibility = View.GONE
                        val user = auth?.currentUser
                        val uid = user!!.uid
                        val userData = UserModel(username, email, "", uid)
                        ref.child(uid).push().setValue(userData)
                        startActivity(Intent(this@Register, MainActivity::class.java))
                        finish()
                        tampilToast(
                            this,
                            "Register sukses"
                        )
                    } else {
                        progressBar.visibility = View.GONE
                        tampilToast(
                            this,
                            "Register gagal "+task.exception.toString()
                        )
                    }
                })
        } else {
            tampilToast(
                this,
                "masukkan email/password/nama"
            )
        }
    }
}