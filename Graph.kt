import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class Room(name: String){
    var name: String = name
    var dirs: String = ""
    var pos: String = ""
    var adjList: LinkedList<Room> = LinkedList()

    fun addAdj(node: Room){
        adjList.add(node)
    }

    override fun toString(): String {
        return "${name}"
    }
}
class Graph{
    var graph: HashMap<String, Room> = HashMap()
    var nodePath: ArrayList<Room> = ArrayList() // Empty this after returning ?
    var pos: String = ""
    fun getRoom(name: String): Room? {
        return graph.get(name)
    }
    fun addRoom(room: Room){
        if(!graph.contains(room.name))
            graph.put(room.name, room)
    }
    fun setConnectionBetweenRooms(room1: Room, room2: Room){
        if (room1 != null && room2 != null){
            if(!room1.adjList.contains(room2)){
                room1.adjList.push(room2)
            }
            if(!room2.adjList.contains(room1)) {
                room2.adjList.add(room1)
            }
        }
    }
    fun getRoomNames(): ArrayList<String>{
        var roomNames: ArrayList<String> = ArrayList()
        for(key in graph.keys){
            roomNames.add(key)
        }
        return roomNames
    }

    //Change name
    fun getPath(start: String, end: String):String{
        var visited: HashSet<String> = HashSet()
        var directions = ""
        var start: Room = getRoom(start)!!
        var destination: Room = getRoom(end)!!

        nodePath.clear()

        if(findPathDFS(start, destination, visited)){
            var roomsOnPath = nodePath.reversed()

            var iterator = roomsOnPath.size-1

            for(i in 0 until iterator){
                directions+="Step${i+1}: " + roomsOnPath[i].dirs
                directions+="\n"
            }
            directions+="Step${roomsOnPath.size}: " + roomsOnPath.last().pos
        }
        else{
            directions = "The room was not recognized."
        }
        return directions
    }
    //Change name
    fun findPathDFS(start: Room, destination: Room, visited: HashSet<String>): Boolean {
        if(visited.contains(start.name)){
            return false
        }
        visited.add(start.name)
        if(start == destination){
            return true
        }
        for (nextToCheck in start.adjList){
            if(findPathDFS(nextToCheck, destination, visited)) {
                nodePath.add(nextToCheck)
                return true
            }
        }
        return false
    }
    override fun toString(): String {
        var s = ""
        for(key in graph.keys){
            s+= key
            s+= "-->"
            s+= graph.get(key)?.adjList
            s+= "\n"
        }
        return s
    }

}