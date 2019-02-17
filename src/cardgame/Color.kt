package cardgame

import org.lwjgl.opengl.GL11

class Color(var r: Float, var g: Float, var b: Float, var a: Float): Style {
    override fun draw(rect: Rect) {
        if (this.a > 0) {
            glColor4f(this)
            GL11.glBegin(GL11.GL_POLYGON)
            GL11.glVertex2f(rect.x, rect.y)
            GL11.glVertex2f(rect.x + rect.w, rect.y)
            GL11.glVertex2f(rect.x + rect.w, rect.y + rect.h)
            GL11.glVertex2f(rect.x, rect.y + rect.h)
            GL11.glEnd()
        }
    }

    /** MAKES THE TEXTURE GO ALL THIS COLOR AND OOH ITS COOL  */
    fun bind() {
        org.newdawn.slick.Color(r, g, b).bind()
    }

    fun adjust(r: Float, g: Float, b: Float) {
        this.r += r
        this.g += g
        this.b += b
    }

    fun adjust(r: Float, g: Float, b: Float, a: Float) {
        this.r += r
        this.g += g
        this.b += b
        this.b += a
    }

    fun adjust(a: Float) {
        this.a += a
    }

    fun invert() {
        this.r = 1 - r
        this.g = 1 - g
        this.b = 1 - b
    }

    companion object {
        val BLACK = Color(0f, 0f, 0f, 1f)
        val WHITE = Color(1f, 1f, 1f, 1f)
        val TRANSPARENT_BLACK = Color(0f, 0f, 0f, 0f)
        val TRANSPARENT = TRANSPARENT_BLACK
        val TRANSPARENT_WHITE = Color(1f, 1f, 1f, 0f)
        val RED = Color(1f, 0f, 0f, 1f)
        val GREEN = Color(0f, 1f, 0f, 1f)
        val DARK_GREEN = Color(.0f, .5f, .0f, 1f)
        val BLUE = Color(0f, 0f, 1f, 1f)
        val LIGHT_PURPLE = Color(1f, 0f, 1f, 1f)
        val CYAN = Color(0f, 1f, 1f, 1f)
        val YELLOW = Color(1f, 1f, 0f, 1f)
        val ORANGE = Color(1f, 165 / 255f, 0f, 1f)
        val DARK_ORANGE = Color(1f, 200 / 255f, 35f, 1f)
        val DARK_GREY = Color(50/255f, 50/255f, 50/255f, 1f)
        val DARK_BLUE = Color(12/255f, 0f, 200/255f, 1f)
        val GRAY = Color(.7f, .7f, .7f, 1f)
        val BROWN = Color(139/255f, 90/255f, 0f, 1f)
        val CARROT = Color(237/255f, 145/255f, 33/255f, 1f)
        val GREEN_GRASS = Color(.3f, .9f, .3f, 1f)
        val GREEN_GRASS_ALT = Color(.2f, 1f, .2f, 1f)

        fun adjustAColorsAlpha(color: Color, newAlpha: Float): Color {
            return Color(color.r, color.g, color.b, newAlpha)
        }

        fun adjustAColor(color: Color, lightnessAdjustment: Float): Color {
            return Color(color.r + lightnessAdjustment, color.g + lightnessAdjustment, color.b + lightnessAdjustment, color.a)
        }

        fun adjustAColor(color: Color, rAdjustment: Float, gAdjustment: Float, bAdjustment: Float, aAdjustment: Float = 0f): Color {
            return Color(color.r + rAdjustment, color.g + gAdjustment, color.b + bAdjustment, color.a + aAdjustment)
        }

        fun glClearColor(color: Color) {
            GL11.glClearColor(color.r, color.g, color.b, color.a)
        }

        fun glColor4f(color: Color) {
            GL11.glColor4f(color.r, color.g, color.b, color.a)
        }

        fun randomColor(): Color {
            return Color(Util.RAND.nextFloat(), Util.RAND.nextFloat(), Util.RAND.nextFloat(), 1f)
        }

        fun randomNonOpaqueColor(): Color {
            return Color(Util.RAND.nextFloat(), Util.RAND.nextFloat(), Util.RAND.nextFloat(), Util.RAND.nextFloat())
        }

        fun invertedColor(color: Color): Color {
            return Color(1 - color.r, 1 - color.g, 1 - color.b, color.a)
        }

        fun isCloserToBlack(color: Color): Boolean {
            return color.r + color.g + color.b < 1.5
        }
    }
}