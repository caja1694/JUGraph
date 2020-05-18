import kotlin.contracts.contract

class Dictionary(){
    var dictionary: HashMap<String, String> = HashMap()

    fun getNameOfNode(searchItem: String): String{
        if(dictionary.contains(searchItem))
            return searchItem
        if(dictionary.containsValue(searchItem))
            return dictionary.filterValues { it == searchItem }.keys.toString()

        return getNameOfClosestNode(searchItem)
    }
    fun getNameOfClosestNode(searchItem: String):String{
        var nameOfBlock = searchItem.substring(0, 3)
        when(nameOfBlock){
            "E14" -> return "Gjuerisalen_Fagerhultsaulan"
            else -> {
                return nameOfBlock + "XX"
            }
        }
    }

    fun init(nodeNames: ArrayList<String>){
        var letters = "ABCDFGHJ"
        for (l in letters){
            dictionary.put(l.toString(), "")
        }
        for(n in nodeNames){
            dictionary.put(n, "")
        }
    }
    fun map(room: String){
        var key: String = room.get(0).toString().toUpperCase()
        if(key != "E"){
            dictionary.put(key, room)
        }
        if(dictionary.containsKey(room)) {
            if (!dictionary.containsValue(room))
                dictionary.put(key, room)
        }
        else {
            key = room.substring(0, 3)
            dictionary.put(key, room)
        }
    }
}