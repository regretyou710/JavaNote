```
/**
 * 涉及到String類與其他結構之間的轉換
 *
 * @author shkstart
 * @create 2019 下午 2:39
 */
public class StringTest1 {


    /*
    String 與 byte[]之間的轉換
    編碼：String --> byte[]:調用String的getBytes()
    解碼：byte[] --> String:調用String的構造器

    編碼：字符串 -->字節  (看得懂 --->看不懂的二進制數據)
    解碼：編碼的逆過程，字節 --> 字符串 （看不懂的二進制數據 ---> 看得懂）

    說明：解碼時，要求解碼使用的字符集必須與編碼時使用的字符集一致，否則會出現亂碼。
     */
    @Test
    public void test3() throws UnsupportedEncodingException {
        String str1 = "abc123中國";
        byte[] bytes = str1.getBytes();//使用默認的字符集，進行編碼。
        System.out.println(Arrays.toString(bytes));

        byte[] gbks = str1.getBytes("gbk");//使用gbk字符集進行編碼。
        System.out.println(Arrays.toString(gbks));

        System.out.println("******************");

        String str2 = new String(bytes);//使用默認的字符集，進行解碼。
        System.out.println(str2);

        String str3 = new String(gbks);
        System.out.println(str3);//出現亂碼。原因：編碼集和解碼集不一致！


        String str4 = new String(gbks, "gbk");
        System.out.println(str4);//沒有出現亂碼。原因：編碼集和解碼集一致！


    }

    /*
    String 與 char[]之間的轉換

    String --> char[]:調用String的toCharArray()
    char[] --> String:調用String的構造器
     */
    @Test
    public void test2(){
        String str1 = "abc123";  //題目： a21cb3

        char[] charArray = str1.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            System.out.println(charArray[i]);
        }

        char[] arr = new char[]{'h','e','l','l','o'};
        String str2 = new String(arr);
        System.out.println(str2);
    }

    /*
    覆習：
    String 與基本數據類型、包裝類之間的轉換。

    String --> 基本數據類型、包裝類：調用包裝類的靜態方法：parseXxx(str)
    基本數據類型、包裝類 --> String:調用String重載的valueOf(xxx)

     */
    @Test
    public void test1(){
        String str1 = "123";
//        int num = (int)str1;//錯誤的
        int num = Integer.parseInt(str1);

        String str2 = String.valueOf(num);//"123"
        String str3 = num + "";

        System.out.println(str1 == str3);
    }

}
```