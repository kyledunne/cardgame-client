package old

import java.util.LinkedList

/**
 * Created by Kyle on 4/20/2016.
 */
class LayeredContainer(parent: Container?, style: Style?, layoutManager: LayoutManager) : Container(parent, style, layoutManager) {
    private val layers: MutableCollection<Rectangle> = LinkedList()

    override fun draw() {
        super.draw()
        for (layer in layers) {
            layer.draw()
        }
    }

    fun addLayer(layer: Rectangle) {
        layers.add(layer)
        layer.x = x
        layer.y = y
        layer.width = width
        layer.height = height

    }

    fun removeLayer(layer: Rectangle) {
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
                layer.absoluteX = absoluteX
            }
        }

    override var y: Float
        get() = super.y
        set(y) {
            super.y = y
            for (layer in layers) {
                layer.absoluteY = absoluteY
            }
        }

    override var width: Float
        get() = super.width
        set(width) {
            super.width = width
            for (layer in layers) {
                layer.width = width
            }
        }

    override var height: Float
        get() = super.height
        set(height) {
            super.height = height
            for (layer in layers) {
                layer.height = height
            }
        }

    override var absoluteX: Float
        get() = super.absoluteX
        set(absoluteX) {
            super.absoluteX = absoluteX
            for (layer in layers) {
                layer.absoluteX = absoluteX
            }
        }

    override var absoluteY: Float
        get() = super.absoluteY
        set(absoluteY) {
            super.absoluteY = absoluteY
            for (layer in layers) {
                layer.absoluteY = absoluteY
            }
        }
}
