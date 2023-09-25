![JUC1](../img/高級2/JUC1.jpg)
```
//演示用戶線程和守護線程
public class Main {

    public static void main(String[] args) {
        Thread aa = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while (true) {

            }
        }, "aa");
        //設置守護線程
        aa.setDaemon(true);
        aa.start();

        System.out.println(Thread.currentThread().getName()+" over");
    }
}
```