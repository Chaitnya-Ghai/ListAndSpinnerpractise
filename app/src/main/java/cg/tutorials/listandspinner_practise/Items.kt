package cg.tutorials.listandspinner_practise

data class Items(
    var name:String,
    var quantity: Int
){
    override fun toString(): String {
        return "$name"
    }
}
