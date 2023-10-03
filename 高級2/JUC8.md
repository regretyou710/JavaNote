# JUC輔助類CyclicBarrierDemo
```
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//集齊7顆龍珠就可以召喚神龍
public class CyclicBarrierDemo {

    //創建固定值
    private static final int NUMBER = 7;

    public static void main(String[] args) {
        //創建CyclicBarrier，在cyclicBarrier.await()達到設定的數量(參數一)時啟動run方法(參數二)
        CyclicBarrier cyclicBarrier =
                new CyclicBarrier(NUMBER,()->{
                    System.out.println("*****集齊7顆龍珠就可以召喚神龍");
                });

        //集齊七顆龍珠過程
        for (int i = 1; i <=7; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+" 星龍被收集到了");
                    //等待
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
```