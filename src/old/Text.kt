package old

import java.awt.Font
import org.lwjgl.opengl.GL11
import org.newdawn.slick.TrueTypeFont

class Text(var string: String, var font: Font, var fontColor: Color) : Style {
    var ttFont = TrueTypeFont(font, true)

    override fun draw(rectangle: Rectangle) {
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        ttFont.drawString(rectangle.absoluteX, rectangle.absoluteY, string, Util.slickColor(fontColor))
        GL11.glDisable(GL11.GL_TEXTURE_2D)
    }

    fun getWidth() = ttFont.getWidth(string).toFloat()

    fun getHeight() = ttFont.getHeight(string).toFloat()

    companion object {
        val DEFAULT_FONT = Font("Verdana", 0, 18)
        val DEFAULT_TTFONT = TrueTypeFont(DEFAULT_FONT, true)

        fun adjustedFont(font: Font, newSize: Int): Font {
            return Font(font.fontName, font.style, newSize)
        }
    }
}
