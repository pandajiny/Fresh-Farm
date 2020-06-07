package com.example.freshfarm.user.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.freshfarm.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.toast

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = Firebase.auth

        signupButton.setOnClickListener {
            doSignUp()
        }
    }

    private fun doSignUp() {

        val email = signupEmailText.text.toString()
        val password = signupPasswordText.text.toString()

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
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("AUTH FAIL", "createUserWithEmail:success")
                val user = auth.currentUser
                finish()
//                updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Log.w("AUTH FAIL", "createUserWithEmail:failure", task.exception)
                toast("Authentication failed.")
//                updateUI(null)
            }
        }

    }


}
