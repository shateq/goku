package shateq.goku

import shateq.goku.cli.GokuCli
import shateq.goku.compose.compose
import java.util.*

@JvmField
val SCANNER = Scanner(System.`in`)

fun main(vararg args: String) {
    if (args.contains("-nogui")) {
        val cli = GokuCli()
        cli.loop()
    } else {
        compose()
    }
}