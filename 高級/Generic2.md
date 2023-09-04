```
/**
 *
 * 1. 泛型在繼承方面的體現
 *
 *
 * 2. 通配符的使用
 *
 * @author shkstart
 * @create 2019 下午 2:13
 */
public class GenericTest {

    /*
    1. 泛型在繼承方面的體現

      雖然類A是類B的父類，但是G<A> 和G<B>二者不具備子父類關系，二者是並列關系。

       補充：類A是類B的父類，A<G> 是 B<G> 的父類

     */
    @Test
    public void test1(){

        Object obj = null;
        String str = null;
        obj = str;

        Object[] arr1 = null;
        String[] arr2 = null;
        arr1 = arr2;
        //編譯不通過
//        Date date = new Date();
//        str = date;
        List<Object> list1 = null;
        List<String> list2 = new ArrayList<String>();
        //此時的list1和list2的類型不具有子父類關系
        //編譯不通過
//        list1 = list2;
        /*
        反證法：
        假設list1 = list2;
           list1.add(123);導致混入非String的數據。出錯。

         */

        show(list1);
        show1(list2);
    }


    public void show1(List<String> list){

    }

    public void show(List<Object> list){

    }

    @Test
    public void test2(){

        AbstractList<String> list1 = null;
        List<String> list2 = null;
        ArrayList<String> list3 = null;

        list1 = list3;
        list2 = list3;

        List<String> list4 = new ArrayList<>();

    }

    /*
    2. 通配符的使用
       通配符：?

       類A是類B的父類，G<A>和G<B>是沒有關系的，二者共同的父類是：G<?>


     */

    @Test
    public void test3(){
        List<Object> list1 = null;
        List<String> list2 = null;

        List<?> list = null;

        list = list1;
        list = list2;
        //編譯通過
//        print(list1);
//        print(list2);


        //
        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("CC");
        list = list3;
        //添加(寫入)：對於List<?>就不能向其內部添加數據。
        //除了添加null之外。
//        list.add("DD");
//        list.add('?');

        list.add(null);

        //獲取(讀取)：允許讀取數據，讀取的數據類型為Object。
        Object o = list.get(0);
        System.out.println(o);


    }

    public void print(List<?> list){
        Iterator<?> iterator = list.iterator();
        while(iterator.hasNext()){
            Object obj = iterator.next();
            System.out.println(obj);
        }
    }

    /*
    3.有限制條件的通配符的使用。
        ? extends A:
                G<? extends A> 可以作為G<A>和G<B>的父類，其中B是A的子類

        ? super A:
                G<? super A> 可以作為G<A>和G<B>的父類，其中B是A的父類

     */
    @Test
    public void test4(){

        List<? extends Person> list1 = null;
        List<? super Person> list2 = null;

        List<Student> list3 = new ArrayList<Student>();
        List<Person> list4 = new ArrayList<Person>();
        List<Object> list5 = new ArrayList<Object>();

        list1 = list3;
        list1 = list4;
//        list1 = list5;

//        list2 = list3;
        list2 = list4;
        list2 = list5;

        //讀取數據：
        list1 = list3;
        Person p = list1.get(0);
        //編譯不通過
        //Student s = list1.get(0);

        list2 = list4;
        Object obj = list2.get(0);
        ////編譯不通過
//        Person obj = list2.get(0);

        //寫入數據：
        //編譯不通過，因為有可能是Student類的子類
//        list1.add(new Student());

        //編譯通過，可以添加Person及其子類，透過多型達到泛型的類型安全
        list2.add(new Person());
        list2.add(new Student());

        //編譯不通過，因為當Object向下轉型時不能確定可以強轉成Person類
        //有可能那個類剛好繼承Object但是與Person類無關(兄弟類的樹枝圖)，使得強轉失敗(類型不安全)
        list2.add(new Object());
    }
}

class Person {
}

class Student extends Person {
}
```

```
    @Test
    public void test5() {
        List<? extends Person> list1 = null;
        List<? super Person> list2 = null;

        System.out.println("***********************");
        //因為List<? extends Person>，其中通配符表示Person或其子類
        //編譯時不能確定通配符類型，運行時才能確定
        //結論:<? extends Person>表示，list1.get(0)返回的最大類型為Person，最小類型不確定Person的哪一個子類

        List<Person> list6 = new ArrayList<>();
        list6.add(new Person());
        list1 = list6;

        //case 1:
        //list1.get(0)類型;編譯時:Person，類型相同
        //list1.get(0)類型;運行時:Person，類型相同
        Person person1 = list1.get(0);

        //case 2:
        //list1.get(0)類型;編譯時:Person，編譯失敗
//        Man person2 = list1.get(0);

        //case 3:
        //list1.get(0)類型;編譯時:Person強轉Man，類型相同
        //list1.get(0)類型;運行時:Man，運行失敗，因為在Person實例化時類加載過程中，只有Person->Object
//        Man person3 = (Man) list1.get(0);

        //case 4:
        //list1.get(0)類型;編譯時:Person，隱含向上(多型)
        //list1.get(0)類型;運行時:Person，隱含向上(多型)
        Object person4 = list1.get(0);

        System.out.println("***********************");
        List<Man> list7 = new ArrayList<>();
        list7.add(new Man());
        list1 = list7;

        //case 1:
        //list1.get(0)類型;編譯時:Man，隱含向上(多型)
        //list1.get(0)類型;編譯時:Person，類型相同
        //list1.get(0)類型;運行時:Man，隱含向上(多型)
        Person person5 = list1.get(0);

        //case 2:
        //list1.get(0)類型;編譯時:Man，類型相同
        //list1.get(0)類型;編譯時:Person，編譯失敗
//        Man person6 = list1.get(0);

        //case 3:
        //list1.get(0)類型;編譯時:Man，類型相同
        //list1.get(0)類型;編譯時:Person強轉Man，類型相同
        //list1.get(0)類型;運行時:Man，類型相同
        Man person7 = (Man) list1.get(0);

        //case 4:
        //list1.get(0)類型;編譯時:Man，隱含向上(多型)
        //list1.get(0)類型;編譯時:Person，隱含向上(多型)
        //list1.get(0)類型;運行時:Man，隱含向上(多型)
        Object person8 = list1.get(0);

        System.out.println("***********************");
        //因為List<? super Person>，其中通配符表示Person或其父類
        //編譯時不能確定通配符類型，運行時才能確定
        //結論:<? super Person>表示，list1.get(0)返回的最大類型為Person根父類，最小類型為Person
        List<Person> list8 = new ArrayList<>();
        list8.add(new Person());
        list2 = list8;

        //case 1:
        //list2.get(0)類型;編譯時:Person，類型相同
        //list2.get(0)類型;編譯時:Object，編譯失敗
//        Person person9 = list2.get(0);

        //case 2:
        //list2.get(0)類型;編譯時:Person，類型相同
        //list2.get(0)類型;編譯時:Object強轉Person，類型相同
        //list2.get(0)類型;運行時:Person，類型相同
        Person person10 = (Person)list2.get(0);

        //case 3:
        //list2.get(0)類型;編譯時:Person，隱含向上(多型)
        //list2.get(0)類型;編譯時:Object，類型相同
        //list2.get(0)類型;運行時:Person，隱含向上(多型)
        Object person11 = list2.get(0);
    }

class Person {
}


class Man extends Person {
}
```
# 結論:注意<? extends T>的添加與獲取及<? super T>的添加與獲取、<?>的添加與獲取