package agh.ics.oop

import org.junit.Test

import org.junit.Assert.*

internal class RectangularMapTest {
    @Test
    fun IllegalArguments(){
        assertThrows(IllegalArgumentException::class.java){
            RectangularMap(-3, -3)
        }
        assertThrows(IllegalArgumentException::class.java){
            RectangularMap(0, -3)
        }
        assertThrows(IllegalArgumentException::class.java){
            RectangularMap(3, -3)
        }
        assertThrows(IllegalArgumentException::class.java){
            RectangularMap(0, 0)
        }

    }

    @Test
    fun place() {
        val map = RectangularMap(10, 10)
        map.place(Animal(map, Vector2d(-3, 0)))
        assertEquals(0, map.animals().size)
        map.place(Animal(map, Vector2d(0, -1)))
        assertEquals(0, map.animals().size)
        map.place(Animal(map, Vector2d(-5, -1)))
        assertEquals(0, map.animals().size)
        map.place(Animal(map, Vector2d(12, 12)))
        assertEquals(0, map.animals().size)

        val a = Animal(map, Vector2d(8, 8))
        map.place(a)
        assertEquals(a, map.objectAt(Vector2d(8,8)))
    }

    @Test
    fun canMoveTo() {
        val map = RectangularMap(10, 10)
        val a = Animal(map)
        assertEquals(false, map.canMoveTo(Vector2d(11, 11)))
        assertEquals(false, map.canMoveTo(Vector2d(-3, -4)))
        assertEquals(true, map.canMoveTo(Vector2d(3, 3)))

    }

    @Test
    fun isOccupied() {
        val map = RectangularMap(10, 10)
        val a = Animal(map)
        map.place(a)
        assertEquals(true, map.isOccupied(Vector2d(2, 2)))
    }

    @Test
    fun objectAt() {
        val map = RectangularMap(10, 10)
        val a = Animal(map)
        map.place(a)
        assertEquals(a, map.objectAt(Vector2d(2, 2)))
    }

}