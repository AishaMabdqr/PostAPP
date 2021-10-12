package com.example.postrequestapp


class Details {

    val users : List<Items>? = null

    class Items() {
        var name: String? = null
        var location: String? = null
        var pk:Int? = null


        constructor(name: String?, location:String? ,pk: Int? ) : this() {
            this.name = name
            this.location = location
            this.pk = pk
        }
}

}