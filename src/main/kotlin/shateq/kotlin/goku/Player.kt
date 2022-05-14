package shateq.kotlin.goku

import shateq.java.goku.Color

data class Player(@get:JvmName("name") val name: String) {
    companion object {
        @JvmField val PAUSE = Player("PAUSE")
    }

    val opponents: List<Player> = ArrayList()

    lateinit var lastColor: Color

    var points: Int = 0
        @JvmName("points") get
        private set

    fun addPoints(number: Int) = this.points + number
}