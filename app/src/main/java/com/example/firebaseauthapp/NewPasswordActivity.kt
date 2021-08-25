package com.example.firebaseauthapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

import android.widget.EditText
import android.widget.Toast


class NewPasswordActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var btnNewPass: Button
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_password)

        firebaseAuth = FirebaseAuth.getInstance()
        email = findViewById(R.id.etYourEmail)
        btnNewPass = findViewById(R.id.btnNewPassword)

        btnNewPass.setOnClickListener {
            val myEmail = email.text.toString()
            if (myEmail.isEmpty()){
                Toast.makeText(
                    applicationContext,
                    "Please Enter your Email",
                    Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            firebaseAuth.sendPasswordResetEmail(myEmail).addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(
                        applicationContext,
                        "Password reset link was sent your email address",
                        Toast.LENGTH_SHORT)
                        .show()
                }else{
                    Toast.makeText(
                        applicationContext,
                        "Mail sending error",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}