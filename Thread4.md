```
/**
 * 解決線程安全問題的方式三：Lock鎖  --- JDK5.0新增
 *
 * 1. 面試題：synchronized 與 Lock的異同？
 *   相同：二者都可以解決線程安全問題
 *   不同：synchronized機制在執行完相應的同步代碼以後，自動的釋放同步監視器
 *        Lock需要手動的啟動同步（lock()），同時結束同步也需要手動的實現（unlock()）
 *
 * 2.優先使用順序：
 * Lock => 同步代碼塊（已經進入了方法體，分配了相應資源） => 同步方法（在方法體之外）
 *
 *
 *  面試題：如何解決線程安全問題？有幾種方式
 *          同步代碼塊、同步方法、Lock鎖
 *
 * 如果使用繼承方式創建Thread，private ReentrantLock lock = new ReentrantLock();要注意宣告成靜態，使lock唯一
 *
 * @author shkstart
 * @create 2019-02-15 下午 3:38
 */
class Window implements Runnable{
    private int ticket = 100;
    //1.實例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while(true){
            try{

                //2.調用鎖定方法lock()
                lock.lock();

                if(ticket > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "：售票，票號為：" + ticket);
                    ticket--;
                }else{
                    break;
                }
            }finally {
                //3.調用解鎖方法：unlock()
                lock.unlock();
            }
        }
    }
}

public class LockTest {
    public static void main(String[] args) {
        Window w = new Window();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
```