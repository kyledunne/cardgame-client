package old

import org.newdawn.slick.opengl.Texture
import org.newdawn.slick.opengl.TextureLoader
import org.newdawn.slick.util.ResourceLoader
import org.lwjgl.opengl.GL11

class Image(texturePath: String, var backgroundColor: Color?, var bindingColor: Color?) : Style {
    var texture: Texture = TextureLoader.getTexture(texturePath.substring(texturePath.length - 3), ResourceLoader.getResourceAsStream(texturePath))
        private set

    override fun draw(rectangle: Rectangle) {
        backgroundColor?.draw(rectangle)
        val textureWidth = texture.width
        val textureHeight = texture.height
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        bindingColor?.bind()
        texture.bind()
        GL11.glBegin(GL11.GL_POLYGON)
        GL11.glTexCoord2f(0f, 0f)
        GL11.glVertex2f(rectangle.absoluteX, rectangle.absoluteY)
        GL11.glTexCoord2f(0f, textureHeight)
        GL11.glVertex2f(rectangle.absoluteX, rectangle.absoluteY + rectangle.height)
        GL11.glTexCoord2f(textureWidth, textureHeight)
        GL11.glVertex2f(rectangle.absoluteX + rectangle.width, rectangle.absoluteY + rectangle.height)
        GL11.glTexCoord2f(textureWidth, 0f)
        GL11.glVertex2f(rectangle.absoluteX + rectangle.width, rectangle.absoluteY)
        GL11.glEnd()
        GL11.glDisable(GL11.GL_TEXTURE_2D)
    }
}
