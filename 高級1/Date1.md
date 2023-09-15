```
/**
 * JDK 8之前日期和時間的API測試
 *
 * @author shkstart
 * @create 2019 下午 4:30
 */
public class DateTimeTest {

    /*
    java.util.Date類
           |---java.sql.Date類

    1.兩個構造器的使用
        >構造器一：Date()：創建一個對應當前時間的Date對象
        >構造器二：創建指定毫秒數的Date對象
    2.兩個方法的使用
        >toString():顯示當前的年、月、日、時、分、秒
        >getTime():獲取當前Date對象對應的毫秒數。（時間戳）

    3. java.sql.Date對應著數據庫中的日期類型的變量
        >如何實例化
        >如何將java.util.Date對象轉換為java.sql.Date對象
     */
    @Test
    public void test2(){
        //構造器一：Date()：創建一個對應當前時間的Date對象
        Date date1 = new Date();
        System.out.println(date1.toString());//Sat Feb 16 16:35:31 GMT+08:00 2019

        System.out.println(date1.getTime());//1550306204104

        //構造器二：創建指定毫秒數的Date對象
        Date date2 = new Date(155030620410L);
        System.out.println(date2.toString());

        //創建java.sql.Date對象
        java.sql.Date date3 = new java.sql.Date(35235325345L);
        System.out.println(date3);//1971-02-13

        //如何將java.util.Date對象轉換為java.sql.Date對象
        //情況一：
//        Date date4 = new java.sql.Date(2343243242323L);
//        java.sql.Date date5 = (java.sql.Date) date4;
        //情況二：
        Date date6 = new Date();
        java.sql.Date date7 = new java.sql.Date(date6.getTime());


    }

    //1.System類中的currentTimeMillis()
    @Test
    public void test1(){
        long time = System.currentTimeMillis();
        //返回當前時間與1970年1月1日0時0分0秒之間以毫秒為單位的時間差。
        //稱為時間戳
        System.out.println(time);
    }
}
```

```
/**
 * jdk 8之前的日期時間的API測試
 * 1. System類中currentTimeMillis();
 * 2. java.util.Date和子類java.sql.Date
 * 3. SimpleDateFormat
 * 4. Calendar
 *
 * @author shkstart
 * @create 2019 上午 11:35
 */
public class DateTimeTest {
    /*
    SimpleDateFormat的使用：SimpleDateFormat對日期Date類的格式化和解析

    1.兩個操作：
    1.1 格式化：日期 --->字符串
    1.2 解析：格式化的逆過程，字符串 ---> 日期

    2.SimpleDateFormat的實例化

     */
    @Test
    public void testSimpleDateFormat() throws ParseException {
        //實例化SimpleDateFormat:使用默認的構造器
        SimpleDateFormat sdf = new SimpleDateFormat();

        //格式化：日期 --->字符串
        Date date = new Date();
        System.out.println(date);

        String format = sdf.format(date);
        System.out.println(format);

        //解析：格式化的逆過程，字符串 ---> 日期
        String str = "19-12-18 上午11:43";
        Date date1 = sdf.parse(str);
        System.out.println(date1);

        //*************按照指定的方式格式化和解析：調用帶參的構造器*****************
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //格式化
        String format1 = sdf1.format(date);
        System.out.println(format1);//2019-02-18 11:48:27
        //解析:要求字符串必須是符合SimpleDateFormat識別的格式(通過構造器參數體現),
        //否則，拋異常
        Date date2 = sdf1.parse("2020-02-18 11:48:27");
        System.out.println(date2);
    }

    /*
    練習一：字符串"2020-09-08"轉換為java.sql.Date

    練習二："三天打漁兩天曬網"   1990-01-01  xxxx-xx-xx 打漁？曬網？

    舉例：2020-09-08 ？ 總天數

    總天數 % 5 == 1,2,3 : 打漁
    總天數 % 5 == 4,0 : 曬網

    總天數的計算？
    方式一：( date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24) + 1
    方式二：1990-01-01  --> 2019-12-31  +  2020-01-01 -->2020-09-08
     */
    @Test
    public void testExer() throws ParseException {
        String birth = "2020-09-08";

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf1.parse(birth);
//        System.out.println(date);

        java.sql.Date birthDate = new java.sql.Date(date.getTime());
        System.out.println(birthDate);
    }


    /*
    Calendar日歷類(抽象類）的使用

     */
    @Test
    public void testCalendar(){
        //1.實例化
        //方式一：創建其子類（GregorianCalendar）的對象
        //方式二：調用其靜態方法getInstance()
        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar.getClass());

        //2.常用方法
        //get()
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));

        //set()
        //calendar可變性
        calendar.set(Calendar.DAY_OF_MONTH,22);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        //add()
        calendar.add(Calendar.DAY_OF_MONTH,-3);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        //getTime():日歷類---> Date
        Date date = calendar.getTime();
        System.out.println(date);

        //setTime():Date ---> 日歷類
        Date date1 = new Date();
        calendar.setTime(date1);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

    }
}
```