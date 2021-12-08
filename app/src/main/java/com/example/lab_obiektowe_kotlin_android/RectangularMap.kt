package agh.ics.oop


class RectangularMap(private val width: Int, private val height: Int): AbstractWorldMap() {
    final private val animalHashMap: HashMap<Vector2d, Animal> = hashMapOf()
    final override var lowerLeft = Vector2d(0, 0)

    final override var upperRight: Vector2d = if (width > 0 && height > 0 )
        Vector2d(height - 1, width - 1)
    else
        throw IllegalArgumentException("width and height must be positive")


    override fun observePosition(old: Vector2d, new: Vector2d){
        val animal = animalHashMap.remove(old)
        if (animal is Animal)
            animalHashMap[new] = animal
        else throw Exception("sooo weird")
    }

    override fun place(animal: Animal): Boolean {
        if (!isOccupied(animal.position)){
            animal.addObserver(this::observePosition)
            if (canMoveTo(animal.position)) {
                animalHashMap[animal.position] = animal
                return true
            }
        }
        return false
    }

    override fun canMoveTo(position: Vector2d): Boolean {
        return super.canMoveTo(position) &&
                (position follows lowerLeft) &&
                (position precedes upperRight)
    }

    override fun isOccupied(position: Vector2d): Boolean = animalHashMap.containsKey(position)
    override fun objectAt(position: Vector2d): Any? = this.animalHashMap[position]
    override fun animals(): List<Animal> = animalHashMap.values.toList()
}