package com.example.quizappkotlin

import com.example.quizappkotlin.models.Question
import kotlin.random.Random

object Constants {


    const val USER_NAME : String = "user_name"
    const val TOTAL_ANSWERS : String = "total_answers"
    const val CORRECT_ANSWERS : String = "correct_answers"
    const val TOTAL_RANDOM_QUESTION_LIMIT = 4


    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()
        val randomQuestionsList = ArrayList<Question>()
        var ran = Random.nextInt(0, 3)


        val que1 = Question(
            1,
            " what country does this flag belong to?",
            R.drawable.pakistan,
            "Pakistan",
            "India",
            "China",
            "Sri Lanka",
            1
        )
        questionsList.add(que1)

        val que2 = Question(
            2,
            "what country does this flag belong to?",
            R.drawable.india,
            "Pakistan",
            "India",
            "China",
            "Sri Lanka",
            2
        )
        questionsList.add(que2)

        val que3 = Question(
            3,
            " what country does this flag belong to?",
            R.drawable.china,
            "Pakistan",
            "India",
            "China",
            "Sri Lanka",
            3
        )
        questionsList.add(que3)


        val que4 = Question(
            4,
            " what country does this flag belong to?",
            R.drawable.pakistan,
            "Pakistan",
            "India",
            "China",
            "Sri Lanka",
            1
        )
        questionsList.add(que4)

        val que5 = Question(
            5,
            "what country does this flag belong to?",
            R.drawable.india,
            "Pakistan",
            "India",
            "China",
            "Sri Lanka",
            2
        )
        questionsList.add(que5)

        val que6 = Question(
            6,
            " what country does this flag belong to?",
            R.drawable.china,
            "Pakistan",
            "India",
            "China",
            "Sri Lanka",
            3
        )
        questionsList.add(que6)

        while(randomQuestionsList.size < TOTAL_RANDOM_QUESTION_LIMIT){
            randomQuestionsList.add(questionsList.random())
        }


        return randomQuestionsList
    }
}