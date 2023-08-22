```
/**
 * 一、枚舉類的使用
 * 1.枚舉類的理解：類的對象只有有限個，確定的。我們稱此類為枚舉類
 * 2.當需要定義一組常量時，強烈建議使用枚舉類
 * 3.如果枚舉類中只有一個對象，則可以作為單例模式的實現方式。
 *
 * 二、如何定義枚舉類
 * 方式一：jdk5.0之前，自定義枚舉類
 * 方式二：jdk5.0，可以使用enum關鍵字定義枚舉類
 *
 * 三、Enum類中的常用方法：
 *    values()方法：返回枚舉類型的對象數組。該方法可以很方便地遍歷所有的枚舉值。
 *    valueOf(String str)：可以把一個字符串轉為對應的枚舉類對象。要求字符串必須是枚舉類對象的“名字”。如不是，會有運行時異常：IllegalArgumentException。
 *    toString()：返回當前枚舉類對象常量的名稱
 *
 * 四、使用enum關鍵字定義的枚舉類實現接口的情況
 *   情況一：實現接口，在enum類中實現抽象方法
 *   情況二：讓枚舉類的對象分別實現接口中的抽象方法
 *
 * @author shkstart
 * @create 2019 上午 10:17
 */
public class SeasonTest {

    public static void main(String[] args) {
        Season spring = Season.SPRING;
        System.out.println(spring);

    }

}
//自定義枚舉類
class Season{
    //1.聲明Season對象的屬性:private final修飾
    private final String seasonName;
    private final String seasonDesc;

    //2.私有化類的構造器,並給對象屬性賦值
    private Season(String seasonName,String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //3.提供當前枚舉類的多個對象：public static final的
    public static final Season SPRING = new Season("春天","春暖花開");
    public static final Season SUMMER = new Season("夏天","夏日炎炎");
    public static final Season AUTUMN = new Season("秋天","秋高氣爽");
    public static final Season WINTER = new Season("冬天","冰天雪地");

    //4.其他訴求1：獲取枚舉類對象的屬性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }
    //4.其他訴求1：提供toString()
    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}
```