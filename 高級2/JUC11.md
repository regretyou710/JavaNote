# 阻塞隊列
```
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        //創建阻塞隊列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        //第一組:拋異常
//        System.out.println(blockingQueue.add("a"));//true
////        System.out.println(blockingQueue.add("b"));//true
////        System.out.println(blockingQueue.add("c"));//true
////        //System.out.println(blockingQueue.element());//a 返回對列第一個元素
////
////        //System.out.println(blockingQueue.add("w"));//拋異常
////        System.out.println(blockingQueue.remove());//a
////        System.out.println(blockingQueue.remove());//b
////        System.out.println(blockingQueue.remove());//c
////        System.out.println(blockingQueue.remove());//拋異常

        //第二組
//        System.out.println(blockingQueue.offer("a"));//true
//        System.out.println(blockingQueue.offer("b"));//true
//        System.out.println(blockingQueue.offer("c"));//true
//        System.out.println(blockingQueue.offer("www"));//false
//
//        System.out.println(blockingQueue.peek());//a 返回對列第一個元素
//        System.out.println(blockingQueue.poll());//a
//        System.out.println(blockingQueue.peek());//b
//        System.out.println(blockingQueue.poll());//b
//        System.out.println(blockingQueue.poll());//c
//        System.out.println(blockingQueue.poll());//null

        //第三組:阻塞
//        blockingQueue.put("a");
//        blockingQueue.put("b");
//        blockingQueue.put("c");
//        //blockingQueue.put("w");//開始阻塞
//
//        System.out.println(blockingQueue.take());//a
//        System.out.println(blockingQueue.take());//b
//        System.out.println(blockingQueue.take());//c
//        System.out.println(blockingQueue.take());//開始阻塞

        //第四組:阻塞後釋放
        System.out.println(blockingQueue.offer("a"));//true
        System.out.println(blockingQueue.offer("b"));//true
        System.out.println(blockingQueue.offer("c"));//true
        System.out.println(blockingQueue.offer("w",3L, TimeUnit.SECONDS));//false 阻塞後釋放
    }
}
```