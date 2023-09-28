# Synchronized所的八種情況
```
/**
* 1 標準訪問，先打印短信還是郵件
* ------sendSMS
* ------sendEmail
*/

import java.util.concurrent.TimeUnit;

class Phone {

    public synchronized void sendSMS() throws Exception {      
        System.out.println("------sendSMS");
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println("------sendEmail");
    }

    public void getHello() {
        System.out.println("------getHello");
    }
}

public static void main(String[] args) throws Exception {

        Phone phone = new Phone();       

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        Thread.sleep(100);//因為start方法什麼時候創建是不確定的，所以讓效果明顯讓線程sleep

        new Thread(() -> {
            try {
                phone.sendEmail();             
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
```

```
/**
* 2 停4秒在短信方法內，先打印短信還是郵件
* ------sendSMS
* ------sendEmail
*/

import java.util.concurrent.TimeUnit;

class Phone {

    public synchronized void sendSMS() throws Exception {    
         //停留4秒
        TimeUnit.SECONDS.sleep(4);  
        System.out.println("------sendSMS");
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println("------sendEmail");
    }

    public void getHello() {
        System.out.println("------getHello");
    }
}

public static void main(String[] args) throws Exception {

        Phone phone = new Phone();       

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        Thread.sleep(100);//因為start方法什麼時候創建是不確定的，所以讓效果明顯讓線程sleep

        new Thread(() -> {
            try {
                phone.sendEmail();             
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
```

```
/**
* 3 新增普通的hello方法，是先打短信還是hello
* ------getHello
* ------sendSMS
*/

import java.util.concurrent.TimeUnit;

class Phone {

    public synchronized void sendSMS() throws Exception {    
         //停留4秒
        TimeUnit.SECONDS.sleep(4);  
        System.out.println("------sendSMS");
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println("------sendEmail");
    }

    public void getHello() {
        System.out.println("------getHello");
    }
}

public static void main(String[] args) throws Exception {

        Phone phone = new Phone();       

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        Thread.sleep(100);//因為start方法什麼時候創建是不確定的，所以讓效果明顯讓線程sleep

        new Thread(() -> {
            try {
                phone.getHello();            
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
```

```
/**
* 4 現在有兩部手機，先打印短信還是郵件
* ------sendEmail
* ------sendSMS
*/

import java.util.concurrent.TimeUnit;

class Phone {

    public synchronized void sendSMS() throws Exception {    
         //停留4秒
        TimeUnit.SECONDS.sleep(4);  
        System.out.println("------sendSMS");
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println("------sendEmail");
    }

    public void getHello() {
        System.out.println("------getHello");
    }
}

public static void main(String[] args) throws Exception {

        Phone phone = new Phone();       
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        Thread.sleep(100);//因為start方法什麼時候創建是不確定的，所以讓效果明顯讓線程sleep

        new Thread(() -> {
            try {
                phone2.sendEmail();            
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
```

```
/**
* 5 兩個靜態同步方法，1部手機，先打印短信還是郵件
* ------sendSMS
* ------sendEmail
*/

import java.util.concurrent.TimeUnit;

class Phone {

    public static synchronized void sendSMS() throws Exception {    
         //停留4秒
        TimeUnit.SECONDS.sleep(4);  
        System.out.println("------sendSMS");
    }

    public static synchronized void sendEmail() throws Exception {
        System.out.println("------sendEmail");
    }

    public void getHello() {
        System.out.println("------getHello");
    }
}

public static void main(String[] args) throws Exception {

        Phone phone = new Phone();        

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        Thread.sleep(100);//因為start方法什麼時候創建是不確定的，所以讓效果明顯讓線程sleep

        new Thread(() -> {
            try {
                phone.sendEmail();            
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
```

```
/**
* 6 兩個靜態同步方法，2部手機，先打印短信還是郵件
* ------sendSMS
* ------sendEmail
*/

import java.util.concurrent.TimeUnit;

class Phone {

    public static synchronized void sendSMS() throws Exception {    
         //停留4秒
        TimeUnit.SECONDS.sleep(4);  
        System.out.println("------sendSMS");
    }

    public static synchronized void sendEmail() throws Exception {
        System.out.println("------sendEmail");
    }

    public void getHello() {
        System.out.println("------getHello");
    }
}

public static void main(String[] args) throws Exception {

        Phone phone = new Phone();        
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        Thread.sleep(100);//因為start方法什麼時候創建是不確定的，所以讓效果明顯讓線程sleep

        new Thread(() -> {
            try {
                phone2.sendEmail();            
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
```

```
/**
* 7 1個靜態同步方法,1個普通同步方法，1部手機，先打印短信還是郵件
* ------sendEmail
* ------sendSMS
*/

import java.util.concurrent.TimeUnit;

class Phone {

    public static synchronized void sendSMS() throws Exception {    
         //停留4秒
        TimeUnit.SECONDS.sleep(4);  
        System.out.println("------sendSMS");
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println("------sendEmail");
    }

    public void getHello() {
        System.out.println("------getHello");
    }
}

public static void main(String[] args) throws Exception {

        Phone phone = new Phone();        
      
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        Thread.sleep(100);//因為start方法什麼時候創建是不確定的，所以讓效果明顯讓線程sleep

        new Thread(() -> {
            try {
                phone.sendEmail();            
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
```

```
/**
* 8 1個靜態同步方法,1個普通同步方法，2部手機，先打印短信還是郵件
* ------sendEmail
* ------sendSMS
*/

import java.util.concurrent.TimeUnit;

class Phone {

    public static synchronized void sendSMS() throws Exception {    
         //停留4秒
        TimeUnit.SECONDS.sleep(4);  
        System.out.println("------sendSMS");
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println("------sendEmail");
    }

    public void getHello() {
        System.out.println("------getHello");
    }
}

public static void main(String[] args) throws Exception {

        Phone phone = new Phone();        
        Phone phone2 = new Phone();  

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        Thread.sleep(100);//因為start方法什麼時候創建是不確定的，所以讓效果明顯讓線程sleep

        new Thread(() -> {
            try {
                phone2.sendEmail();            
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
```