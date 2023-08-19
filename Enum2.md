```
/**
 * 使用enum關鍵字定義枚舉類
 * 說明：定義的枚舉類默認繼承於java.lang.Enum類
 *
 * @author shkstart
 * @create 2019 上午 10:35
 */
public class SeasonTest1 {
    public static void main(String[] args) {
        Season1 summer = Season1.SUMMER;
        //toString():返回枚舉類對象的名稱
        System.out.println(summer.toString());

//        System.out.println(Season1.class.getSuperclass());
        System.out.println("****************");
        //values():返回所有的枚舉類對象構成的數組
        Season1[] values = Season1.values();
        for(int i = 0;i < values.length;i++){
            System.out.println(values[i]);
            values[i].show();
        }
        System.out.println("****************");
        Thread.State[] values1 = Thread.State.values();
        for (int i = 0; i < values1.length; i++) {
            System.out.println(values1[i]);
        }

        //valueOf(String objName):返回枚舉類中對象名是objName的對象。
        Season1 winter = Season1.valueOf("WINTER");
        //如果沒有objName的枚舉類對象，則拋異常：IllegalArgumentException
//        Season1 winter = Season1.valueOf("WINTER1");
        System.out.println(winter);
        winter.show();
    }
}

interface Info{
    void show();
}

//使用enum關鍵字枚舉類
enum Season1 implements Info{
    //1.提供當前枚舉類的對象需在enum結構一開始聲明，多個對象之間用","隔開，末尾對象";"結束
    SPRING("春天","春暖花開"){
        @Override
        public void show() {
            System.out.println("春天在哪里？");
        }
    },
    SUMMER("夏天","夏日炎炎"){
        @Override
        public void show() {
            System.out.println("寧夏");
        }
    },
    AUTUMN("秋天","秋高氣爽"){
        @Override
        public void show() {
            System.out.println("秋天不回來");
        }
    },
    WINTER("冬天","冰天雪地"){
        @Override
        public void show() {
            System.out.println("大約在冬季");
        }
    };

    //2.聲明Season對象的屬性:private final修飾
    private final String seasonName;
    private final String seasonDesc;

    //2.私有化類的構造器,並給對象屬性賦值

    private Season1(String seasonName,String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //4.其他訴求1：獲取枚舉類對象的屬性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

//    //4.其他訴求1：提供toString()
//    //因為enum繼承於java.lang.Enum類所以toString()輸出不是地址值而是枚舉對象
//    @Override
//    public String toString() {
//        return "Season1{" +
//                "seasonName='" + seasonName + '\'' +
//                ", seasonDesc='" + seasonDesc + '\'' +
//                '}';
//    }


//  所有的枚舉類都重寫相同show方法
//    @Override
//    public void show() {
//        System.out.println("這是一個季節");
//    }
}
```