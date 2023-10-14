You need to know the following facts about enums:
您需要了解以下有關枚舉的事實：

Enum constructor is always private. You cannot make it public or protected. If an enum type has no constructor declarations, then a private constructor that takes no parameters is automatically provided.
枚舉構造函數始終是私有的。 您不能將其公開或保護。 如果枚舉類型沒有建構函數聲明，則會自動提供不含參數的私有建構子。

An enum is implicitly final, which means you cannot extend it.
枚舉是隱式最終的，這意味著您無法擴展它。

You cannot extend an enum from another enum or class because an enum implicitly extends java.lang.Enum. But an enum can implement interfaces.
您不能從另一個枚舉或類別擴展枚舉，因為枚舉隱式擴展了 java.lang.Enum。 但枚舉可以實現介面。

Since enum maintains exactly one instance of its constants, you cannot clone it. You cannot even override the clone method in an enum because java.lang.Enum makes it final.
由於 enum 只維護其常數的一個實例，因此您無法複製它。 您甚至不能重寫枚舉中的克隆方法，因為 java.lang.Enum 使其成為最終方法。

Compiler provides an enum with two public static methods automatically - values() and valueOf(String). The values() method returns an array of its constants and valueOf() method tries to match the String argument exactly (i.e. case sensitive) with an enum constant and returns that constant if successful otherwise it throws java.lang.IllegalArgumentException.
編譯器會自動提供帶有兩個公共靜態方法的枚舉 - value() 和 valueOf(String)。 value() 方法傳回其常數的數組，valueOf() 方法嘗試將 String 參數與枚舉常數完全匹配（即區分大小寫），如果成功則傳回該常數，否則拋出 java.lang.IllegalArgumentException。

By default, an enum's toString() prints the enum name but you can override it to print anything you want.
預設情況下，枚舉的 toString() 會列印枚舉名稱，但您可以覆蓋它以列印您想要的任何內容。

***

The following are a few more important facts about java.lang.Enum which you should know:
以下是您應該了解的有關 java.lang.Enum 的一些更重要的事實：

It implements java.lang.Comparable (thus, an enum can be added to sorted collections such as SortedSet, TreeSet, and TreeMap). The natural order of the enum values is the order in which they are defined i.e. in the order of their ordinal value.
它實作了 java.lang.Comparable（因此，可以將枚舉新增到排序集合，例如 SortedSet、TreeSet 和 TreeMap）。枚舉值的自然順序是它們定義的順序，即按照它們的序數值的順序。

It has a method ordinal(), which returns the index (starting with 0) of that constant i.e. the position of that constant in its enum declaration.
它有一個方法 ordinal()，它會傳回該常數的索引（從 0 開始），即該常數在其枚舉聲明中的位置。

It has a method name(), which returns the name of this enum constant, exactly as declared in its enum declaration.
它有一個方法 name()，它傳回此枚舉常數的名稱，與枚舉聲明中的聲明完全相同。