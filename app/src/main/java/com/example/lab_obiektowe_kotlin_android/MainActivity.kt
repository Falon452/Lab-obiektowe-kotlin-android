package com.example.lab_obiektowe_kotlin_android

import agh.ics.oop.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast


//private lateinit var customDrawableView: CustomDrawableView

class MainActivity : AppCompatActivity(), View.OnClickListener {
//    lateinit var currentView: RectangularView
    lateinit var currentView: GrassFieldView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnForward = findViewById<Button>(R.id.btnForward)
        val btnRight= findViewById<Button>(R.id.btnRight)
        val btnBackward = findViewById<Button>(R.id.btnBackward)
        val btnLeft = findViewById<Button>(R.id.btnLeft)

        btnForward.setOnClickListener(this)
        btnRight.setOnClickListener(this)
        btnBackward.setOnClickListener(this)
        btnLeft.setOnClickListener(this)

//        currentView = findViewById(R.id.RectangleView)
        currentView = findViewById(R.id.GrassFieldView)

    }


    override fun onClick(v: View) {
        when (v.id){
            R.id.btnForward -> currentView.moveAnimalAndRefresh(MoveDirection.FORWARD)
            R.id.btnRight -> currentView.moveAnimalAndRefresh(MoveDirection.RIGHT)
            R.id.btnBackward -> currentView.moveAnimalAndRefresh(MoveDirection.BACKWARD)
            R.id.btnLeft -> currentView.moveAnimalAndRefresh(MoveDirection.LEFT)
        }
    }
}

