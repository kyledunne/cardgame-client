package kot2d

import java.util.*

open class Rect(var parent: Container?, var style: Style?) {
    open var x = 0f
        set(x) {
            field = x
            abx = x + parent!!.abx
        }
    open var y = 0f
        set(y) {
            field = y
            aby = y + parent!!.aby
        }
    open var w = 0f
    open var h = 0f
    open var abx = 0f
    open var aby = 0f
    open var active: Boolean = if (parent != null) parent!!.active else false

    open fun draw() {
        style?.draw(this)
    }

    companion object {

        fun getBounds(rect: Rect): FloatArray {
            val bounds = FloatArray(4)
            bounds[0] = rect.abx
            bounds[1] = rect.aby
            bounds[2] = rect.w
            bounds[3] = rect.h
            return bounds
        }
    }
}

open class Container(parent: Container?, style: Style?, val layoutManager: LayoutManager) : Rect(parent, style) {
    init {
        layoutManager.client = this
    }

    override fun draw() {
        super.draw()
        layoutManager.drawComponents()
    }

    fun addComponent(component: Rect, constraints: LayoutConstraints) {
        component.parent = this
        if (active) {
            component.active = true
        }
        layoutManager.add(component, constraints)
    }

    fun removeComponent(component: Rect) {
        component.active = false
        component.parent = null
        layoutManager.remove(component)
    }

    open fun empty() {
        layoutManager.clear()
    }

    override var x: Float
        get() = super.x
        set(x) {
            super.x = x
            layoutManager.clientXChanged()
        }

    override var y: Float
        get() = super.y
        set(y) {
            super.y = y
            layoutManager.clientYChanged()
        }

    override var w: Float
        get() = super.w
        set(w) {
            super.w = w
            layoutManager.clientWidthChanged()
        }

    override var h: Float
        get() = super.h
        set(h) {
            super.h = h
            layoutManager.clientHeightChanged()
        }

    override var abx: Float
        get() = super.abx
        set(abx) {
            super.abx = abx
            layoutManager.clientXChanged()
        }

    override var aby: Float
        get() = super.aby
        set(aby) {
            super.aby = aby
            layoutManager.clientYChanged()
        }

    override var active: Boolean
        get() = super.active
        set(active) {
            super.active = active
            if (active) {
                layoutManager.activateComponents()
            } else {
                layoutManager.deactivateComponents()
            }
        }
}

class LayeredContainer(parent: Container?, style: Style?, layoutManager: LayoutManager) : Container(parent, style, layoutManager) {
    private val layers: MutableCollection<Rect> = LinkedList()

    override fun draw() {
        super.draw()
        for (layer in layers) {
            layer.draw()
        }
    }

    fun addLayer(layer: Rect) {
        layers.add(layer)
        layer.x = x
        layer.y = y
        layer.w = w
        layer.h = h

    }

    fun removeLayer(layer: Rect) {
        layers.remove(layer)
    }

    override fun empty() {
        super.empty()
        for (layer in layers) {
            (layer as? Container)?.empty()
        }
        layers.clear()
    }

    override var x: Float
        get() = super.x
        set(x) {
            super.x = x
            for (layer in layers) {
                layer.abx = abx
            }
        }

    override var y: Float
        get() = super.y
        set(y) {
            super.y = y
            for (layer in layers) {
                layer.aby = aby
            }
        }

    override var w: Float
        get() = super.w
        set(w) {
            super.w = w
            for (layer in layers) {
                layer.w = w
            }
        }

    override var h: Float
        get() = super.h
        set(h) {
            super.h = h
            for (layer in layers) {
                layer.h = h
            }
        }

    override var abx: Float
        get() = super.abx
        set(abx) {
            super.abx = abx
            for (layer in layers) {
                layer.abx = abx
            }
        }

    override var aby: Float
        get() = super.aby
        set(aby) {
            super.aby = aby
            for (layer in layers) {
                layer.aby = aby
            }
        }
}

abstract class LayoutManager {
    var client: Container? = null

    abstract fun add(component: Rect, constraints: LayoutConstraints)
    abstract fun remove(component: Rect)

    /** Unlike remove(), this method must call deactivate()
     * and setParent(null) on each component being removed  */
    abstract fun clear()

    abstract fun clientWidthChanged()
    abstract fun clientHeightChanged()
    abstract fun clientXChanged()
    abstract fun clientYChanged()
    abstract fun drawComponents()
    abstract fun activateComponents()
    abstract fun deactivateComponents()

    fun finalizePlaceholder() {}
}

interface LayoutConstraints