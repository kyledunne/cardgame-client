package old.layoutManagers

import old.LayoutConstraints
import old.LayoutManager
import old.Rectangle

/**
 * Created by Kyle on 4/20/2016.
 */
class GridLayout private constructor() : LayoutManager() {
    lateinit var components: Array<Array<Rectangle?>>
    var rows = 0
    var columns = 0
    var leftBorder = 0f
    var rightBorder = 0f
    var topBorder = 0f
    var bottomBorder = 0f
    var horizontalGap = 0f
    var verticalGap = 0f
    var cellWidth = 0f
    var cellHeight = 0f
    var layoutFinalized = false
    var usesCellAspectRatio = false
    var cellAspectRatio = 0f

    companion object {

        fun new1(rows: Int, columns: Int, leftBorder: Float, rightBorder: Float,
                 topBorder: Float, bottomBorder: Float, horizontalGap: Float, verticalGap: Float): GridLayout {
            val g = GridLayout()
            g.components = Array(rows) { arrayOfNulls<Rectangle?>(columns) }
            g.rows = rows
            g.columns = columns
            g.leftBorder = leftBorder
            g.rightBorder = rightBorder
            g.topBorder = topBorder
            g.bottomBorder = bottomBorder
            g.horizontalGap = horizontalGap
            g.verticalGap = verticalGap
            return g
        }

        fun new2(rows: Int, columns: Int, leftBorder: Float, rightBorder: Float,
                 topBorder: Float, bottomBorder: Float, cellWidth: Float, cellHeight: Float): GridLayout {
            val g = GridLayout()
            g.components = Array(rows) { arrayOfNulls<Rectangle?>(columns) }
            g.rows = rows
            g.columns = columns
            g.leftBorder = leftBorder
            g.rightBorder = rightBorder
            g.topBorder = topBorder
            g.bottomBorder = bottomBorder
            g.cellWidth = cellWidth
            g.cellHeight = cellHeight
            return g
        }

        fun new3(rows: Int, columns: Int, leftBorder: Float, rightBorder: Float,
                 topBorder: Float, horizontalGap: Float, verticalGap: Float, cellAspectRatio: Float): GridLayout {
            val g = GridLayout()
            g.components = Array(rows) { arrayOfNulls<Rectangle?>(columns) }
            g.rows = rows
            g.columns = columns
            g.leftBorder = leftBorder
            g.rightBorder = rightBorder
            g.topBorder = topBorder
            g.horizontalGap = horizontalGap
            g.verticalGap = verticalGap
            g.usesCellAspectRatio = true
            g.cellAspectRatio = cellAspectRatio
            return g
        }

        fun new4(rows: Int, columns: Int, leftBorder: Float, topBorder: Float,
                 horizontalGap: Float, verticalGap: Float, cellWidth: Float, cellAspectRatio: Float): GridLayout {
            val g = GridLayout()
            g.components = Array(rows) { arrayOfNulls<Rectangle?>(columns) }
            g.rows = rows
            g.columns = columns
            g.leftBorder = leftBorder
            g.topBorder = topBorder
            g.horizontalGap = horizontalGap
            g.verticalGap = verticalGap
            g.cellWidth = cellWidth
            g.usesCellAspectRatio = true
            g.cellAspectRatio = cellAspectRatio
            return g
        }
    }

    /** call after the client's parent has been set (and the coordinates of the parent have been set)  */
    fun finalize1() {
        layoutFinalized = true
        cellWidth = client!!.width
        cellWidth = cellWidth - rightBorder - leftBorder
        var emptySpace = Math.max(0, columns - 1) * horizontalGap
        cellWidth -= emptySpace
        cellWidth /= columns

        cellHeight = client!!.height
        cellHeight = cellHeight - bottomBorder - topBorder
        emptySpace = Math.max(0, rows - 1) * verticalGap
        cellHeight -= emptySpace
        cellHeight /= rows
    }

    /** call after the client's parent has been set (and the coordinates of the parent have been set)  */
    fun finalize2() {
        layoutFinalized = true
        val emptyHorizontalSpace = client!!.width - leftBorder - rightBorder - cellWidth * columns
        horizontalGap = emptyHorizontalSpace / Math.max(1, columns - 1)
        val emptyVerticalSpace = client!!.height - topBorder - bottomBorder - cellHeight * rows
        verticalGap = emptyVerticalSpace / Math.max(1, rows - 1)
    }

    /** call after the client's parent has been set (and the coordinates of the parent have been set)  */
    fun finalize3() {
        layoutFinalized = true
        cellWidth = client!!.width
        cellWidth = cellWidth - rightBorder - leftBorder
        val emptySpace = Math.max(0, columns - 1) * horizontalGap
        cellWidth -= emptySpace
        cellWidth /= columns
        cellHeight = cellWidth * cellAspectRatio
        //set bottomBorder if needed
    }

    /** call after the client's parent has been set (and the coordinates of the parent have been set)  */
    fun finalize4() {
        layoutFinalized = true
        cellHeight = cellWidth * cellAspectRatio
        //set rightBorder and bottomBorder if needed
    }

    override fun add(component: Rectangle, constraints: LayoutConstraints) {
        if (!layoutFinalized) {
            throw RuntimeException("this instance of GridLayout has not been finalized")
        }
        val c = constraints as Constraints
        components[c.row][c.column] = component
        val x = leftBorder + cellWidth * c.column + horizontalGap * c.column
        val y = topBorder + cellHeight * c.row + verticalGap * c.row
        component.x = x
        component.y = y
        component.width = cellWidth
        component.height = cellHeight
    }

    override fun remove(component: Rectangle) {
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                if (component == components[i][j]) {
                    components[i][j] = null
                }
            }
        }
    }

    override fun clear() {
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                if (components[i][j] != null) {
                    components[i][j]!!.active = false
                    components[i][j]!!.parent = null
                    components[i][j] = null
                }

            }
        }
    }

    override fun clientWidthChanged() {
        println("kot2d.GridLayout does not yet support width changes")
    }

    override fun clientHeightChanged() {
        println("kot2d.GridLayout does not yet support height changes")
    }

    override fun clientXChanged() {
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                components[i][j]?.absoluteX = components[i][j]!!.x + client!!.absoluteX
            }
        }
    }

    override fun clientYChanged() {
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                components[i][j]?.absoluteY = components[i][j]!!.y + client!!.absoluteY
            }
        }
    }

    override fun drawComponents() {
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                components[i][j]?.draw()
            }
        }
    }

    override fun activateComponents() {
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                components[i][j]?.active = true
            }
        }
    }

    override fun deactivateComponents() {
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                components[i][j]?.active = false
            }
        }
    }

    class Constraints(val row: Int, val column: Int) : LayoutConstraints
}
