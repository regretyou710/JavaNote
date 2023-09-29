# JUC輔助類CountDownLatch
```
import java.util.concurrent.CountDownLatch;

//演示 CountDownLatch
public class CountDownLatchDemo {
    //6個同學陸續離開教室之後，班長鎖門
    public static void main(String[] args) throws InterruptedException {

        //創建CountDownLatch對象，設置初始值
        CountDownLatch countDownLatch = new CountDownLatch(6);

        //6個同學陸續離開教室之後
        for (int i = 1; i <=6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" 號同學離開了教室");

                //計數  -1
                countDownLatch.countDown();

            },String.valueOf(i)).start();
        }

        //等待
        countDownLatch.await();//計數器不是0則持續await

        System.out.println(Thread.currentThread().getName()+" 班長鎖門走人了");
    }
}
```