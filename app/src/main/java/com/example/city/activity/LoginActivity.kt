package com.example.city.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.city.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var auth :FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ///////////////////// Gor To Register Page When Click On Register Button ////////////////////////////
        binding.buttonRegisterLA.setOnClickListener{
            val goToRegister = Intent(this@LoginActivity , RegisterActivity::class.java )
            startActivity(goToRegister)
            finish()
        }


        ///////// Initialize firebase Auth
        auth = FirebaseAuth.getInstance()



        /////////// sign in firebase//////////////
        binding.buttonLoginLA.setOnClickListener {
            UserLogin()

        }



    }
//////////////////////////////.......................FUNCTION......................//////////////////////////////////////////

    ///// if user is already register then direct go to home page
//    override fun onStart() {
//        super.onStart()
//        val currentUser : FirebaseUser? = auth.currentUser
//        if (currentUser != null) {
//            startActivity(Intent(this,MainActivity::class.java))
//            finish()
//        }
//    }

    ////////////// Create Function For User Login ////////////////
    private fun UserLogin(){
        val userName = binding.userNameLA.text.toString()
        val userPassword = binding.userPasswordLA.text.toString()

        if (userName.isEmpty() || userPassword.isEmpty()){
            Toast.makeText(this, "Please Fill All Details", Toast.LENGTH_SHORT).show()
        }
        else{
            auth.signInWithEmailAndPassword(userName,userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Successfully Login", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }else {
                        Toast.makeText(this, "Login Faild ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }


    }
}