package com.example.lab_obiektowe_kotlin_android


import agh.ics.oop.*
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.lang.Math.abs

class GrassFieldView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    val map = GrassField(60)
    val initialPositions = listOf(
        Vector2d(3, 1),
        Vector2d(0, 2),
        Vector2d(2, 0)
    )

    init {
        placeInitialPositions(this.initialPositions)
        map.addNGrasses()
    }

    val animals = map.animals()


    private val numberOfAnimals = this.initialPositions.size
    private var animalIndex = 0

    val myPaintAnimal = Paint()
    val myPaintOther = Paint()
    val myPaintGrass = Paint()

    val basicRect = Rect(0, 0, 0, 0)
    val shiftVector = Vector2d(2, 2)
    val position = Vector2d(2, 2)

    override fun onDraw(canvas: Canvas?) {
        myPaintAnimal.setColor(Color.GREEN)

        myPaintOther.setColor(Color.BLACK)
        myPaintGrass.setColor(Color.RED)

        var (lowerLeft, upperRight) = map.getVectors()

        lowerLeft -= shiftVector
        upperRight += shiftVector

        val width = abs(upperRight.x - lowerLeft.x)
        val height = abs(upperRight.y - lowerLeft.y)
        val canvasWidth = canvas?.width
        val canvasHeight = canvas?.height

        var x1 = 0
        var y1 = 0
        var x2 = 0
        var y2 = 0

        val xRatio = canvasWidth?.div(width)
        val yRatio = canvasHeight?.div(height)

        val xShift = 0 - lowerLeft.x
        val yShift = 0 - lowerLeft.y

        myPaintAnimal.textSize = 70.0F
        if (xRatio != null && yRatio != null) {
            if (xRatio < 35 || yRatio < 35)
                myPaintAnimal.textSize = 40.0F
        }

        for (x in (lowerLeft.x)..(upperRight.x + 1)) {
            for (y in (lowerLeft.y)..(upperRight.y + 1)) {
                x1 = (x + xShift) * xRatio!!
                x2 = (x + xShift + 1) * xRatio
                y1 = (y + yShift) * yRatio!!
                y2 = (y + yShift + 1) * yRatio

                basicRect.set(x1, y1, x2, y2)
                position.set(x, y)
                val obj = map.objectAt(position)


                when (obj) {
                    is Animal -> {
                        canvas?.drawRect(basicRect, myPaintOther)
                        when (obj.orientation) {
                            MapDirection.NORTH -> canvas?.drawText(
                                "^",
                                (x1 + xRatio / 7).toFloat(),
                                (y1 + yRatio / 0.9).toFloat(),
                                myPaintAnimal
                            )
                            MapDirection.EAST -> canvas?.drawText(
                                ">",
                                (x1 + xRatio / 7).toFloat(),
                                (y1 + yRatio / 0.9).toFloat(),
                                myPaintAnimal
                            )
                            MapDirection.SOUTH -> canvas?.drawText(
                                "v",
                                (x1 + xRatio / 7).toFloat(),
                                (y1 + yRatio / 0.9).toFloat(),
                                myPaintAnimal
                            )
                            MapDirection.WEST -> canvas?.drawText(
                                "<",
                                (x1 + xRatio / 7).toFloat(),
                                (y1 + yRatio / 0.9).toFloat(),
                                myPaintAnimal
                            )
                        }
                    }
                    is Grass -> canvas?.drawRect(basicRect, myPaintGrass)
                    else -> canvas?.drawRect(basicRect, myPaintOther)
                }
            }
        }
    }

    private fun moveAnimal(direction: MoveDirection) {
        if (animalIndex == numberOfAnimals)
            animalIndex = 0
        animals[animalIndex].move(direction)
        animalIndex += 1
    }


    fun moveAnimalAndRefresh(direction: MoveDirection) {
        moveAnimal(direction)
        postInvalidate()
    }



    private fun placeInitialPositions(initialPositions: List<Vector2d>) {
        initialPositions.forEach { position ->
            val succeeded: Boolean = map.place(Animal(map, position))
            if (!succeeded) throw IllegalArgumentException("Can't place Animal at $position")
        }
    }
}
