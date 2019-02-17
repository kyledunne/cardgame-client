package old

/**
 * Created by Kyle on 4/30/2016.
 */
abstract class ClickableContainer(parent: Container?, style: Style?, layoutManager: LayoutManager) : Container(parent, style, layoutManager), Clickable {
    override var listener = ClickableListener(this)
    override var bounds = getBounds(this)

    override var active: Boolean
        get() = super.active
        set(active) {
            super.active = active
            if (active) {
                listener.addMouseListener()
            } else {
                listener.removeMouseListener()
            }
        }
}
