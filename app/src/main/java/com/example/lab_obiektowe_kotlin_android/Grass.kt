package agh.ics.oop

class Grass(initPosition: Vector2d): IElement{
    final override val position = initPosition

    override fun toString(): String {
        return "*"
    }
}