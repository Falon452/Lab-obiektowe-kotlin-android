package agh.ics.oop


import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap


/*
Obiekty mają być dodawane w ten sposób, że skrajne pozycje na liście zawsze zajmowane są przez obiekty które mają odpowiednio największą oraz najmniejszą wartość indeksu w danym wymiarze.
 to jest O((n^2)/2)
 lepiej dodać wszystkie i posortować O(nlogn)
 */
enum class classType {
    Animal,
    Grass

}
class MapBoundary {
    val objectsSortedWithX: SortedSet<Pair<Vector2d, classType>> = TreeSet(compareBy ({ it.first.x }, { it.first.y }))
    val objectsSortedWithY: SortedSet<Pair<Vector2d, classType>> = TreeSet(compareBy ({ it.first.y }, { it.first.x }))


    fun add( elem: IElement){
        if (elem is Animal) {
            objectsSortedWithX.add(Pair(elem.position, classType.Animal))
            objectsSortedWithY.add(Pair(elem.position, classType.Animal))
        }
        if (elem is Grass){
            objectsSortedWithX.add(Pair(elem.position, classType.Grass))
            objectsSortedWithY.add(Pair(elem.position, classType.Grass))
        }
    }

    fun getBoundaries(): Pair<Vector2d, Vector2d> {
        return Pair(
            Vector2d(objectsSortedWithX.first().first.x, objectsSortedWithY.first().first.y),
            Vector2d(objectsSortedWithX.last().first.x, objectsSortedWithY.last().first.y))
    }

    fun animalObserver(old: Vector2d, new: Vector2d){
        objectsSortedWithX.remove(Pair(old, classType.Animal))
        objectsSortedWithY.remove(Pair(old, classType.Animal))
        objectsSortedWithX.add(Pair(new, classType.Animal))
        objectsSortedWithY.add(Pair(new, classType.Animal))
    }
}
