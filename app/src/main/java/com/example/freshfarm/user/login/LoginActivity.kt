package com.example.freshfarm.user.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.freshfarm.R
import com.example.freshfarm.user.signup.SignUpActivity
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var auth: FirebaseAuth

    private val RC_DEFAULT_SIGN_UP = 8000
    private val RC_GOOGLE_SIGN_IN = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        loginButton.setOnClickListener {
            doLogin()
        }

        loginSignUpTextButton.setOnClickListener {
            startSignUpActivity(RC_DEFAULT_SIGN_UP)
        }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            finish()
        }
    }

    private fun doLogin() {
        val email = loginEmailText.text.toString()
        val password = loginPasswordText.text.toString()

//        email form validation
        if (email.isEmpty()) {
            toast("Please check e-mail form")
            return
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            toast("Please check e-mail form")
            return
        }

//        password validation
//        TODO("please assign password rule")


//        TODO("Please implement with firebase")
        Log.w("request login: $email", "with $password")
    }

    //    start activity functions
    private fun startSignUpActivity(REQUEST_CODE: Int) {
        if (REQUEST_CODE == RC_DEFAULT_SIGN_UP) {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
