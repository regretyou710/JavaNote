```
/**
 * 商品類
 * @author shkstart
 * @create 2019 下午 4:52
 */
public class Goods implements  Comparable{

    private String name;
    private double price;

    public Goods() {
    }

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    //指明商品比較大小的方式:按照價格從低到高排序,再按照產品名稱從高到低排序
    @Override
    public int compareTo(Object o) {
//        System.out.println("**************");
        if(o instanceof Goods){
            Goods goods = (Goods)o;
            //方式一：
            if(this.price > goods.price){
                return 1;
            }else if(this.price < goods.price){
                return -1;
            }else{
//                return 0;
               return -this.name.compareTo(goods.name);
            }
            //方式二：
//           return Double.compare(this.price,goods.price);
        }
//        return 0;
        throw new RuntimeException("傳入的數據類型不一致！");
    }
}
```

```
/**
 * 一、說明：Java中的對象，正常情況下，只能進行比較：==  或  != 。不能使用 > 或 < 的
 *          但是在開發場景中，我們需要對多個對象進行排序，言外之意，就需要比較對象的大小。
 *          如何實現？使用兩個接口中的任何一個：Comparable 或 Comparator
 *
 * 二、Comparable接口與Comparator的使用的對比：
 *    Comparable接口的方式一旦一定，保證Comparable接口實現類的對象在任何位置都可以比較大小。
 *    Comparator接口屬於臨時性的比較。
 *
 *
 *
 *
 *thor shkstart
 * @create 2019 下午 4:41
 */
public class CompareTest {
    /*
    Comparable接口的使用舉例：  自然排序
    1.像String、包裝類等實現了Comparable接口，重寫了compareTo(obj)方法，給出了比較兩個對象大小的方式。
    2.像String、包裝類重寫compareTo()方法以後，進行了從小到大的排列
    3. 重寫compareTo(obj)的規則：
        如果當前對象this大於形參對象obj，則返回正整數，
        如果當前對象this小於形參對象obj，則返回負整數，
        如果當前對象this等於形參對象obj，則返回零。
    4. 對於自定義類來說，如果需要排序，我們可以讓自定義類實現Comparable接口，重寫compareTo(obj)方法。
       在compareTo(obj)方法中指明如何排序
     */
    @Test
    public void test1(){
        String[] arr = new String[]{"AA","CC","KK","MM","GG","JJ","DD"};
        //
        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr));

    }

    @Test
    public void test2(){
        Goods[] arr = new Goods[5];
        arr[0] = new Goods("lenovoMouse",34);
        arr[1] = new Goods("dellMouse",43);
        arr[2] = new Goods("xiaomiMouse",12);
        arr[3] = new Goods("huaweiMouse",65);
        arr[4] = new Goods("microsoftMouse",43);

        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr));
    }

    /*
    Comparator接口的使用：定制排序
    1.背景：
    當元素的類型沒有實現java.lang.Comparable接口而又不方便修改代碼，
    或者實現了java.lang.Comparable接口的排序規則不適合當前的操作，
    那麽可以考慮使用 Comparator 的對象來排序
    2.重寫compare(Object o1,Object o2)方法，比較o1和o2的大小：
    如果方法返回正整數，則表示o1大於o2；
    如果返回0，表示相等；
    返回負整數，表示o1小於o2。

     */
    @Test
    public void test3(){
        String[] arr = new String[]{"AA","CC","KK","MM","GG","JJ","DD"};
        Arrays.sort(arr,new Comparator(){

            //按照字符串從大到小的順序排列
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof String && o2 instanceof  String){
                    String s1 = (String) o1;
                    String s2 = (String) o2;
                    return -s1.compareTo(s2);
                }
//                return 0;
                throw new RuntimeException("輸入的數據類型不一致");
            }
        });
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test4(){
        Goods[] arr = new Goods[6];
        arr[0] = new Goods("lenovoMouse",34);
        arr[1] = new Goods("dellMouse",43);
        arr[2] = new Goods("xiaomiMouse",12);
        arr[3] = new Goods("huaweiMouse",65);
        arr[4] = new Goods("huaweiMouse",224);
        arr[5] = new Goods("microsoftMouse",43);

        Arrays.sort(arr, new Comparator() {
            //指明商品比較大小的方式:按照產品名稱從低到高排序,再按照價格從高到低排序
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Goods && o2 instanceof Goods){
                    Goods g1 = (Goods)o1;
                    Goods g2 = (Goods)o2;
                    if(g1.getName().equals(g2.getName())){
                        return -Double.compare(g1.getPrice(),g2.getPrice());
                    }else{
                        return g1.getName().compareTo(g2.getName());
                    }
                }
                throw new RuntimeException("輸入的數據類型不一致");
            }
        });

        System.out.println(Arrays.toString(arr));
    }

}
```