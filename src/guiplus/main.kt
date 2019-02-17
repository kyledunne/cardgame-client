package guiplus

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
        val forestBackgroundTextures = arrayOf(toTexture("res/img/forest_background/animated_forest_frame_0.png"),
                toTexture("res/img/forest_background/animated_forest_frame_1.png"),
                toTexture("res/img/forest_background/animated_forest_frame_2.png"),
                toTexture("res/img/forest_background/animated_forest_frame_3.png"),
                toTexture("res/img/forest_background/animated_forest_frame_4.png"),
                toTexture("res/img/forest_background/animated_forest_frame_5.png"))
        // /end init

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)
        //@insert render
        Display.update()
        Input.init()

        while (!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)
            Input.update()
            //@insert simulate
            //@insert render
            Display.sync(60)
            Display.update()
        }
    }
}
