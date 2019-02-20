package kot2d

import java.util.LinkedList

class CoordinatesLayout : LayoutManager() {
    private val components = LinkedList<Rect>()

    override fun add(component: Rect, constraints: LayoutConstraints) {
        components.add(component)
        val c = constraints as Constraints
        component.x = c.x
        component.y = c.y
        component.w = c.w
        component.h = c.h
    }

    override fun remove(component: Rect) {
        components.remove(component)
    }

    override fun clear() {
        components.clear()
    }

    override fun clientWidthChanged() {
        //do nothing
    }

    override fun clientHeightChanged() {
        //do nothing
    }

    override fun clientXChanged() {
        for (component in components) {
            component.abx = component.x + client!!.abx
        }
    }

    override fun clientYChanged() {
        for (component in components) {
            component.aby = component.y + client!!.aby
        }
    }

    override fun drawComponents() {
        for (component in components) {
            component.draw()
        }
    }

    override fun activateComponents() {
        for (component in components) {
            component.active = true
        }
    }

    override fun deactivateComponents() {
        for (component in components) {
            component.active = false
        }
    }

    class Constraints(val x: Float, val y: Float, val w: Float, val h: Float) : LayoutConstraints
}