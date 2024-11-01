class Products {
    val products= mutableListOf<Phone>()

    fun addNewProduct(product:Phone){
        products.add(product)
    }

    fun removeProduct(product: Phone) {
        products.remove(product)
    }
}