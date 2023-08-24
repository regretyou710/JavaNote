```
/**
 * 1. Set接口的框架：
 *
 * |----Collection接口：單列集合，用來存儲一個一個的對象
 *          |----Set接口：存儲無序的、不可重覆的數據   -->高中講的“集合”
 *              |----HashSet：作為Set接口的主要實現類；線程不安全的；可以存儲null值
 *                  |----LinkedHashSet：作為HashSet的子類；遍歷其內部數據時，可以按照添加的順序遍歷
 *                                      對於頻繁的遍歷操作，LinkedHashSet效率高於HashSet.
 *              |----TreeSet：可以按照添加對象的指定屬性，進行排序。
 *
 *
 *  1. Set接口中沒有額外定義新的方法，使用的都是Collection中聲明過的方法。
 *
 *  2. 要求：向Set(主要指：HashSet、LinkedHashSet)中添加的數據，其所在的類一定要重寫hashCode()和equals()
 *     要求：重寫的hashCode()和equals()盡可能保持一致性：相等的對象必須具有相等的散列碼
 *      重寫兩個方法的小技巧：對象中用作 equals() 方法比較的 Field，都應該用來計算 hashCode 值。
 *
 *
 * @author shkstart
 * @create 2019 下午 3:40
 */
public class SetTest {
    /*
    一、Set：存儲無序的、不可重覆的數據
    以HashSet為例說明：
    1. 無序性：不等於隨機性。存儲的數據在底層數組中並非按照數組索引的順序添加，而是根據數據的哈希值決定的。

    2. 不可重覆性：保證添加的元素按照equals()判斷時，不能返回true.即：相同的元素只能添加一個。

    二、添加元素的過程：以HashSet為例：
        我們向HashSet中添加元素a,首先調用元素a所在類的hashCode()方法，計算元素a的哈希值，
        此哈希值接著通過某種算法計算出在HashSet底層數組中的存放位置（即為：索引位置），判斷
        數組此位置上是否已經有元素：
            如果此位置上沒有其他元素，則元素a添加成功。 --->情況1
            如果此位置上有其他元素b(或以鏈表形式存在的多個元素），則比較元素a與元素b的hash值：
                如果hash值不相同，則元素a添加成功。--->情況2
                如果hash值相同，進而需要調用元素a所在類的equals()方法：
                       equals()返回true,元素a添加失敗
                       equals()返回false,則元素a添加成功。--->情況3

        對於添加成功的情況2和情況3而言：元素a 與已經存在指定索引位置上數據以鏈表的方式存儲。
        jdk 7 :元素a放到數組中，指向原來的元素。
        jdk 8 :原來的元素在數組中，指向元素a
        總結：七上八下

        HashSet底層：數組+鏈表的結構。

     */

    @Test
    public void test1(){
        Set set = new HashSet();
        set.add(456);
        set.add(123);
        set.add(123);
        set.add("AA");
        set.add("CC");
        set.add(new User("Tom",12));
        set.add(new User("Tom",12));
        set.add(129);

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    //LinkedHashSet的使用
    //LinkedHashSet作為HashSet的子類，在添加數據的同時，每個數據還維護了兩個引用，記錄此數據前一個
    //數據和後一個數據。
    //優點：對於頻繁的遍歷操作，LinkedHashSet效率高於HashSet
    @Test
    public void test2(){
        Set set = new LinkedHashSet();
        set.add(456);
        set.add(123);
        set.add(123);
        set.add("AA");
        set.add("CC");
        set.add(new User("Tom",12));
        set.add(new User("Tom",12));
        set.add(129);

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
```

```
/**
 * @author shkstart
 * @create 2019 下午 4:59
 */
public class TreeSetTest {

    /*
    1.向TreeSet中添加的數據，要求是相同類的對象。
    2.兩種排序方式：自然排序（實現Comparable接口） 和 定制排序（Comparator）


    3.自然排序中，比較兩個對象是否相同的標準為：compareTo()返回0(相同的元素不會進行添加).不再是equals().
    4.定制排序中，比較兩個對象是否相同的標準為：compare()返回0(相同的元素不會進行添加).不再是equals().
     */
    @Test
    public void test1(){
        TreeSet set = new TreeSet();

        //失敗：不能添加不同類的對象
//        set.add(123);
//        set.add(456);
//        set.add("AA");
//        set.add(new User("Tom",12));

            //舉例一：
//        set.add(34);
//        set.add(-34);
//        set.add(43);
//        set.add(11);
//        set.add(8);

        //舉例二：
        set.add(new User("Tom",12));
        set.add(new User("Jerry",32));
        set.add(new User("Jim",2));
        set.add(new User("Mike",65));
        set.add(new User("Jack",33));
        set.add(new User("Jack",56));


        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

    @Test
    public void test2(){
        Comparator com = new Comparator() {
            //按照年齡從小到大排列
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User && o2 instanceof User){
                    User u1 = (User)o1;
                    User u2 = (User)o2;
                    return Integer.compare(u1.getAge(),u2.getAge());
                }else{
                    throw new RuntimeException("輸入的數據類型不匹配");
                }
            }
        };

        TreeSet set = new TreeSet(com);
        set.add(new User("Tom",12));
        set.add(new User("Jerry",32));
        set.add(new User("Jim",2));
        set.add(new User("Mike",65));
        set.add(new User("Mary",33));
        set.add(new User("Jack",33));
        set.add(new User("Jack",56));


        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
```

```
/**
 * @author shkstart
 * @create 2019 下午 3:56
 */
public class User implements Comparable{
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("User equals()....");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() { //return name.hashCode() + age;
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    //按照姓名從大到小排列,年齡從小到大排列
    @Override
    public int compareTo(Object o) {
        if(o instanceof User){
            User user = (User)o;
//            return -this.name.compareTo(user.name);
            int compare = -this.name.compareTo(user.name);
            if(compare != 0){
                return compare;
            }else{
                return Integer.compare(this.age,user.age);
            }
        }else{
            throw new RuntimeException("輸入的類型不匹配");
        }

    }
}
```