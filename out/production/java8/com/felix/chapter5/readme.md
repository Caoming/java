使用流
=========
# 筛选
* filter 筛选
* distinct 去重
* limit 截短流
* skip 跳过元素

# 映射和流偏平化
* flatMap 方法让你把一个流中每个值换成一个流，然后把所有的流连接起来成为一个流
* 作用，在中间过程产生多个流，但需要合并的时候

# 查找和匹配
* allMatch 所有匹配
* anyMatch 任意一个匹配
* noneMatch 没有一个匹配
* findFirst 第一个元素
* findAny 任意随机一个元素

# 归约
* reduce 两个参数 
    * 初始值
    * BinaryOperator<T>： lambda (a,b) -> a + b
    
# 数值流
    * 作用： 减少拆装箱的成本，带来一些数值常用的归约方法
    * IntStream、DoubleStream、LongStream 三种数值流
    * 例子：
        * 映射到数值流：menu.stream().mapToInt(Dish::getCalories).sum();
        * 转化为对象流：intStream.boxed()
        * 数值范围：IntStream.rangeClosed 左闭右闭 和 ntStream.range 左闭右开
        
# 构建流
    * 由值创建流，例子：
        * Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        * 空流：Stream<String> emptyStream = Stream.empty();
        * 数组创建流： Arrays.stream(Arrays.asList(int[]{1,2,3,34})).sum()
        * 文件流：Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())
        * 创建无限流：Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
        * 创建生成流：Stream.generate(Math::random).limit(5).forEach(System.out::println);