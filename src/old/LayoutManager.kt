package old

/**
 * Created by Kyle on 4/20/2016.
 */
abstract class LayoutManager {
    var client: Container? = null

    abstract fun add(component: Rectangle, constraints: LayoutConstraints)
    abstract fun remove(component: Rectangle)

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
