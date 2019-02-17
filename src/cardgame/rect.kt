package cardgame

import java.util.LinkedList

interface RectListener

class Rect(var style: Style? = null, var layoutManager: LayoutManager? = null) {
    var x = 0f
    var y = 0f
    var w = 0f
    var h = 0f
    var active: Boolean = false
        set(active) {
            if (active != field) {
                field = active
                layoutManager?.setActive(active)
            }
        }

    var layers: MutableList<Rect> = LinkedList()
    var listener: RectListener? = null

    fun draw() {
        style?.draw(this)
        layoutManager?.drawComponents(this)
        for (layer in layers) {
            layer.draw()
        }
    }

    fun add(component: Rect, constraints: LayoutConstraints) {
        component.active = active
        layoutManager!!.add(component, constraints)
    }

    fun remove(component: Rect) {
        component.active = false
        layoutManager!!.remove(component)
    }

    fun empty() {
        layoutManager!!.clear()
    }
}