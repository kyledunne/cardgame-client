package old

/**
 * Created by Kyle on 4/30/2016.
 */
abstract class ClickableRectangle(parent: Container?, style: Style?) : Rectangle(parent, style), Clickable {
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
