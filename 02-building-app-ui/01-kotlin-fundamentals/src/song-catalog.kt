fun main() {
    val bohemianRhapsody = Song("Bohemian Rhapsody", "Queen", 1975, 1234567)
    println(bohemianRhapsody)
    println(bohemianRhapsody.isPopular)
}

class Song(val title: String, val artist: String, val year: Int, val count: Int) {
    var isPopular = count >= 1000

    override fun toString(): String {
        return "$title, performed by $artist, was released in $year."
    }
}