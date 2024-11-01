class Phone(val brand:String,val model:String,val selfPrice:Long) {
    fun getKeyProduct():String{
        return "$brand $model"
    }
}