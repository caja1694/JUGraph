import java.io.File
import kotlin.collections.ArrayList
import kotlin.reflect.jvm.internal.impl.utils.DFS

fun main(args: Array<String>) {
    var rooms = File("rooms.txt").readLines()
    var graph = Graph()
    var dirs = file("dirs.txt")
    var pos = file("position.txt")
    var dictionary: Dictionary = Dictionary()
    // Creating nodes and adding them to the graph without neighbors.
    // Also adding directions to this from prev node and setting its position.
    for(neighbors in rooms){
        println(neighbors)
        var room = createNode(neighbors)
        room.dirs = dirs[rooms.indexOf(neighbors)]
        room.pos = pos[rooms.indexOf(neighbors)]
        graph.addRoom(room)
    }
    // Setting adjlist in node object = Creating edges = Setting neighborList
    for(i in rooms){
        var tmp = i.split(" ")
        var source = graph.getRoom(tmp.get(0))
        for(s in tmp){
            var dest = graph.getRoom(s)
            if(dest?.name != source?.name && dest != null)
                source?.addAdj(dest!!)
        }
    }
    // Searching a node and printing dirs.
    dictionary.init(graph.getRoomNames())
    println("Get directions to: ")
    var sysIn = readLine()!!
    var searchItem = dictionary.getNameOfNode(sysIn)
    println("Searchitem: " + searchItem)
    println(graph.getPath("Entrance", searchItem))
}
// Creating a new node object.
fun createNode(neighbors: String): Room{
    var node: Room = Room(neighbors.split(" ").get(0))
    return node
}
// Retunrs and Arraylist Arr[i] = a line of text in file.txt.
fun file(file: String): ArrayList<String>{
    var f = File(file)
    var input = f.readLines()
    var s: ArrayList<String> = ArrayList()
    for(i in input){
         s.add(i)
    }
    return s
}