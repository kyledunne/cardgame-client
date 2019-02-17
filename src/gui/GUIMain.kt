package gui

import old.Color
import old.LayeredContainer
import old.layoutManagers.CoordinatesLayout
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.Display
import org.lwjgl.opengl.GL11

/**
 * Created by Kyle on 5/19/2016.
 */
object GUIMain {
    lateinit var WINDOW: LayeredContainer

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
        Color.glClearColor(Color(25, 23, 199))
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)
        GL11.glEnable(GL11.GL_BLEND)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
        GL11.glShadeModel(GL11.GL_SMOOTH)
        GL11.glDisable(GL11.GL_LIGHTING)
        GL11.glClearDepth(1.0)

        init()
        WINDOW.active = true

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)
        WINDOW.draw()
        Display.update()

        while (!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)
            checkEvents()
            render()
            Display.sync(60)
            Display.update()
        }

        println("Escape pressed")
    }

    private fun init() {
        WINDOW = LayeredContainer(null, Color.TRANSPARENT, CoordinatesLayout())
        WINDOW.initializeWindow()
        main.Main.init()
    }

    /** SUP  */
    private fun checkEvents() {
        main.Main.checkEvents()
    }

    private fun render() {
        main.Main.render()
    }

    fun getWindowWidth() = Display.getWidth()

    fun getWindowHeight() = Display.getHeight()
}
