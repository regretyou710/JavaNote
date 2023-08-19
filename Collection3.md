```
/**
 * jdk 5.0 新增了foreach循環，用於遍歷集合、數組
 *
 * @author shkstart
 * @create 2019 上午 11:24
 */
public class ForTest {

    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        //for(集合元素的類型 局部變量 : 集合對象)
        //內部仍然調用了叠代器。
        for(Object obj : coll){
            System.out.println(obj);
        }
    }

    @Test
    public void test2(){
        int[] arr = new int[]{1,2,3,4,5,6};
        //for(數組元素的類型 局部變量 : 數組對象)
        for(int i : arr){
            System.out.println(i);
        }
    }

    //練習題
    @Test
    public void test3(){

        String[] arr = new String[]{"MM","MM","MM"};

//        //方式一：普通for賦值
//        for(int i = 0;i < arr.length;i++){
//            arr[i] = "GG";
//        }

        //方式二：增強for循環
        for(String s : arr){
            s = "GG";//並未對陣列中的元素重新指派
        }

        for(int i = 0;i < arr.length;i++){
            System.out.println(arr[i]);
        }


    }
}
```