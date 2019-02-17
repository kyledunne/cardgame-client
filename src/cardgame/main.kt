package cardgame

import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.Display
import org.lwjgl.opengl.GL11

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        Display.setFullscreen(true)
        Display.create()

        GL11.glMatrixMode(GL11.GL_PROJECTION)
        GL11.glLoadIdentity()
        GL11.glOrtho(0.0, Display.getWidth().toDouble(), Display.getHeight().toDouble(), 0.0, 0.0, 1.0)
        GL11.glMatrixMode(GL11.GL_MODELVIEW)
        GL11.glLoadIdentity()
        GL11.glTranslatef(.375f, .375f, 0f)
        GL11.glDisable(GL11.GL_DEPTH_TEST)
        Color.glClearColor(Color(25/255f, 23/255f, 199/255f, 1f))
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)
        GL11.glEnable(GL11.GL_BLEND)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
        GL11.glShadeModel(GL11.GL_SMOOTH)
        GL11.glDisable(GL11.GL_LIGHTING)
        GL11.glClearDepth(1.0)

        // init
        var window = Rect(null, GridLayout.new1(2, 5, 100f, 50f,
                10f, 300f, 100f, 50f))
        window.w = Display.getWidth().toFloat()
        window.h = Display.getHeight().toFloat()

        var greenRect = Rect(Color.GREEN, null)
        window.add(greenRect, GridLayout.Constraints(0, 1))

        var purpleRect = Rect(Color.LIGHT_PURPLE, null)
        window.add(purpleRect, GridLayout.Constraints(1, 4))
        // /end init

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)
        window.draw()
        Display.update()

        while (!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)
            //input
            window.draw()
            Display.sync(60)
            Display.update()
        }
    }
}
