```
/**
 * 銀行有一個賬戶。
 有兩個儲戶分別向同一個賬戶存3000元，每次存1000，存3次。每次存完打印賬戶余額。

    分析：
 1.是否是多線程問題？ 是，兩個儲戶線程
 2.是否有共享數據？ 有，賬戶（或賬戶余額）
 3.是否有線程安全問題？有
 4.需要考慮如何解決線程安全問題？同步機制：有三種方式。

 * @author shkstart
 * @create 2019-02-15 下午 3:54
 */
class Account{
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    //存錢
    public synchronized void deposit(double amt){
        if(amt > 0){
            balance += amt;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + ":存錢成功。余額為：" + balance);
        }
    }
}

class Customer extends  Thread{

    private Account acct;

    public Customer(Account acct) {
        this.acct = acct;
    }

    @Override
    public void run() {

        for (int i = 0; i < 3; i++) {
            acct.deposit(1000);
        }

    }
}

public class AccountTest {

    public static void main(String[] args) {
        Account acct = new Account(0);
        Customer c1 = new Customer(acct);
        Customer c2 = new Customer(acct);

        c1.setName("甲");
        c2.setName("乙");

        c1.start();
        c2.start();
    }
}
```