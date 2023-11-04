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