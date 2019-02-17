package engine

import old.MouseListener
import org.lwjgl.input.Mouse
import java.util.LinkedList

/**
 * Created by Kyle on 4/29/2016.
 */
object Input {
    var mouseX = 0
    var mouseY = 0
    var lastFrameMouseX = 0
    var lastFrameMouseY = 0
    var isLeftMouseButtonDown = false
    var wasLeftMouseButtonDown = false

    private val mouseListeners = LinkedList<MouseListener>()

    fun checkInputs() {
        lastFrameMouseX = mouseX
        lastFrameMouseY = mouseY
        mouseX = Mouse.getX()
        mouseY = GUIMain.getWindowHeight() - Mouse.getY()
        wasLeftMouseButtonDown = isLeftMouseButtonDown
        isLeftMouseButtonDown = Mouse.isButtonDown(0)
        val mouseMoved = lastFrameMouseX != mouseX || lastFrameMouseY != mouseY
        val mousePressed = !wasLeftMouseButtonDown && isLeftMouseButtonDown
        val mouseReleased = wasLeftMouseButtonDown && !isLeftMouseButtonDown
        if (mouseMoved) {
            for (listener in mouseListeners) {
                listener.mouseMoved()
            }
        }
        if (mousePressed) {
            for (listener in mouseListeners) {
                listener.mousePressed()
            }
        }
        if (mouseReleased) {
            for (listener in mouseListeners) {
                listener.mouseReleased()
            }
        }
    }

    fun addMouseListener(listener: MouseListener) {
        mouseListeners.add(listener)
    }

    fun removeMouseListener(listener: MouseListener) {
        mouseListeners.remove(listener)
    }
}
