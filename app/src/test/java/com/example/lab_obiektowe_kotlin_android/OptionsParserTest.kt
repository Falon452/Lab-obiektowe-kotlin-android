package agh.ics.oop

import agh.ics.oop.MoveDirection.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.ExpectedException
import kotlin.reflect.KClass

internal class OptionsParserTest{
    val parser = OptionsParser()
    val T1 = listOf<String>("f", "F", "fOrwArd", "forward", "Forward","FORWARD",
        "b", "B", "baCkWard", "BACKWARD", "backward", "Backward",
        "l", "L", "leFt", "left", "Left", "LEFT",
        "r", "R", "RiGHt", "right", "Right", "RIGHT")
    val R1 = listOf<MoveDirection>(
        FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD,
        BACKWARD, BACKWARD, BACKWARD, BACKWARD, BACKWARD, BACKWARD,
        LEFT, LEFT, LEFT, LEFT, LEFT, LEFT,
        RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, RIGHT)


    @Test
    fun parse(){
        val parsedResult1 = parser.parse(T1)
        parsedResult1.forEachIndexed() {index, moveDirection ->
            assertEquals(moveDirection, R1[index])
        }
    }

    @Test(expected = java.lang.IllegalArgumentException::class)
    fun parseThrow(){
        val T2 = listOf<String>("garbage")
        parser.parse(T2)
    }
}