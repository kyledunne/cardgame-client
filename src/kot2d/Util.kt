package kot2d

import kot2d.Color
import org.lwjgl.opengl.Display
import java.util.Random

object Util {
    val RAND = Random()

    fun slickColor(color: Color): org.newdawn.slick.Color {
        return org.newdawn.slick.Color(color.r, color.g, color.b, color.a)
    }
}