package com.example.city.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.city.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

 ////// Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        ///////////////// Go To Login Page When Click On Login Button ////////////////
        binding.buttonLoginRA.setOnClickListener{
            startActivity(Intent(this@RegisterActivity , LoginActivity::class.java))
            finish()
        }


        binding.buttonRegisterRA.setOnClickListener{
            UserRegister()                   // Here we call UserRegister function
        }

    }

    ////////////////////////////////.................... FUNCTION ....................... /////////////////////////////

    private fun UserRegister(){

        //// get we get text from edt text box
        val userEmail : String = binding.userEmailRA.text.toString()
        val userName : String = binding.userNameRA.text.toString()
        val userPassword : String = binding.userPasswordRA.text.toString()
        val userRepeatPassword : String = binding.userRePasswordRA.text.toString()

        // here we check all box fill
        if (userEmail.isEmpty() || userName.isEmpty() || userPassword.isEmpty() || userRepeatPassword.isEmpty() ){
            Toast.makeText(this, "Please Fill All Details", Toast.LENGTH_SHORT).show()
        }else if (userPassword != userRepeatPassword){
            Toast.makeText(this, "Repeat Password Must be same ", Toast.LENGTH_SHORT).show()

        }else{
            auth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(this){task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                    else {
                        Toast.makeText(this, "Registration Faild : ${task.exception} ",Toast.LENGTH_SHORT).show()
                    }

                }
        }

    }
}