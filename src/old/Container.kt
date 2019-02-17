package old

/**
 * Created by Kyle on 4/20/2016.
 */
open class Container(parent: Container?, style: Style?, val layoutManager: LayoutManager) : Rectangle(parent, style) {
    init {
        layoutManager.client = this
    }

    override fun draw() {
        super.draw()
        layoutManager.drawComponents()
    }

    fun addComponent(component: Rectangle, constraints: LayoutConstraints) {
        component.parent = this
        if (active) {
            component.active = true
        }
        layoutManager.add(component, constraints)
    }

    fun removeComponent(component: Rectangle) {
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

    override var width: Float
        get() = super.width
        set(width) {
            super.width = width
            layoutManager.clientWidthChanged()
        }

    override var height: Float
        get() = super.height
        set(height) {
            super.height = height
            layoutManager.clientHeightChanged()
        }

    override var absoluteX: Float
        get() = super.absoluteX
        set(absoluteX) {
            super.absoluteX = absoluteX
            layoutManager.clientXChanged()
        }

    override var absoluteY: Float
        get() = super.absoluteY
        set(absoluteY) {
            super.absoluteY = absoluteY
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
