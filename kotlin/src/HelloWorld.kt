fun main(args: Array<String>) {
    println("http:/api/v1/message/send".split("http:")[1]);

    println(testLet())

    var list = testApply()

    for(item: String in list){
        println(item)
    }


    for (i in list.indices){
        println(list[i])
    }

    var listStr = mutableListOf("12","23","34")
    testA(*listStr.toTypedArray())
}

fun testLet():Unit{
    return "testLet".let {
        println(it)
        println(it)
        println(it)
    }
}


fun testApply():List<String> {
    return ArrayList<String>().apply {
        add("123123")
        add("123")
        add("123")
        add("123")
    }
}

fun testA(vararg indexs:String){
    testB(*indexs.asList().toTypedArray())
}

fun testB(vararg indexs:String){
    println(indexs.toString())
}