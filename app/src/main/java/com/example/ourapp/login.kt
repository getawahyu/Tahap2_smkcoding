package com.example.ourapp

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build.MODEL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.ourapp.login
import com.example.ourapp.Register
import com.example.ourapp.util.tampilToast
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.widget.MessageDialog
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.et_email
import kotlinx.android.synthetic.main.activity_login.et_password
import kotlinx.android.synthetic.main.activity_login.register
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class login : AppCompatActivity(), View.OnClickListener {

    private var auth: FirebaseAuth? = null
    private val RC_SIGN_IN = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progress.visibility = View.GONE

        register.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
            finish()
        }

        login.setOnClickListener{
            loginWithEmail() }

        imageView.setOnClickListener {
            loginWithGoogle()
        }

        auth = FirebaseAuth.getInstance()

        if (auth!!.currentUser == null) {
        } else {
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginWithGoogle(){
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(listOf(AuthUI.IdpConfig.GoogleBuilder().build()))
                .setIsSmartLockEnabled(false)
                .build(),
            RC_SIGN_IN)
        progress.visibility = View.VISIBLE
    }

    private fun loginWithEmail(){
        var email = et_email.text.toString()
        var pass = et_password.text.toString()

        if (!email.isEmpty() && !pass.isEmpty()){
            progress.visibility = View.VISIBLE
            auth?.signInWithEmailAndPassword(email, pass)!!.addOnCompleteListener(this) {
                if (it.isSuccessful){
                    startActivity(Intent(this@login, MainActivity::class.java))
                    finish()
                }else{
                    tampilToast(this@login, "Login gagal" +it.exception.toString())
                }
            }
        }else{
            tampilToast(this, "masukkan email/password")
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ){
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else { //Jika gagal login
                progress.visibility = View.GONE
                Toast.makeText(this, "Login Dibatalkan", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onClick(v: View?) {
        startActivityForResult(
            AuthUI.getInstance ()
                .createSignInIntentBuilder()
                .setAvailableProviders(listOf(AuthUI.IdpConfig.GoogleBuilder().build()))
                .setIsSmartLockEnabled(false)
                .build(),
            RC_SIGN_IN
        )

        progress.visibility = View.VISIBLE
    }

}