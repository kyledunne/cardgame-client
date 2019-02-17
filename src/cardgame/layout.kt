package cardgame

import java.util.*

interface LayoutConstraints

abstract class LayoutManager {
    var x: Float? = null
    var y: Float? = null
    var w: Float? = null
    var h: Float? = null

    abstract fun drawComponents(client: Rect)
    abstract fun add(component: Rect, constraints: LayoutConstraints)
    abstract fun remove(component: Rect)
    abstract fun clear()
    abstract fun setActive(active: Boolean)
}

class GridLayout private constructor(val rows: Int, val columns: Int, val initVal: Int,
                                     val leftBorder: Float = 0f,
                                     val rightBorder: Float = 0f,
                                     val topBorder: Float = 0f,
                                     val bottomBorder: Float = 0f,
                                     var hGap: Float = 0f,
                                     var vGap: Float = 0f,
                                     var cellWidth: Float = 0f,
                                     var cellHeight: Float = 0f,
                                     val cellAspect: Float = 0f): LayoutManager() {
    var components: Array<Rect?> = arrayOfNulls(columns * rows)

    private fun finalize(client: Rect) {
        x = client.x
        y = client.y
        w = client.w
        h = client.h
        when (initVal) {
            1 -> finalize1()
            2 -> finalize2()
            3 -> finalize3()
            4 -> finalize4()
        }
        var row = 0
        var column = 0
        for (component in components) {
            if (component != null) {
                component.x = x!! + leftBorder + cellWidth * column + hGap * column
                component.y = y!! + topBorder + cellHeight * row + vGap * row
                component.w = cellWidth
                component.h = cellHeight
            }
            if (column == columns - 1) {
                column = 0
                row++
            } else {
                column++
            }
        }
    }

    private fun finalize1() {
        cellWidth = ((w!! - leftBorder - rightBorder) - (hGap * (columns - 1))) / columns
        cellHeight = ((h!! - topBorder - bottomBorder) - (vGap * (rows-1))) / rows
    }

    private fun finalize2() {
        TODO()
    }

    private fun finalize3() {
        TODO()
    }

    private fun finalize4() {
        TODO()
    }

    override fun drawComponents(client: Rect) {
        if (x == null) {
            finalize(client)
        } else if (x != client.x || y != client.y || w != client.w || h != client.h) {
            if (w == client.w && h == client.h) {
                val dx = client.x - x!!
                val dy = client.y - y!!
                for (component in components) {
                    if (component != null) {
                        component.x += dx
                        component.y += dy
                    }
                }
            } else {
                finalize(client)
            }
        }
        for (component in components) {
            component?.draw()
        }
    }

    override fun add(component: Rect, constraints: LayoutConstraints) {
        if (constraints !is Constraints) error("Constraints of wrong type")
        components[constraints.col + constraints.row * columns] = component
    }

    override fun remove(component: Rect) {
        val index = components.indexOf(component)
        if (index == -1) error("Tried to remove a component that was not in this.components")
        components[index] = null
    }

    override fun clear() {
        for (component in components) component?.active = false
        components = arrayOfNulls(rows * columns)
    }

    override fun setActive(active: Boolean) {
        for (component in components) component?.active = active
    }

    companion object {
        fun new1(rows: Int, columns: Int, leftBorder: Float, rightBorder: Float, topBorder: Float,
                 bottomBorder: Float, horizontalGap: Float, verticalGap: Float): GridLayout {
            return GridLayout(rows=rows, columns=columns, initVal=1, leftBorder=leftBorder,
                    rightBorder=rightBorder, topBorder=topBorder, bottomBorder=bottomBorder,
                    hGap=horizontalGap, vGap=verticalGap)
        }

        fun new2(rows: Int, columns: Int, leftBorder: Float, rightBorder: Float, topBorder: Float,
                 bottomBorder: Float, cellWidth: Float, cellHeight: Float): GridLayout {
            return GridLayout(rows=rows, columns=columns, initVal=2, leftBorder=leftBorder,
                    rightBorder=rightBorder, topBorder=topBorder, bottomBorder=bottomBorder,
                    cellWidth=cellWidth, cellHeight=cellHeight)
        }

        fun new3(rows: Int, columns: Int, leftBorder: Float, rightBorder: Float, topBorder: Float,
                 horizontalGap: Float, verticalGap: Float, cellAspectRatio: Float): GridLayout {
            return GridLayout(rows=rows, columns=columns, initVal=3, leftBorder=leftBorder,
                    rightBorder=rightBorder, topBorder=topBorder, hGap=horizontalGap,
                    vGap=verticalGap, cellAspect=cellAspectRatio)
        }

        fun new4(rows: Int, columns: Int, leftBorder: Float, topBorder: Float, horizontalGap: Float,
                 verticalGap: Float, cellWidth: Float, cellAspectRatio: Float): GridLayout {
            return GridLayout(rows=rows, columns=columns, initVal=4, leftBorder=leftBorder,
                    topBorder=topBorder, hGap=horizontalGap, vGap=verticalGap, cellWidth=cellWidth,
                    cellAspect=cellAspectRatio)
        }
    }

    class Constraints(var row: Int, var col: Int): LayoutConstraints
}

class CoordinatesLayout(): LayoutManager() {
    var components: MutableList<Rect> = LinkedList()

    override fun drawComponents(client: Rect) {
        if (x != client.x || y != client.y) {
            x = client.x
            y = client.y
            for (component in components) {
                component.x += x!!
                component.y += y!!
            }
        }
        for (component in components) {
            component.draw()
        }
    }

    override fun add(component: Rect, constraints: LayoutConstraints) {
        if (constraints !is Constraints) error("Constraints of wrong type")
        component.x = constraints.x
        component.y = constraints.y
        component.w = constraints.w
        component.h = constraints.h
        components.add(component)
    }

    override fun remove(component: Rect) {
        val index = components.indexOf(component)
        if (index == -1) error("Tried to remove a component that was not in this.components")
        components.removeAt(index)
    }

    override fun clear() {
        for (component in components) component.active = false
        components.clear()
    }

    override fun setActive(active: Boolean) {
        for (component in components) component.active = active
    }

    class Constraints(val x: Float, val y: Float, val w: Float, val h: Float): LayoutConstraints
}
