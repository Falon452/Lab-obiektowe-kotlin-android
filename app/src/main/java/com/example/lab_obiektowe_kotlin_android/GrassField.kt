package agh.ics.oop

import kotlin.math.roundToInt


class GrassField(nOfGrassFields: Int): AbstractWorldMap() {
    private val nOfGrassFields = if (nOfGrassFields >= 0)
        nOfGrassFields
    else
        throw IllegalArgumentException("Parameter nOfGrassFields must be positive.")

    private val grassFieldHashMap = super.objectsHashMap
    private val boundary: MapBoundary =MapBoundary()
    private val upperBound: Int = (kotlin.math.sqrt((nOfGrassFields * 10).toDouble())).roundToInt()
    override var lowerLeft: Vector2d = Vector2d(2,2)
    override var upperRight: Vector2d = Vector2d(2,2)

    override fun observePosition(old: Vector2d, new: Vector2d): Unit {
        boundary.animalObserver(old, new)
        updateVectors()
        return super.observePosition(old, new)
    }


    fun addNGrasses() {
        var i = 0
        while (i != nOfGrassFields) {
            if (placeGrass(Grass(getRandomPosition())))
                i += 1
        }
    }

    override fun place(animal: Animal): Boolean {
        boundary.add(animal)
        updateVectors()
        return super.place(animal)
    }

    private fun placeGrass(grass: Grass): Boolean {
        boundary.add(grass)
        updateVectors()
        if (!(isOccupied(grass.position))){
            this.grassFieldHashMap[grass.position] = grass
            return true
        }
        return false
    }


    private fun getRandomPosition() = Vector2d(
        (-upperBound.div(2)..upperBound.div(2)).random(),
        (-upperBound.div(2)..upperBound.div(2)).random()
    )

    private fun updateVectors(){
        val boundaries = boundary.getBoundaries()
        lowerLeft = boundaries.first
        upperRight = boundaries.second
    }

    fun getVectors(): Pair<Vector2d, Vector2d> {
        return Pair(lowerLeft, upperRight)
    }

}