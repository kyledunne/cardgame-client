package old

/**
 * Created by Kyle on 4/30/2016.
 */
interface Clickable {
    var bounds: FloatArray
    var listener: ClickableListener
    fun mousedOver()
    fun unMousedOver()
    fun clicked()
    fun released()
}
