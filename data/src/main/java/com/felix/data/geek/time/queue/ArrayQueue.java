package com.felix.data.geek.time.queue;

/**
 * 顺序队列，先进先出
 */
public class ArrayQueue {

    // 开始角标
    private int startIndex = 0;

    // 结束角标
    private int endIndex = 0;

    private int size = 0;


    private Integer[] arr = new Integer[4];


    /**
     * 进入队列
     * @param t
     * @return
     */
    public  boolean enQueue(Integer t){
        if(startIndex == 0 && startIndex == endIndex){
            arr[0] = t;
            endIndex++;
            size++;
            return true;
        }

        if(startIndex == 0 ){
            if(endIndex <= arr.length-1) {
                arr[endIndex] = t;
                endIndex++;
                size++;
                if(endIndex >= arr.length){
                    endIndex =0;
                }
                return true;
            }else{
                System.out.println("数组已满");
                return false;
            }
        }else{
            if(endIndex<=arr.length-1) {
                if (endIndex != startIndex) {
                    arr[endIndex] = t;
                    endIndex++;
                    size++;
                    if(endIndex >= arr.length){
                        endIndex =0;
                    }
                    return true;
                }else{
                    System.out.println("数组已满");
                    return false;
                }
            }
        }

        return false;
    }

    /**
     * 出队列
     * @return
     */
    public Integer deQueue(){
        if(size == 0){
            System.out.println("队列中无元素");
            return 0;
        }

        int targetInt = arr[startIndex];
        arr[startIndex] = null;
        startIndex++;
        size--;
        if(startIndex > arr.length-1){
            startIndex =0;
        }
        return targetInt;
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        System.out.println(queue.deQueue());
        queue.enQueue(4);
        queue.enQueue(5);
        queue.enQueue(6);
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
    }
}
