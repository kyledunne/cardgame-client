package kot2d

import gui.GUIMain
import gui.Input
import old.Color
import old.LayeredContainer
import old.layoutManagers.CoordinatesLayout
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.Display
import org.lwjgl.opengl.GL11

lateinit var exitCondition: () -> Boolean
lateinit var checkEvents: () -> Unit

fun init() {
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
}

fun loop(checkEvents: () -> Unit,
         exitCondition: () -> Boolean = { Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) }) {
    kot2d.exitCondition = exitCondition
    kot2d.checkEvents = checkEvents

    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)
    GUIMain.WINDOW.draw()
    Display.update()

    while (!kot2d.exitCondition()) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)
        Input.checkInputs()
        kot2d.checkEvents()
        GUIMain.WINDOW.draw()
        Display.sync(60)
        Display.update()
    }
}