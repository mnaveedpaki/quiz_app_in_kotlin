package com.example.quizappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    var edtName : TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        edtName = findViewById(R.id.edtname)


    }

    fun onEnterName(view: View) {
        if (edtName?.text.isNullOrBlank()){
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show()
        }
        else{
            val intent = Intent(this, QuiazQuestionsActivity::class.java)
            intent.putExtra(Constants.USER_NAME, edtName?.text.toString())
            startActivity(intent)
            finish()
        }
    }
}