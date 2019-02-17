package old.layoutManagers

import old.LayoutConstraints
import old.LayoutManager
import old.Rectangle

import java.util.LinkedList

/**
 * Created by Kyle on 5/22/2016.
 */
class CoordinatesLayout : LayoutManager() {
    private val components = LinkedList<Rectangle>()

    override fun add(component: Rectangle, constraints: LayoutConstraints) {
        components.add(component)
        val c = constraints as Constraints
        component.x = c.x
        component.y = c.y
        component.width = c.width
        component.height = c.height
    }

    override fun remove(component: Rectangle) {
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
            component.absoluteX = component.x + client!!.absoluteX
        }
    }

    override fun clientYChanged() {
        for (component in components) {
            component.absoluteY = component.y + client!!.absoluteY
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

    class Constraints(val x: Float, val y: Float, val width: Float, val height: Float) : LayoutConstraints
}
