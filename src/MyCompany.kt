class MyCompany {
    private val shops = mutableListOf<Shop>()
    private val myProducts = Products()

    private var needRepear: Boolean = true

    fun initCompany() {
        addNewShop("Ярославль", 1.1, true)
        addNewShop("Рыбинск", 1.15, false)

        addNewProduct(Phone("Sony", "XperiaZ2", 10000))
        addNewProduct(Phone("Sony", "XperiaZ3", 15000))
        addNewProduct(Phone("Realme", "9Pro+", 28000))
        addNewProduct(Phone("IPhone", "14", 100000))
        addNewProduct(Phone("IPhone", "15", 130000))
    }

    fun addNewShop(city: String, deliveryIndex: Double, canRepear: Boolean) {
        shops.add(Shop(city, deliveryIndex, canRepear))
    }

    fun addNewProduct(product: Phone) {
        myProducts.addNewProduct(product)
        for (i in shops) i.addPhonePrice(myProducts.products.last().getKeyProduct(), product.selfPrice)
    }

    fun removeProduct(product: Phone) {
        myProducts.removeProduct(product)
        for (i in shops) i.removePhonePrice(product.getKeyProduct())
    }

    fun enterToSite() {
        needRepear = true
        println("\nПриветствуем вас в нашем интернет магазине по продаже телефонов! Выберите город, в котором вам удобно совершить покупку:")
        do {
            val needShop = chooseCityOrExit()
            if (needShop != null) chooseServiceInShop(needShop)
            else return
        } while (true)

    }

    fun chooseCityOrExit(): Shop? {
        println("0 -> Выход")
        for (i in shops.indices) println("${i + 1} -> ${shops[i].city}")
        do {
            val choose = readln()
            if (checkOfSelectionForCorrect(choose, 0, shops.size)) {
                val num = choose.toInt()
                if (num == 0) return null
                else return shops[num - 1]
            }
        } while (true)
    }

    fun chooseServiceInShop(shop: Shop) {
        val additionalList = listOf("repear", "info")
        println("\nКакую услугу вы хотите выбрать: \n0 -> Вернуться к выбору города")
        for (i in myProducts.products.indices) {
            val key = myProducts.products[i].getKeyProduct()
            println("${i + 1} -> Купить телефон ${key} по цене ${shop.getPhonePrice(key)}")
        }
        if (shop.canRepear && needRepear) println("${additionalList[0]} -> Ремонт вашего телефона")
        println("${additionalList[1]} -> Показать статистику магазина")
        do {
            val choose = readln()
            if (checkOfSelectionForCorrect(choose, 0, myProducts.products.size, additionalList)) {
                if (choose.toIntOrNull() == null) {
                    when (choose) {
                        additionalList[0] -> shop.repairPhone()
                        additionalList[1] -> {
                            shop.showStatistic()
                            chooseServiceInShop(shop)
                            return
                        }
                    }
                } else {
                    val num = choose.toInt()
                    if (num != 0) shop.sellPhone(myProducts.products[num - 1].getKeyProduct())
                    else return
                }
                if (shop.canRepear) needRepear = false
                return
            }
        } while (true)
    }

    fun checkOfSelectionForCorrect(
        selection: String,
        min: Int,
        max: Int,
        additional: List<String> = emptyList(),
    ): Boolean {
        val repeatText = " пожалуйста, повторите выбор:"
        for (i in additional) if (selection == i) return true
        val num = selection.toIntOrNull()
        if (num == null) {
            println("Введено не число или такого пункта нет,$repeatText")
            return false
        }
        if (num >= min && num <= max) return true
        println("Такого пункта нет,$repeatText")
        return false
    }
}