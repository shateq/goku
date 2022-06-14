package shateq.goku.kotlin

import shateq.goku.java.Color

data class Player(@get:JvmName("name") val name: String, @get:JvmName("index") val index: Int) {
    companion object {
        @JvmField val PAUSE = Player("PAUSE", 0)
    }

    val opponents: List<Player> = ArrayList()

    lateinit var lastColor: Color

    var wins: Int = 0
        @JvmName("wins") get
        private set

    var draws: Int = 0
        @JvmName("draws") get
        private set

    fun incWins() { this.wins += 1 }

    fun incDraws() { this.draws += 1 }
}