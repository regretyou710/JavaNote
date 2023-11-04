# 關於使用Java編譯模塊化代碼的一些命令行選項的說明
1. --module-source-path：此選項用於指定模塊源代碼文件的位置。它應該指向包含模塊信息模塊（module-info.java）的目錄的父目錄。例如，如果你的模塊名為moduleA，那麽該模塊的模塊信息模塊應該位於moduleA目錄中，如果moduleA目錄存在於src目錄中，那麽--module-source-path應該包含src目錄，即--module-source-path src。

   如果moduleA依賴於另一個名為moduleB的模塊，且moduleB目錄存在於src2目錄中，你也可以將該目錄添加到--module-source-path中，例如--module-source-path src;src2。如果模塊B的源代碼在src2中正確組織，javac將編譯src2中所需的文件。

2. -d：此選項在使用--module-source-path選項時是必需的。它用於指定輸出目錄。這是javac將生成模塊的基於包的目錄結構和源文件的類文件的目錄。例如，如果指定out作為輸出目錄，javac將在out下創建一個與模塊名相同的目錄，並在該目錄下創建適當的基於包的目錄結構的類文件。

3. --module或-m：此選項用於編譯特定模塊的所有源文件。此選項在你想一次性編譯模塊的所有文件而不需要逐個列出模塊的源文件時很有幫助。例如，如果模塊A中有兩個Java文件，存儲在moduleA\a\A1.java和moduleA\a\A2.java下，你可以使用以下命令同時編譯它們：javac --module-source-path src -d out --module moduleA。Javac將找出moduleA下的所有Java源文件並將它們全部編譯。它將在-d選項中指定的輸出目錄（即out）下創建類文件。因此，out目錄現在將包含兩個類文件 - moduleA/a/A1.class和moduleA/a/A2.class。

4. --module-path或-p：此選項指定模塊編譯所依賴的其他模塊的位置，非常靈活。你可以在這里指定模塊化JAR文件的爆炸式模塊目錄、包含模塊化JAR的目錄或特定的模塊化JAR文件。例如，如果你要編譯moduleA，並且它依賴於位於thirdpartymodules目錄中的另一個名為abc.util的模塊，該模塊以utils.jar打包，則你的module-path可以是thirdpartymodules或thirdpartymodules/utils.jar。以下兩個命令都將起作用：

   ```
   javac --module-source-path src --module-path thirdpartymodules -d out --module moduleA
   ```

   和

   ```
   javac --module-source-path src --module-path thirdpartymodules/utils.jar -d out --module moduleA
   ```

   注意：如果你的模塊依賴於非模塊化的第三方JAR，你需要執行兩個操作 -
   - 將該第三方JAR放入--module-path中。
   - 將非模塊化的JAR放入--module-path將使該JAR被加載為“自動模塊”。該模塊的名稱被假定與JAR的名稱相同，去除任何版本號。例如，如果將mysql-driver-6.0.jar放入--module-path，它將作為名為mysql.driver的自動模塊加載。模塊名稱的派生方式在java.lang.module.ModuleFinder的JavaDoc中有詳細說明，但對於考試而言，只需記住連字符會轉換為點，版本號和擴展部分會被移除。
   - 在你的模塊的module-info中添加`requires <module-name>;`條款。

5. -classpath：此選項用於編譯非模塊化代碼。如果你正在編譯常規的非模塊化代碼，但該代碼依賴於某些類，你可以使用-classpath選項將這些類或JAR包放在類路徑上。
   注意：這個選項對於編譯模塊化代碼沒有幫助，因為模塊的類無法看到類路徑上的類。模塊化代碼只能看到其他模塊化代碼。因此，非模塊化類必須轉換為“自動模塊”並放在--module-path中，如上面所述。

# 以下是有關在包含在模塊中的類運行時的三個命令行選項的解釋：
1. --module-path或-p：此選項指定運行所需的模塊的位置。此選項非常靈活。你可以在這里指定已解壓的模塊目錄、包含模塊化JAR文件的目錄，甚至具體的模塊或非模塊JAR文件。路徑可以是絕對路徑或相對於當前目錄的路徑。例如，--module-path c:/javatest/output/mathutils.jar 或 --module-path mathutils.jar。

   你還可以指定模塊文件的位置。例如，如果你的模塊名為abc.math.utils，且此模塊存儲在c:\javatest\output中，那麽你可以使用：--module-path c:/javatest/output。請記住，c:\javatest\output目錄必須包含abc.math.utils目錄，並且模塊文件（包括module-info.class）必須以適當的目錄結構存放在abc.math.utils目錄下。

   你可以用路徑分隔符（在Windows上為分號，*nix上為冒號）分隔多個JAR文件或模塊位置。

   注意：-p是--module-path的縮寫形式（請注意單破折號和雙破折號的區別）。

2. --module或-m：此選項指定你要運行的模塊。例如，如果你想運行abc.math.utils模塊中的abc.utils.Main類，你應該寫--module abc.math.utils/abc.utils.Main。如果模塊JAR文件在其MANIFEST.MF文件中指定了Main-Class屬性，你可以在--module選項中省略主類名稱。例如，你可以寫--module abc.math.utils而不是--module abc.math.utils/abc.utils.Main。

   注意：-m是--module的縮寫形式（請注意單破折號和雙破折號的區別）。

   因此，
   ```
   java --module-path mathutils.jar --module abc.math.utils/abc.utils.Main
   ```
   等同於
   ```
   java -p mathutils.jar -m abc.math.utils/abc.utils.Main
   ```

   注意：也可以完全忽略模塊選項，將模塊化代碼視為非模塊化。例如，如果要使用舊的-classpath選項運行相同的類，可以這樣做：
   ```
   java -classpath mathutils.jar abc.utils.Main
   ```

3. -classpath：請記住，模塊化代碼不能訪問-classpath上的代碼，但"automatic modules"是此規則的例外。當非模塊化的JAR文件放在--module-path上時，它變成了一個"automatic module"，但仍然可以訪問所有模塊化和非模塊化的代碼。換句話說，來自"automatic module"的類可以訪問位於--module-path上的類（只能訪問被模塊導出的類），以及在-classpath上的類，而無需任何"requires"條款（請記住"automatic modules"中沒有module-info）。

   因此，如果你的模塊化JAR A依賴於非模塊化的JAR B，你必須將非模塊化的JAR B放在--module-path上。你還必須在模塊A的module-info中添加適當的requires條款，否則你的模塊將無法成功編譯。此外，如果非模塊化的JAR B依賴於另一個非模塊化的JAR C，那麽非模塊化的JAR C可以放在-classpath或-module-path上。