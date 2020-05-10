import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    var rooms = File("rooms.txt").readLines()
    var graph = Graph()
    var dirs = file("dirs.txt")
    var pos = file("position.txt")
    // Creating nodes and adding them to the graph without neighbors
    // Also adding directions to this from prev node and setting its position.
    for(i in rooms){
        var node = createNode(i)
        node.dirs = dirs[rooms.indexOf(i)]
        node.pos = pos[rooms.indexOf(i)]
        graph.graph.put(node.name, node)
    }
    // Setting adjlist in node object = Creating edges = Setting neighborList
    for(i in rooms){
        var tmp = i.split(" ")
        var source = graph.getNode(tmp.get(0))
        for(s in tmp){
            var dest = graph.getNode(s)
            if(dest?.name != source?.name && dest != null)
             source?.addAdj(dest!!)
        }
    }
    // Searching a node and printing dirs.
    println("Get directions to: ")
    var sysIn = readLine()!!
    println(graph.hasPathDFS("Entrance", sysIn))
    var path = graph.path.reversed()
    var directions = ""
    for(i in path){
        directions += "Step${path.indexOf(i)+1}: " + i.dirs
        directions += "\n"
    }
    directions+= "Step${path.size+1}: " + path.last().pos
    println(directions)
}
// Creating a new node object.
fun createNode(n: String): MNode{
    var node: MNode = MNode(n.split(" ").get(0))
    return node
}
// Retunrs and Arraylist Arr[i] = a line of text in file.txt.
fun file(file: String): ArrayList<String>{
    var f = File(file)
    println("test")
    var input = f.readLines()
    var s: ArrayList<String> = ArrayList()
    for(i in input){
         s.add(i)
    }
    return s
}

// Old graphclass and functions
// Graph has Hashmap<String, HashSet<Node>>
/*
class XNode(name: String){
    var name: String = name
    var adjList: HashSet<XNode> = HashSet()
    var dir = ""

    fun addNeighbor(n: XNode){
        adjList.add(n)
    }
    override fun toString(): String {
        return "${name}"
    }
    fun printDirs(){
        println(this.dir)
    }
}
class XGraph<XNode> {
    val adjacencyMap: HashMap<String, HashSet<XNode>> = HashMap()

    fun addEdge(sourceVertex: String, destinationVertex: XNode) {
        // Add edge to source vertex / node.
        adjacencyMap // To add for dest, just copy code and switch source/dest
                .computeIfAbsent(sourceVertex){HashSet()}
                .add(destinationVertex)
    }

    // Checks if there is a path with BFS
    fun getPathBFS(start: XNode, end: XNode): Boolean {
        var next: LinkedList<XNode> = LinkedList()
        var visited: HashSet<XNode> = HashSet()
        next.add(start)
        while(!next.isEmpty()){
            var node: XNode = next.remove()
            if(node === end){
                return true
            }
            if(visited.contains(node))
                continue
            visited.add(node)
            for(n in adjacencyMap){
                for(v in n.value){
                    next.add(v)
                }
            }

        }
        return false
    }
    fun gePathDFS(start: XNode, end: XNode){

    }

    override fun toString(): String = StringBuffer().apply {
        for (key in adjacencyMap.keys) {
            append("$key -> ")
            append(adjacencyMap[key]?.joinToString(", ", "[", "]\n"))
        }
    }.toString()
}





fun oldGraphFunc(){
    var input = File("rooms.txt").readLines()
    var nodes = XGraph<XNode>()
    var dirs = files()
    for(i in input){
        var ns = i.split(" ")
        var tempNode: XNode = XNode(ns.get(0))
        tempNode.dir = dirs[input.indexOf(i)]
        var tmpN = XNode("")
        for(x in 1 until ns.size){
            tmpN = XNode(ns.get(x))
            nodes.addEdge(tempNode.name, tmpN)
        }
    }
    println(nodes)
}

 */