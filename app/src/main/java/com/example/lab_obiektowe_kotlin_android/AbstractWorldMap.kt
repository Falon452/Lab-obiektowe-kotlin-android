package agh.ics.oop

import java.util.*

abstract class AbstractWorldMap : IWorldMap {
    protected open val objectsHashMap: HashMap<Vector2d, IElement> = hashMapOf()
    protected open var lowerLeft = Vector2d(2, 2)
    protected open var upperRight = Vector2d(2, 2)

    protected open fun observePosition(old: Vector2d, new: Vector2d) {
        val animal = objectsHashMap.remove(old)
        if (animal is Animal)
            this.objectsHashMap[new] = animal
        else throw Exception("animal was deleted from objectsHashMap and animal should be Animal")
    }

    override fun toString(): String = MapVisualizer(this).draw(lowerLeft, upperRight)

    override fun animals(): List<Animal> = objectsHashMap.values.filterIsInstance<Animal>()
    override fun place(animal: Animal): Boolean {
        if (!isOccupied(animal.position)) {
            animal.addObserver(this::observePosition)
            if (canMoveTo(animal.position)) {
                objectsHashMap[animal.position] = animal
                return true
            }
        }
        return false
    }

    override fun canMoveTo(position: Vector2d): Boolean = !(this.isOccupied(position))

    override fun isOccupied(position: Vector2d): Boolean = objectsHashMap.containsKey(position)
    override fun objectAt(position: Vector2d): Any? = this.objectsHashMap[position]
}