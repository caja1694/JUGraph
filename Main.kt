import java.io.File
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    var nodeData = NodeData()
    var positionNodes: ArrayList<String> = nodeData.positionNodes.split("\n") as ArrayList<String>
    var directionNodes: ArrayList<String> = nodeData.directionNodes.split("\n") as ArrayList<String>
    var connections: ArrayList<String> = nodeData.connections.split("\n") as ArrayList<String>
    var graph = Graph()

    for(node in directionNodes){
        var nodeName = node.split(";").get(0)
        var nodeDir = node.split(";").get(1)
        var node = Room(name = nodeName, directions = nodeDir)
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



}
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