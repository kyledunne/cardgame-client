package cardgame

import java.util.Random

/**
 * Created by Kyle on 5/19/2016.
 */
object Util {
    val RAND = Random()

    fun slickColor(color: Color): org.newdawn.slick.Color {
        return org.newdawn.slick.Color(color.r, color.g, color.b, color.a)
    }
}