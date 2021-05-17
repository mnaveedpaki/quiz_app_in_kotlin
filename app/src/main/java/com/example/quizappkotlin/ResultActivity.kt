package com.example.quizappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    var tvUsername : TextView? = null
    var tvScore : TextView? = null
    var mUserName : String? = null
    var mTotalAnswers : String? = null
    var mCorrectAnswers : String? = null
    var btnFinish : Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        tvUsername = findViewById(R.id.tv_username)
        tvScore = findViewById(R.id.tv_score)
        btnFinish = findViewById(R.id.btn_finish)

        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mTotalAnswers = intent.getStringExtra(Constants.TOTAL_ANSWERS)
        mCorrectAnswers = intent.getStringExtra(Constants.CORRECT_ANSWERS)

        tvUsername?.text = mUserName
        tvScore?.text = "Your score is $mCorrectAnswers out of $mTotalAnswers"

        btnFinish?.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}