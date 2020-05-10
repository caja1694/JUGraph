import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.reflect.jvm.internal.impl.utils.DFS

class MNode(name: String){
    var name: String = name
    var dirs: String = ""
    var pos: String = ""
    var adjList: LinkedList<MNode> = LinkedList()
    fun addAdj(node: MNode){
        adjList.add(node)
    }

    override fun toString(): String {
        return "${name}"
    }
}
class Graph{
    var graph: HashMap<String, MNode> = HashMap()
    var path: ArrayList<MNode> = ArrayList() // Empty this after returning ?
    var pos: String = ""
    fun getNode(name: String): MNode? {
        return graph.get(name)
    }
    //Change name
    fun hasPathDFS(start: String, end: String):Boolean{
        var visited: HashSet<String> = HashSet()
        return hasPathDFS(this.getNode(start)!!, this.getNode(end)!!, visited)
    }
    //Change name
    fun hasPathDFS(s: MNode, d: MNode, visited: HashSet<String>): Boolean {
        if(visited.contains(s.name)){
            return false
        }
        visited.add(s.name)
        if(s == d){
            return true
        }
        for (child in s.adjList){
            if(hasPathDFS(child, d, visited)) {
                path.add(child)
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