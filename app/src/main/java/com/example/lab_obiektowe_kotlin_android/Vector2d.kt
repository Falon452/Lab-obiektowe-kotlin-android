package agh.ics.oop

import java.util.*
import kotlin.math.max
import kotlin.math.min


class Vector2d(var x: Int, var y: Int) {
    fun set(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    override fun toString(): String = "($x, $y)"

    infix fun precedes(other: Vector2d) = (this.x <= other.x) && (this.y <= other.y)
    infix fun follows(other: Vector2d) = (this.x >= other.x) && (this.y >= other.y)

    fun upperRight(other: Vector2d) = Vector2d(max(this.x, other.x), max(this.y, other.y))
    fun lowerLeft(other: Vector2d) = Vector2d(min(this.x, other.x), min(this.y, other.y))

    operator fun plus(other: Vector2d) = Vector2d(this.x + other.x, this.y + other.y)
    operator fun minus(other: Vector2d) = Vector2d(this.x - other.x, this.y - other.y)
    fun opposite() = Vector2d(-this.x, -this.y)

    override fun equals(other: Any?): Boolean {
        if (other is Vector2d) {
            return (this.x == other.x) && (this.y == other.y)
        }
        return false
    }

    /* these were created in order to use this:val (left, bottom) = Any:Vector2d */
    operator fun component1() = x
    operator fun component2() = y

    override fun hashCode(): Int {
        return Objects.hash(this.x, this.y)
    }
}
