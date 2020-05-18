import java.io.File
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    var positionNodes = readFile("positionNodes.txt")
    var directionNodes = readFile("directionsNodes.txt")
    var connections = readFile("connections.txt")
    var dirNodes: ArrayList<Room> = ArrayList()
    var graph = Graph()

    for(node in directionNodes){
        var nodeName = node.split(";").get(0)
        var nodeDir = node.split(";").get(1)
        println("Nodename: " + nodeName + "NodeDirs: " + nodeDir)
        var node = Room(name = nodeName, directions = nodeDir)
        dirNodes.add(node)
        graph.addRoom(node)
    }

    for (connection in connections){
        var names = connection.split(" ")
        for(i in 1 until names.size){
            var root = graph.getRoom(names.get(0))
            var neighbor = graph.getRoom(names.get(i))
            if(root != null && neighbor != null){
                graph.setConnectionBetweenRooms(root, neighbor)
            }
        }
    }
    for(node in positionNodes){
        var values = node.split(";")
        var parentName = values.get(0)
        var childName = values.get(1)
        var position = values.get(2)
        var newRoom = Room(childName, position)
        var parentRoom = graph.getRoom(parentName)
        graph.addRoom(newRoom)
        if (parentRoom != null) {
            graph.setConnectionBetweenRooms(parentRoom, newRoom)
        }
    }

    var dictionary = Dictionary()
    dictionary.init(graph.getRoomNames())
    println("Get directions to: ")
    var sysIn = readLine()!!
    var searchItem = dictionary.getNameOfNode(sysIn)
    println("Getting directions to: "+ searchItem)
    println(graph.getPath("A", searchItem))


    /*
    var rooms = File("rooms.txt").readLines()
    var graph = Graph()
    var dirs = readFile("dirs.txt")
    var pos = readFile("position.txt")
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
     */
}
// Creating a new node object.
/*
fun createNode(neighbors: String): Room{
    var node: Room = Room(neighbors.split(" ").get(0))
    return node
}*/
// Retunrs and Arraylist Arr[i] = a line of text in file.txt.
fun readFile(file: String): ArrayList<String>{
    var f = File(file)
    var input = f.readLines()
    var sArr: ArrayList<String> = ArrayList()
    for(i in input){
         sArr.add(i)
    }
    return sArr
}