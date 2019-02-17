package engine

open class Rect {
    var x = 0f
    var y = 0f
    var width = 0f
    var height = 0f
}

abstract class Style {
    abstract fun draw(rect: Rect)
}

abstract class LayoutManager {
    abstract fun draw(rect: Rect)
}