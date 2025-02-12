fun main() {
    val foldablePhone = FoldablePhone()

    foldablePhone.unfold()
    foldablePhone.switchOn()
    foldablePhone.checkPhoneScreenLight()

    foldablePhone.fold()
    foldablePhone.switchOn()
    foldablePhone.checkPhoneScreenLight()
}

open class Phone(var isScreenLightOn: Boolean = false) {
    open fun switchOn() {
        isScreenLightOn = true
    }

    fun switchOff() {
        isScreenLightOn = false
    }

    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

class FoldablePhone(var isPhoneFolded: Boolean = true, isScreenLightOn: Boolean = false) : Phone(isScreenLightOn) {
    override fun switchOn() {
        if (!isPhoneFolded) {
            super.switchOn()
        }
    }

    fun fold() {
        isPhoneFolded = true
        if (isScreenLightOn) {
            isScreenLightOn = false
        }
    }

    fun unfold() {
        isPhoneFolded = false
    }
}