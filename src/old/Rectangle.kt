package old

import gui.GUIMain

/**
 * Created by Kyle on 4/20/2016.
 */
open class Rectangle(var parent: Container?, var style: Style?) {
    open var x = 0f
        set(x) {
            field = x
            this.absoluteX = x + parent!!.absoluteX
        }
    open var y = 0f
        set(y) {
            field = y
            this.absoluteY = y + parent!!.absoluteY
        }
    open var width = 0f
    open var height = 0f
    open var absoluteX = 0f
    open var absoluteY = 0f
    open var active: Boolean = if (parent != null) parent!!.active else false

    open fun draw() {
        style?.draw(this)
    }

    fun initializeWindow() {
        width = GUIMain.getWindowWidth().toFloat()
        height = GUIMain.getWindowHeight().toFloat()
    }

    companion object {

        fun getBounds(rectangle: Rectangle): FloatArray {
            val bounds = FloatArray(4)
            bounds[0] = rectangle.absoluteX
            bounds[1] = rectangle.absoluteY
            bounds[2] = rectangle.width
            bounds[3] = rectangle.height
            return bounds
        }
    }
}
