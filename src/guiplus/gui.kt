package guiplus

import org.lwjgl.input.Mouse
import org.lwjgl.opengl.Display

class GuiContext {
    var active: Int = -1
    var hot: Int = -1
}

class Rect(var x: Float, var y: Float, var width: Float, var height: Float)

interface Style {
    fun draw(rect: Rect)
}

fun draw(rect: Rect, style: Style) {
    style.draw(rect)
}

object Input {
    var mouseX = 0
    var mouseY = 0
    var lastFrameMouseX = 0
    var lastFrameMouseY = 0
    var isLeftMouseButtonDown = false
    var wasLeftMouseButtonDown = false
    var mouseMoved = false
    var leftMouseButtonPressed = false
    var leftMouseButtonReleased = false

    fun init() {
        mouseX = Mouse.getX()
        mouseY = Display.getHeight() - Mouse.getY()
        isLeftMouseButtonDown = Mouse.isButtonDown(0)
        lastFrameMouseX = mouseX
        lastFrameMouseY = mouseY
        wasLeftMouseButtonDown = isLeftMouseButtonDown
    }

    fun update() {
        lastFrameMouseX = mouseX
        lastFrameMouseY = mouseY
        wasLeftMouseButtonDown = isLeftMouseButtonDown
        mouseX = Mouse.getX()
        mouseY = Display.getHeight() - Mouse.getY()
        isLeftMouseButtonDown = Mouse.isButtonDown(0)

        mouseMoved = lastFrameMouseX != mouseX || lastFrameMouseY != mouseY
        leftMouseButtonPressed = !wasLeftMouseButtonDown && isLeftMouseButtonDown
        leftMouseButtonReleased = wasLeftMouseButtonDown && !isLeftMouseButtonDown
    }
}
