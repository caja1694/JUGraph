import kotlin.contracts.contract

class Dictionary(){
    var dictionary: HashMap<String, String> = HashMap()

    fun getNameOfNode(searched: String): String{
        var searchItem = searched.toUpperCase()
        if(dictionary.contains(searchItem))
            return searchItem

        if(dictionary.containsValue(searchItem))
            return dictionary.filterValues { it == searchItem }.keys.toString()

        if(searchItem.get(0) != 'E')
            return getNameOfOuterBuilding(searchItem)

        return getNameOfClosestNode(searchItem)
    }
    fun getNameOfClosestNode(searchItem: String):String{
        var nameOfBlock = searchItem.substring(0, 3).toUpperCase()

        when(nameOfBlock){
            "E10" -> return "E1020"
            else -> return nameOfBlock + "XX"
        }
    }
    fun getNameOfOuterBuilding(searchItem: String): String{
        var firstLetter = searchItem.get(0)

        when(firstLetter){
            'A' -> return "Rektorskansli"
            'B' -> return "JIBS"
            'C' -> return "Bibliotek"
            'D' -> return "Studenternas Hus"
            'F' -> return "Mariedal"
            'G' -> return "Hälso"
            'H' -> return "HLK"
            'J' -> return "Campus"
            else -> return "NOT_FOUND"
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