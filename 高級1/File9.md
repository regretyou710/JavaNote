# Files
在Java的`java.nio.file`包中，`Files`類提供了一系列用於操作文件和目錄的靜態方法。以下是一些與你提到的方法相關的說明：

1. **`find`方法：**
   - `Files.find(Path start, int maxDepth, BiPredicate<Path,BasicFileAttributes> matcher, FileVisitOption... options)`方法用於遞歸地查找與指定條件匹配的文件。
   - `start`是開始查找的路徑。第一個參數應該是開始查找的根目錄（或起始路徑），而不是單個文件。
   - `maxDepth`指定遞歸深度的最大值。
   - `matcher`是一個`BiPredicate`，用於指定文件與其屬性是否滿足特定條件。
   - `options`是一個可選的`FileVisitOption`參數，可用於指定文件訪問的一些選項。

2. **`list`方法：**
   - `Files.list(Path dir)`方法用於列舉指定目錄中的條目。
   - `dir`是要列舉的目錄的路徑。
   - 返回一個`Stream<Path>`，你可以使用它來處理列舉的條目。

3. **`lines`方法：**
   - `Files.lines(Path path)`方法用於讀取指定文件的所有行，並返回一個`Stream<String>`，你可以使用它來處理文件的內容。
   - `path`是要讀取的文件的路徑。

4. **`subpath`方法：**
   - `Path subpath(int beginIndex, int endIndex)`方法用於擷取`Path`對象的子路徑。
   - `beginIndex`是子路徑的起始索引。
   - `endIndex`是子路徑的結束索引（不包括）。

簡單的範例：

```java
import java.nio.file.*;
import java.io.IOException;
import java.util.stream.Stream;

public class FilesExample {
    public static void main(String[] args) throws IOException {
        // 使用 find 方法
        Path start = Paths.get("/path/to/start");
        int maxDepth = Integer.MAX_VALUE;
        Stream<Path> pathStream = Files.find(start, maxDepth, (path, attr) -> path.toString().endsWith(".txt"));
        pathStream.forEach(System.out::println);

        // 使用 list 方法
        Path dir = Paths.get("/path/to/directory");
        Stream<Path> listStream = Files.list(dir);
        listStream.forEach(System.out::println);

        // 使用 lines 方法
        Path filePath = Paths.get("/path/to/file.txt");
        Stream<String> linesStream = Files.lines(filePath);
        linesStream.forEach(System.out::println);

        // 使用 subpath 方法
        Path fullPath = Paths.get("/parent/child/grandchild");
        Path subPath = fullPath.subpath(1, 3);
        System.out.println(subPath);
    }
}
```

# Path
在Java的`java.nio.file`包中，`Path`接口提供了一些用於處理文件路徑的方法。以下是 `Path` 中的 `relativize`、`resolve` 和 `resolveSibling` 方法的詳細說明：

1. **`relativize`方法：**
   - `Path relativize(Path other)` 方法用於計算相對於另一個`Path`的相對路徑。
   - 當你有兩個 `Path` 對象，比如 `path1` 和 `path2`，你可以使用 `path1.relativize(path2)` 來計算從 `path1` 到 `path2` 的相對路徑。
   - 如果兩個 `Path` 對象無法相對，則將拋出 `IllegalArgumentException`。其中一個沒有根路徑拋此例外
   - 兩者都有根路徑或都沒有根路徑都不會拋異常

   ```java
   Path path1 = Paths.get("/root/folder1");
   Path path2 = Paths.get("/root/folder2/file.txt");
   Path relativePath = path1.relativize(path2);
   System.out.println(relativePath); // Outputs: "../folder2/file.txt"
   ```

2. **`resolve`方法：**
   - `Path resolve(String other)` 和 `Path resolve(Path other)` 方法用於將兩個 `Path` 對象合併成一個新的 `Path` 對象。
   - 如果 `other` 是絕對路徑，則 `resolve` 方法將返回 `other`，否則將 `other` 附加到當前路徑。
   
   ```java
   Path basePath = Paths.get("/root/folder");
   Path resolvedPath = basePath.resolve("file.txt");
   System.out.println(resolvedPath); // Outputs: "/root/folder/file.txt"
   ```

3. **`resolveSibling`方法：**
   - `Path resolveSibling(String other)` 和 `Path resolveSibling(Path other)` 方法用於將當前 `Path` 的父路徑與 `other` 組合起來，形成新的 `Path` 對象。
   - 這個方法是 `resolve` 的變種，它不直接修改當前 `Path`，而是將 `other` 與當前 `Path` 的父路徑合併。
   
   ```java
   Path basePath = Paths.get("/root/folder/file.txt");
   Path siblingPath = basePath.resolveSibling("newfile.txt");
   System.out.println(siblingPath); // Outputs: "/root/folder/newfile.txt"
   ```

總的來說，`relativize` 用於計算相對路徑，`resolve` 用於合併路徑，而 `resolveSibling` 則是在當前路徑的父路徑上進行合併。