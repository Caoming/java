用流收集数据
=========
# Collector接口和Collectors工厂类
# 归约和汇总
* counting 总个数
* maxBy和minBy
* summingInt、summingDouble、summingLong 总和
* averagingLong、averagingInt、averagingDouble
* joining 连接符
* reducing操作，见案例
    * collectors.reducing方法和stream的reduce的区别？
# 分组
* groupingBy
# 分区
* partitioningBy
# 收集器接口
* 对应的方法
    * supplier 创建一个新的可变结果容器的函数
    * accumulator 将值折叠成可变结果容器的函数
    * finisher 中间结果转换成最终结果的函数
    * combiner 修改第一个累加器，将其与第二个累加器的内容合并，将两部分结果组合成一个组合结果的函数
    * characteristic 收集器的标志
        * UNORDERED：归约结果不受流中项目的遍历和累积顺序的影响
        * CONCURRENT：accumulator的操作支持多线程同步
        * IDENTITY_FINISH：表明完成器方法返回的函数是一个恒等函数 

# 自己实现收集器
