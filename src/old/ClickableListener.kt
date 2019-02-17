package old

import gui.Input

/**
 * Created by Kyle on 4/30/2016.
 */
class ClickableListener(client: Clickable) {
    private val mouseListener: MouseListener
    private var x = 0f
    private var y = 0f
    private var rightX = 0f
    private var bottomY = 0f
    private var mouseLastPressedWithinBounds = false
    private var mousedOver = false

    init {
        mouseListener = object : MouseListener() {
            override fun mousePressed() {
                val bounds = client.bounds
                x = bounds[0]
                y = bounds[1]
                rightX = x + bounds[2]
                bottomY = y + bounds[3]
                val mouseX = Input.mouseX
                val mouseY = Input.mouseY
                if (x < mouseX && mouseX < rightX && y < mouseY && mouseY < bottomY) {
                    mouseLastPressedWithinBounds = true
                    client.clicked()
                } else {
                    mouseLastPressedWithinBounds = false
                }
            }

            override fun mouseReleased() {
                val bounds = client.bounds
                x = bounds[0]
                y = bounds[1]
                rightX = x + bounds[2]
                bottomY = y + bounds[3]
                val mouseX = Input.mouseX
                val mouseY = Input.mouseY
                if (mouseLastPressedWithinBounds) {
                    if (x < mouseX && mouseX < rightX && y < mouseY && mouseY < bottomY) {
                        client.released()
                    }
                }
            }

            override fun mouseMoved() {
                val bounds = client.bounds
                x = bounds[0]
                y = bounds[1]
                rightX = x + bounds[2]
                bottomY = y + bounds[3]
                val mouseX = Input.mouseX
                val mouseY = Input.mouseY
                if (x < mouseX && mouseX < rightX && y < mouseY && mouseY < bottomY) {
                    if (!mousedOver) {
                        mousedOver = true
                        client.mousedOver()
                    }
                } else if (mousedOver) {
                    mousedOver = false
                    client.unMousedOver()
                }
            }
        }
    }

    fun addMouseListener() {
        Input.addMouseListener(mouseListener)
    }

    fun removeMouseListener() {
        Input.removeMouseListener(mouseListener)
    }
}
