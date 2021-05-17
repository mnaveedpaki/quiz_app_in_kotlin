package com.example.quizappkotlin

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.quizappkotlin.models.Question

class QuiazQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    var tvQuestion : TextView? = null
    var tvProgress : TextView? = null
    var tvOptionOne : TextView? = null
    var tvOptionTwo : TextView? = null
    var tvOptionThree : TextView? = null
    var tvOptionFour : TextView? = null
    var btnSubmit : Button? = null
    var image : ImageView? = null
    var progressbar : ProgressBar? = null
    var mCorrectAnswers : Int = 0

    private var mCurrentPosition : Int = 1
    private var mQuestionsList : ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    private var mUserName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiaz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        tvQuestion = findViewById(R.id.tv_question)
        tvProgress = findViewById(R.id.tv_progress)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)
        image = findViewById(R.id.iv_image)
        progressbar = findViewById(R.id.progressbar)

        mQuestionsList = Constants.getQuestions()

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        setQuestion()
    }

    private fun setQuestion(){
        val question = mQuestionsList!!.get(mCurrentPosition - 1)

        defaultOptionsView()
        enableOptionSelect()

        if (mCurrentPosition > mQuestionsList?.size!!){
            btnSubmit?.text = "Finish"
            return
        }else{
            btnSubmit?.text = "SUBMIT"
        }


        progressbar?.progress = mCurrentPosition
        progressbar?.max = mQuestionsList!!.size
        tvProgress?.text = "$mCurrentPosition"+"/"+"${progressbar?.max}"
        tvQuestion?.text = question!!.question
        image?.setImageResource(question!!.image)

        tvOptionOne?.text = question!!.optionOne
        tvOptionTwo?.text = question!!.optionTwo
        tvOptionThree?.text = question!!.optionThree
        tvOptionFour?.text = question!!.optionFour
    }



    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        tvOptionOne?.let { options.add(0, it) }
        tvOptionTwo?.let { options.add(1, it) }
        tvOptionThree?.let { options.add(2, it) }
        tvOptionFour?.let { options.add(3, it) }

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_bg
            )
        }
    }





    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.tv_option_one -> {
                tvOptionOne?.let { selectedOptionView(it, 1) }
//                selectedOptionView(tv_option_one, 1)
            }

            R.id.tv_option_two -> {
                tvOptionTwo?.let { selectedOptionView(it, 2) }
//                selectedOptionView(tv_option_two, 2)
            }

            R.id.tv_option_three -> {
                tvOptionThree?.let { selectedOptionView(it, 3) }
//                selectedOptionView(tv_option_three, 3)
            }

            R.id.tv_option_four -> {
                tvOptionFour?.let { selectedOptionView(it, 4) }
//                selectedOptionView(tv_option_four, 4)
            }

            R.id.btn_submit -> {

                if (mSelectedOptionPosition == 0) {

                    mCurrentPosition++

                    when {

                        mCurrentPosition <= mQuestionsList!!.size -> {

                            setQuestion()
                        }
                        else -> {

                            // TODO (STEP 5: Now remove the toast message and launch the result screen which we have created and also pass the user name and score details to it.)
                            // START
                            val intent =
                                Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers.toString())
                            intent.putExtra(Constants.TOTAL_ANSWERS, mQuestionsList!!.size.toString())
                            startActivity(intent)
                            finish()
                            // END
                        }
                    }
                } else {
                    disableOptionSelect()
                    val question = mQuestionsList?.get(mCurrentPosition - 1)

                    // This is to check if the answer is wrong
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_bg)
                    }
                    else {
                        mCorrectAnswers++
                    }

                    // This is for correct answer
                    answerView(question.correctAnswer, R.drawable.correct_option_bg)

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        btnSubmit?.text = "FINISH"
                    } else {
                        btnSubmit?.text = "GO TO NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer : Int, drawableView : Int){
        when(answer){
            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }

    private fun selectedOptionView(tv : TextView, selectedOptionsNum : Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionsNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.seleted_option_bg
        )

    }


    private fun disableOptionSelect(){
        tvOptionOne?.isClickable = false;
        tvOptionTwo?.isClickable = false;
        tvOptionThree?.isClickable = false;
        tvOptionFour?.isClickable = false;
    }
    private fun enableOptionSelect(){
        tvOptionOne?.isClickable = true;
        tvOptionTwo?.isClickable = true;
        tvOptionThree?.isClickable = true;
        tvOptionFour?.isClickable = true;
    }
}


