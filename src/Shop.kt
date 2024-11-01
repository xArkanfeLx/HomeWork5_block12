class Shop(val city:String,var deliveryIndex:Double,var canRepear:Boolean) : MyShop {
    private val prices = mutableMapOf<String,Long>()
    private val sells = mutableMapOf<String,Long>()
    private var allSells:Long=0;

    override fun addPhonePrice(key:String,selfPrice:Long) {
        changePhonePrice(key,selfPrice)
        sells.put(key,0)
    }

    override fun removePhonePrice(key: String) {
        prices.remove(key)
        sells.remove(key)
    }

    override fun changePhonePrice(key:String,selfPrice:Long) {
        prices.put(key,generatePrice(selfPrice))
    }

    override fun getPhonePrice(key: String):Long {
        return prices.getValue(key)
    }

    override fun sellPhone(key:String) {
        println("\nТелефон ${key} теперь ваш, спасибо за покупку!\n")
        sells.put(key,sells.get(key)!!+1)
    }

    override fun showStatistic() {
        println("\nСтатистика магазина :")
        for(i in sells) {
            println("Телефон ${i.key} - > ${i.value}")
            allSells+=i.value
        }
        println("Всего продано телефонов -> $allSells")
    }

    override fun changeDeliveryIndex() {
        TODO("Not yet implemented")
    }

    override fun repairPhone():Boolean {
        println("\nТелефон прочинен!\n")
        return false
    }

    fun generatePrice(startPrice:Long):Long {
        val dopPrice = (startPrice*0.25).toInt() // допустим, закладываем на ЗП + Выручка
        return Math.ceil(startPrice*deliveryIndex).toLong()+Math.ceil(dopPrice/2 + Math.random()*(dopPrice/2)).toLong()
    }
}