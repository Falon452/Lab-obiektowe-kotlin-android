package com.example.lab_obiektowe_kotlin_android

import agh.ics.oop.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


import android.app.Activity
import android.os.CountDownTimer
import android.os.Handler
import java.lang.ref.WeakReference



class MainActivity : AppCompatActivity(), View.OnClickListener {
//    lateinit var currentView: RectangularView
    lateinit var currentView: GrassFieldView
    lateinit var edtText: EditText
    val parser: OptionsParser = OptionsParser()
    val refreshTime = 30.toLong()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnForward = findViewById<Button>(R.id.btnForward)
        val btnRight= findViewById<Button>(R.id.btnRight)
        val btnBackward = findViewById<Button>(R.id.btnBackward)
        val btnLeft = findViewById<Button>(R.id.btnLeft)
        edtText = findViewById<EditText>(R.id.edtTextMoves)
        val btnStart = findViewById<Button>(R.id.btnStart)

        btnForward.setOnClickListener(this)
        btnRight.setOnClickListener(this)
        btnBackward.setOnClickListener(this)
        btnLeft.setOnClickListener(this)
        btnStart.setOnClickListener(this)


//        currentView = findViewById(R.id.RectangleView)
        currentView = findViewById(R.id.GrassFieldView)
    }


    override fun onClick(v: View) {
        when (v.id){
            R.id.btnForward -> currentView.moveAnimalAndRefresh(MoveDirection.FORWARD)
            R.id.btnRight -> currentView.moveAnimalAndRefresh(MoveDirection.RIGHT)
            R.id.btnBackward -> currentView.moveAnimalAndRefresh(MoveDirection.BACKWARD)
            R.id.btnLeft -> currentView.moveAnimalAndRefresh(MoveDirection.LEFT)
            R.id.btnStart -> {
                val directions = parser.parse(edtText.text.split(" "))
                var i = 0
                object : CountDownTimer(directions.size*refreshTime, refreshTime) {
                    override fun onTick(millisUntilFinished: Long) {
                        currentView.moveAnimalAndRefresh(directions[i])
                        i += 1
                    }
                    override fun onFinish(){}
                }.start()
                }
            }
        }
    }
