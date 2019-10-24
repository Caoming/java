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