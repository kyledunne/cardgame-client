package main

import java.util.Random

object Util {
    val RAND = Random()

    fun slickColor(color: Color): org.newdawn.slick.Color {
        return org.newdawn.slick.Color(color.r, color.g, color.b, color.a)
    }
}