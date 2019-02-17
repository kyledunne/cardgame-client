package cardgame

import org.lwjgl.opengl.GL11
import org.newdawn.slick.TrueTypeFont
import org.newdawn.slick.opengl.Texture
import org.newdawn.slick.opengl.TextureLoader
import org.newdawn.slick.util.ResourceLoader
import java.awt.Font

interface Style {
    fun draw(rect: Rect)
}

class Image(texturePath: String, var backgroundColor: Color?, var bindingColor: Color?): Style {
    var texture: Texture = TextureLoader.getTexture(texturePath.substring(texturePath.length - 3), ResourceLoader.getResourceAsStream(texturePath))
        private set

    override fun draw(rect: Rect) {
        backgroundColor?.draw(rect)
        val textureWidth = texture.width
        val textureHeight = texture.height
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        bindingColor?.bind()
        texture.bind()
        GL11.glBegin(GL11.GL_POLYGON)
            GL11.glTexCoord2f(0f, 0f)
            GL11.glVertex2f(rect.x, rect.y)
            GL11.glTexCoord2f(0f, textureHeight)
            GL11.glVertex2f(rect.x, rect.y + rect.h)
            GL11.glTexCoord2f(textureWidth, textureHeight)
            GL11.glVertex2f(rect.x + rect.w, rect.y + rect.h)
            GL11.glTexCoord2f(textureWidth, 0f)
            GL11.glVertex2f(rect.x + rect.w, rect.y)
        GL11.glEnd()
        GL11.glDisable(GL11.GL_TEXTURE_2D)
    }
}

class Text(var string: String, var font: Font, var fontColor: Color): Style {
    var ttFont = TrueTypeFont(font, true)

    override fun draw(rect: Rect) {
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        ttFont.drawString(rect.x, rect.y, string, Util.slickColor(fontColor))
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
