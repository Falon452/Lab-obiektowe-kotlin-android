package agh.ics.oop

class OptionsParser {
    fun parse(strList: List<String>): List<MoveDirection> {
        val directions: MutableList<MoveDirection> = mutableListOf()

        strList.forEach { str ->
            when (str.lowercase()) {
                "f", "forward" -> directions.add(MoveDirection.FORWARD)
                "b", "backward" -> directions.add(MoveDirection.BACKWARD)
                "l", "left" -> directions.add(MoveDirection.LEFT)
                "r", "right" -> directions.add(MoveDirection.RIGHT)
                else -> throw IllegalArgumentException(str.lowercase() + " is not legal move specification")
            }
        }
        return directions
    }

}