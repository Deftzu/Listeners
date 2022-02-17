fun main(args: Array<String>) {

    val listener1 = EvenNumberListener(1)
    val listener2 = EvenNumberListener(2)

    val integerList = IntegerList()
    integerList.registerListener(listener1)
    integerList.registerListener(listener2)
    integerList.findEvenNumbers()

}

data class IntegerList(val number: MutableList<Int> = ArrayList<Int>(), var evenNumberListenerList: MutableList<EvenNumberListener> = ArrayList<EvenNumberListener>()){

    init {
        for (i in 0..100){
            number.add(i)
        }
    }

    fun registerListener(listener: EvenNumberListener){
        evenNumberListenerList.add(listener)
    }
    fun deleteElemToList(listener: EvenNumberListener){
        evenNumberListenerList.remove(listener)
    }

    fun findEvenNumbers(){
        for (i in number){
            if (i % 2 == 0){
                notifyListeners(i)
            }
        }
    }

    fun notifyListeners(n: Int){
        for (listeners in evenNumberListenerList){
            listeners.onEvenNumberFound(n)
        }
    }
}

data class EvenNumberListener(val id: Int){
    fun onEvenNumberFound(n: Int){
        println("Even number found by id : $id : $n")
    }
}