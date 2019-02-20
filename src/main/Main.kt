package main

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        kot2d.init()

        //

        kot2d.loop({checkEvents()})
    }

    fun checkEvents() {

    }

    fun exitCondition(): Boolean {
        return true
    }
}