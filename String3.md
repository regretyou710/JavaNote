```
/**
 * 關於StringBuffer和StringBuilder的使用
 *
 * @author shkstart
 * @create 2019 下午 3:32
 */
public class StringBufferBuilderTest {
    /*
    對比String、StringBuffer、StringBuilder三者的效率：
    從高到低排列：StringBuilder > StringBuffer > String
     */
    @Test
    public void test3(){
        //初始設置
        long startTime = 0L;
        long endTime = 0L;
        String text = "";
        StringBuffer buffer = new StringBuffer("");
        StringBuilder builder = new StringBuilder("");
        //開始對比
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            buffer.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer的執行時間：" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            builder.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder的執行時間：" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            text = text + i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("String的執行時間：" + (endTime - startTime));

    }

    /*
    StringBuffer的常用方法：
    StringBuffer append(xxx)：提供了很多的append()方法，用於進行字符串拼接
    StringBuffer delete(int start,int end)：刪除指定位置的內容
    StringBuffer replace(int start, int end, String str)：把[start,end)位置替換為str
    StringBuffer insert(int offset, xxx)：在指定位置插入xxx
    StringBuffer reverse() ：把當前字符序列逆轉
    public int indexOf(String str)
    public String substring(int start,int end):返回一個從start開始到end索引結束的左閉右開區間的子字符串
    public int length()
    public char charAt(int n )
    public void setCharAt(int n ,char ch)

        總結：
        增：append(xxx)
        刪：delete(int start,int end)
        改：setCharAt(int n ,char ch) / replace(int start, int end, String str)
        查：charAt(int n )
        插：insert(int offset, xxx)
        長度：length();
        *遍歷：for() + charAt() / toString()
     */
    @Test
    public void test2(){
        StringBuffer s1 = new StringBuffer("abc");
        s1.append(1);
        s1.append('1');
        System.out.println(s1);
//        s1.delete(2,4);
//        s1.replace(2,4,"hello");
//        s1.insert(2,false);
//        s1.reverse();
        String s2 = s1.substring(1, 3);
        System.out.println(s1);
        System.out.println(s1.length());
        System.out.println(s2);
    }


    /*
    String、StringBuffer、StringBuilder三者的異同？
    String:不可變的字符序列；底層使用char[]存儲
    StringBuffer:可變的字符序列；線程安全的，效率低；底層使用char[]存儲
    StringBuilder:可變的字符序列；jdk5.0新增的，線程不安全的，效率高；底層使用char[]存儲

    源碼分析：
    String str = new String();//char[] value = new char[0];
    String str1 = new String("abc");//char[] value = new char[]{'a','b','c'};

    StringBuffer sb1 = new StringBuffer();//char[] value = new char[16];底層創建了一個長度是16的數組。
    System.out.println(sb1.length());//
    sb1.append('a');//value[0] = 'a';
    sb1.append('b');//value[1] = 'b';

    StringBuffer sb2 = new StringBuffer("abc");//char[] value = new char["abc".length() + 16];

    //問題1. System.out.println(sb2.length());//3
    //問題2. 擴容問題:如果要添加的數據底層數組盛不下了，那就需要擴容底層的數組。
             默認情況下，擴容為原來容量的2倍 + 2，同時將原有數組中的元素覆制到新的數組中。

            指導意義：開發中建議大家使用：StringBuffer(int capacity) 或 StringBuilder(int capacity)


     */
    @Test
    public void test1(){
        StringBuffer sb1 = new StringBuffer("abc");
        sb1.setCharAt(0,'m');
        System.out.println(sb1);

        StringBuffer sb2 = new StringBuffer();
        System.out.println(sb2.length());//0
    }

}
```