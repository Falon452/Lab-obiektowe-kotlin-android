package agh.ics.oop

import agh.ics.oop.MoveDirection.*
import agh.ics.oop.MapDirection.*
import kotlin.properties.Delegates


typealias PositionObserverFunc = (Vector2d, Vector2d) -> Unit


class Animal(private val map: IWorldMap) : IElement {
    var orientation: MapDirection = NORTH
        private set
    private val moveObservers: MutableList<PositionObserverFunc> = mutableListOf()

    override var position: Vector2d by Delegates.observable(Vector2d(2, 2)) { _, new, old ->
        moveObservers.forEach { observer -> observer(new, old) }
    }
        private set

    constructor(map: IWorldMap, initialPosition: Vector2d) : this(map) {
        position = initialPosition
    }

    override fun toString(): String {
        return when (orientation) {
            NORTH -> "^"
            EAST -> ">"
            SOUTH -> "v"
            WEST -> "<"
        }
    }

    fun isAt(otherPosition: Vector2d) = position == otherPosition

    fun addObserver(observer: PositionObserverFunc) = moveObservers.add(observer)
    fun removeObserver(observer: PositionObserverFunc) = moveObservers.remove(observer)

    fun move(direction: MoveDirection) {
        when (direction) {
            RIGHT -> {
                orientation = orientation.next()
            }
            LEFT -> {
                orientation = orientation.previous()
            }
            FORWARD -> {
                val newPosition = position + orientation.toUnitVector()
                if (map.canMoveTo(newPosition)) {
                    position = newPosition
                }
            }
            BACKWARD -> {
                val newPosition = position - orientation.toUnitVector()
                if (map.canMoveTo(newPosition)) {
                    position = newPosition
                }
            }
        }
    }
}