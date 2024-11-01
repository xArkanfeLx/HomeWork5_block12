interface  MyShop {
    fun addPhonePrice(key:String,selfPrice:Long)
    fun removePhonePrice(key:String)
    fun changePhonePrice(key:String,selfPrice:Long)
    fun getPhonePrice(key:String):Long
    fun sellPhone(key:String)
    fun showStatistic()
    fun changeDeliveryIndex()
    fun repairPhone():Boolean
}