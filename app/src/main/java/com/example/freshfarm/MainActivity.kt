package com.example.freshfarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.freshfarm.choose.ChooseActivity
import com.example.freshfarm.user.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.naver.maps.map.NaverMapSdk
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

    }

    override fun onStart() {
        super.onStart()

//        check auth
        if (auth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else if (!auth.currentUser!!.isEmailVerified) {
            val user = Firebase.auth.currentUser
            user!!.sendEmailVerification()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("suc", "Email sent.")
                    }
                }
            startLoginActivity()
        }
//        if list is empty, start choose Item Activity
//        TODO("need to set condition when force move to choose activity")
        startChooseActivity()
    }

    private fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun startChooseActivity() {
        startActivity(Intent(this, ChooseActivity::class.java))
    }

//    start activity

}
