引入流
=============
 * Stream API优点
    * 声明性：更简洁，更易读
    * 可复合：更灵活
    * 可并行：性能更好
 * 流简介
    * 概念：从支持数据处理操作的源生成的元素序列
        * 元素序列：就像集合一样，流也提供了一个接口，可以访问特定元素类型的一组有序 值。因为集合是数据结构，所以它的主要目的是以特定的时间/空间复杂度存储和访问元 素(如ArrayList 与 LinkedList)。但流的目的在于表达计算，
        比如你前面见到的 filter、sorted和map。集合讲的是数据，<span color = 'red'流讲的是计算</span>。
        * 源：流会使用一个数据源，有序集合的生成流时会保留原有的顺序。由列生成的流，其元素顺序与列表一致
        * 数据处理操作：流的数据处理功能支持类似于数据库的操作，以及函数式编程中的常用操作。可以顺序执行，也可以并行执行；
        * 流水线：很多流操作本身会返回一个流，这样多个操作就可以链接起来，形成一个大的流水线。这让我们下一章中的一些优化成为可能，如延迟和短路。流水线的操作可以看作对数据源进行数据库式查询。
        * 内部迭代：与使用迭代器显式迭代的集合不同，流的迭代操作是在背后进行的
 * 流和集合
    * 差异：流和集合什么时候开始计算
        * 集合是内存中的数据结构，事先要把数据计算好，放入到集合中；
        * 流则是固定的数据结构，不能添加删除元素，元素是按照需要计算的；