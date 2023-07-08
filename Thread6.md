```
/**
 * 線程通信的例子：使用兩個線程打印 1-100。線程1, 線程2 交替打印
 *
 * 涉及到的三個方法：
 * wait():一旦執行此方法，當前線程就進入阻塞狀態，並釋放同步監視器。
 * notify():一旦執行此方法，就會喚醒被wait的一個線程。如果有多個線程被wait，就喚醒優先級高的那個。
 * notifyAll():一旦執行此方法，就會喚醒所有被wait的線程。
 *
 * 說明：
 * 1.wait()，notify()，notifyAll()三個方法必須使用在同步代碼塊或同步方法中。
 * 2.wait()，notify()，notifyAll()三個方法的調用者必須是同步代碼塊或同步方法中的"同步監視器"(同步監視器和調用這三個方法
 *   的對象必須相同)。否則，會出現IllegalMonitorStateException異常
 * 3.wait()，notify()，notifyAll()三個方法是定義在java.lang.Object類中。因為同步監視器可以是任意類的對象，又因為三個方法
 *   的調用對象必須同步監視器，須保證任意類的對象都要有三個方法，所以定義在Object類中
 *
 * 面試題：sleep() 和 wait()的異同？
 * 1.相同點：一旦執行方法，都可以使得當前的線程進入阻塞狀態。
 * 2.不同點：1）兩個方法聲明的位置不同：Thread類中聲明sleep() , Object類中聲明wait()
 *           2）調用的要求不同：sleep()可以在任何需要的場景下調用。 wait()必須使用在同步代碼塊或同步方法中
 *           3）關於是否釋放同步監視器：如果兩個方法都使用在同步代碼塊或同步方法中，sleep()不會釋放鎖，wait()會釋放鎖。
 *
 * 
 * 由於等待一個鎖定線程只有在獲得這把鎖之後，才能恢覆運行，所以讓持有鎖的線程在不需要鎖的時候及時釋放鎖是很重要的。
 * 在以下情況下，持有鎖的線程會釋放鎖： 
 * 1.當前線程的同步方法、代碼塊執行結束的時候釋放 
 * 2.當前線程在同步方法、同步代碼塊中遇到break 、 return 終於該代碼塊或者方法的時候釋放。 
 * 3.當前線程出現未處理的error或者exception導致異常結束的時候釋放 
 * 4.調用obj.wait()會立即釋放鎖，當前線程暫停，釋放鎖，以便其他線程可以執行obj.notify()，但是notify()不會立刻立刻釋放
 *   sycronized(obj)中的obj鎖，必須要等notify()所在線程執行完synchronized(obj)塊中的所有代碼才會釋放這把鎖。而 yield()
 *   及sleep()不會釋放鎖。 
 * 除了以上情況外，只要持有鎖的此案吃還沒有執行完同步代碼塊，就不會釋放鎖。因此在以下情況下，線程不會釋放鎖： 
 * 1. 在執行同步代碼塊的過程中，執行了Thread.sleep()方法，當前線程放棄CPU，開始睡眠，在睡眠中不會釋放鎖。 
 * 2. 在執行同步代碼塊的過程中，執行了Thread.yield()方法，當前線程放棄CPU，但不會釋放鎖。 
 * 3. 在執行同步代碼塊的過程中，其他線程執行了當前對象的suspend()方法，當前線程被暫停，但不會釋放鎖。但Thread類的suspend()*    方法已經被廢棄
 *
 * @author shkstart
 * @create 2019-02-15 下午 4:21
 */
class Number implements Runnable{
    private int number = 1;
    private Object obj = new Object();
    @Override
    public void run() {

        while(true){

            synchronized (obj) {

                obj.notify();

                if(number <= 100){

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;

                    try {
                        //使得調用如下wait()方法的線程進入阻塞狀態
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }else{
                    break;
                }
            }
        }
    }
}

public class CommunicationTest {
    public static void main(String[] args) {
        Number number = new Number();
        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);

        t1.setName("線程1");
        t2.setName("線程2");

        t1.start();
        t2.start();
    }
}
```