package shateq.goku

import shateq.java.goku.Tournament
import shateq.kotlin.goku.registry.Performer

fun main() {
    val tourney = Tournament("Test-Tourney")

    with(tourney) {
        setScoring(Tournament.Scoring(3f, 1f, 1f))

        addPlayer("Anna")
        addPlayer(Performer("Tomek"))
        addPlayer(Performer("Mateusz"))
        addPlayer("Andrzej")
    }
}