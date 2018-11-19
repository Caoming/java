《深入Java虚拟机》周志明 第二版第七章《虚拟机类加载机制》内容代码
* 初始化只有五个类型
** 遇到指令new、getstatic、putstatic、invokestatic这四个指令时，如果没有进行初始化，
会对类进行初始化。常见场景：new 对象，读取或者设置一个静态类（被修饰为final的常量池字段除外），以及调用一个静态方法
** 遇到java.lang.reflect包的方法进行反射调用的时候
** 初始化类时，发现父类未初始化，先初始化父类
** 当虚拟机启动时，用户需要指定一个执行的类（包含main()方法的那个类），虚拟机会先初始化这个类
** 当JDK1.7动态语言支持的时候，如果一个java.lang.invoke.MethodHandle实例最后解析的结果是REF_getStatic、
REF_putStatic、REF_invokeStatic的方法句柄，这个方法对应的类没有初始化，先对其初始化