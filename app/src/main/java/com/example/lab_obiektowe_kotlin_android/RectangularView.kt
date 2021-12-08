package com.example.lab_obiektowe_kotlin_android

import agh.ics.oop.*
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class RectangularView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val WIDTH = 10
    val HEIGHT = 10
    val map = RectangularMap(WIDTH, HEIGHT)
    val initialPositions = listOf(
        Vector2d(3, 1),
        Vector2d(0, 2),
        Vector2d(2, 0)
    )
    init {
        placeInitialPositions(this.initialPositions)
    }
    val animals = map.animals()
    private val numberOfAnimals = this.initialPositions.size
    private var animalIndex = 0
    val myPaintAnimal = Paint()
    val myPaintOther = Paint()

    override fun onDraw(canvas: Canvas?) {
        myPaintAnimal.setColor(Color.BLACK)
        myPaintOther.setColor(Color.BLACK)
        val canvasWidth = canvas?.width
        val canvasHeight = canvas?.height
        var x1 = 0
        var y1 = 0
        var x2 = 0
        var y2 = 0
        val xRatio = canvasWidth?.div(WIDTH)
        val yRatio = canvasHeight?.div(HEIGHT)
        myPaintAnimal.textSize = 80.0F
        myPaintAnimal.isElegantTextHeight = true


        for (x in 0..WIDTH){
            for (y in 0..HEIGHT){
                x1 = x* xRatio!!
                x2 = (x+1)*xRatio
                y1 = y*yRatio!!
                y2 = (y+1)*yRatio

                if ((map.objectAt(position=Vector2d(x, y))) is Animal ){
                    val myAnimal: Animal = map.objectAt(position=Vector2d(x, y)) as Animal
                    when (myAnimal.orientation){
                        MapDirection.NORTH -> canvas?.drawText("^",
                            (x1 + xRatio/2.8).toFloat(), (y1 + yRatio/1.1).toFloat(), myPaintAnimal)
                        MapDirection.EAST -> canvas?.drawText(">",
                            (x1 + xRatio/2.8).toFloat(), (y1 + yRatio/1.1).toFloat(), myPaintAnimal)
                        MapDirection.SOUTH -> canvas?.drawText("v",
                            (x1 + xRatio/2.8).toFloat(), (y1 + yRatio/1.1).toFloat(), myPaintAnimal)
                        MapDirection.WEST -> canvas?.drawText("<",
                            (x1 + xRatio/2.8).toFloat(), (y1 + yRatio/1.1).toFloat(), myPaintAnimal)
                    }
                } else
                    canvas?.drawRect(Rect(x1, y1, x2, y2), myPaintOther)
            }
        }
    }
    fun moveAnimalAndRefresh(direction: MoveDirection){
        moveAnimal(direction)
        postInvalidate()
    }
    private fun moveAnimal(direction: MoveDirection){
        if (animalIndex == numberOfAnimals)
            animalIndex = 0
        animals[animalIndex].move(direction)
        animalIndex += 1
    }
    private fun placeInitialPositions(initialPositions: List<Vector2d>){
        initialPositions.forEach { position ->
            val succeeded: Boolean = map.place(Animal(map, position))
            if (!succeeded) throw IllegalArgumentException("Can't place Animal at $position")
        }
    }
}