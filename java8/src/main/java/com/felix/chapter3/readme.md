lambda表达式
==================
# 定义
* 概念：可传递的匿名函数的一种方式，它有参数列表、函数主体、返回类型，可能还有一个可以抛出的异常列表
    * 匿名：没有具体的名称，写得少，想得多
    * 函数：它和普通函数一样，有参数列表、函数主体、返回类型、还有可能抛出异常列表
    * 传递：可以当作方法的参数进行传递，也可以存储在变量当中
    * 简洁：没有多少代码，比匿名函数简洁得多
* lambda表达式三个部分 eg: (Apple a) -> a.getWeight();
    * 参数列表: Apple a
    * 箭头：分割lambda的参数列表和主体的分割符
    * lambda主体
        * 函数主体为表达式： (parameters) -> expression  eg: () -> System.out.print("hello world")
        * 函数主体为程序语句： (parameters) -> {statements;} eg: () -> {System.out.print("hello world");}
* 函数式接口
    * 概念：函数接口就是只定义一个方法的接口（<span color ='red'>定义不全面</span>）
* 函数描述符
    * 函数式接口的抽象方法的签名基本上就是lambda函数表达式前面，意思就是函数接口的参数和返回值就lambda的参数返回值
    * eg：process(Runnable r) 传的参数就可以为 () -> System.out.println("") 的lambda的表达式
    * @FunctionalInterface的注解，标识该接口为函数式接口，若有多个方法，则编译出错。该注解不是必须的
* 实践lambda表达式参数化（环绕执行模式）
    * 参见BufferedReaderProcessor，把处理流的工作利用参数进行传递，开启流和关闭流都在代码中
* java8中函数式接口
    * 概述：java.util.function中的新的函数接口
    * 类别
        * Predicate：接收一个范型T的对象，返回boolean类型
        * Consumer：接收一个范型T的对象，无返回
        * Function：接收范型T，R的范型，并返回R对象
        * 原始类型特化
    * lambda 表达式使用局部变量的限制，lambda函数主体可以引用成员变量，对局部变量的只能引用不可变的局部变量。可变的局部变量不能引用
* 方法引用
    * 方法引用：lambda一种更加便捷的一种方式，基本思想就是：如果lambda表示的是：直接调用这个方法，那最好用名称来调用，而不是描述它如何调用
    * eg: (Apple a) -> a.getWeight() 等效方法引用：  Apple:getWeight()
    * 方法引用的三种类型
        * 指向静态方法的方法引用，eg：Integer::parseInt， args -> ClassName.staticMethod(args) 等效于 ClassName::staticMethod
        * 指向任意类型实例方法的引用,eg:String的length方法：String::length  (arg0,rest) -> arg0.instanceMethod(rest) 等效于：ClassName::instanceMethod
        * 指向现有对象的实例方法的引用,eg: (args) -> expr.instanceMethod(args) 等效于：expr::instanceMethod
* 构造函数的引用
    * 概念：对一个现有构造函数，可以利用名称和关键词new来创建它的一个引用。ClassName::new。对应的签名和构造器一样
* 复合lambda
    * 比较器复合
    * 谓词复合：negate、and、or
    * 函数复合 ：andThen、compose
     
     
        
