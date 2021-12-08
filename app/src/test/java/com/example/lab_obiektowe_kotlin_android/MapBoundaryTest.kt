package agh.ics.oop

import org.junit.Test

import org.junit.Assert.*

internal class MapBoundaryTest {

    @Test
    fun getBoundaries() {
        val map = RectangularMap(10,10)
        val a = Animal(map, Vector2d(2,1))
        val b = Animal(map, Vector2d(0,3))
        val c = Animal(map, Vector2d(9,4))
        val d = Animal(map, Vector2d(5,2))
        val e = Animal(map, Vector2d(0,3))
        val f = Animal(map, Vector2d(2,9))
        val boundaries = MapBoundary()
        boundaries.add(a)
        boundaries.add(b)
        boundaries.add(c)
        boundaries.add(d)
        boundaries.add(e)
        boundaries.add(f)
        assertEquals(Pair(Vector2d(0, 1), Vector2d(9, 9)), boundaries.getBoundaries())
    }
    @Test
    fun animalObserver(){
        val map = RectangularMap(10,10)
        val a = Animal(map, Vector2d(2,1))
        val b = Animal(map, Vector2d(0,3))
        val c = Animal(map, Vector2d(9,4))
        val d = Animal(map, Vector2d(5,2))
        val e = Animal(map, Vector2d(0,3))
        val f = Animal(map, Vector2d(2,9))
        val boundaries = MapBoundary()
        boundaries.add(a)
        boundaries.add(b)
        boundaries.add(c)
        boundaries.add(d)
        boundaries.add(e)
        boundaries.add(f)

        boundaries.animalObserver(Vector2d(0, 3), Vector2d(-50, 100))

        assertEquals(Pair(Vector2d(0, 1), Vector2d(9, 9)), boundaries.getBoundaries())


    }
}