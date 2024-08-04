### 兩個介面同方法聲明不同的受檢異常

| **介面異常關係** | **I1** | **I2** | **實現類的處理** | **結果** |
|------------------|--------|--------|------------------|----------|
| **有繼承關係** | `IOException` | `FileNotFoundException` | `FileNotFoundException` | 合法 |
| | `IOException` | `FileNotFoundException` | `Exception` | 編譯錯誤 |
| | `IOException` | `FileNotFoundException` | 非受檢異常 | 合法 |
| | `IOException` | `FileNotFoundException` | 不拋異常 | 合法 |
| **無繼承關係** | `ClassNotFoundException` | `IOException` | `ClassNotFoundException, IOException` | 編譯錯誤 |
| | `ClassNotFoundException` | `IOException` | 只拋其中一個異常 | 編譯錯誤 |
| | `ClassNotFoundException` | `IOException` | `Exception` | 編譯錯誤 |
| | `ClassNotFoundException` | `IOException` | 非受檢異常 | 合法 |
| | `ClassNotFoundException` | `IOException` | 不拋異常 | 合法 |

### 兩個介面同方法聲明不同的非受檢異常

| **介面異常關係** | **I1** | **I2** | **實現類的處理** | **結果** |
|------------------|--------|--------|------------------|----------|
| **有/無繼承關係** | 任意非受檢異常 | 任意非受檢異常 | 兩者都拋 | 合法 |
| | 任意非受檢異常 | 任意非受檢異常 | 只拋其中一個異常 | 合法 |
| | 任意非受檢異常 | 任意非受檢異常 | `Exception` | 編譯錯誤 |
| | 任意非受檢異常 | 任意非受檢異常 | 不拋異常 | 合法 |

### 其中一個介面同方法聲明受檢異常

| **實現類的處理** | **結果** |
|------------------|----------|
| 拋出與受檢異常相同的異常 | 編譯錯誤 |
| 拋父類受檢異常 | 編譯錯誤 |
| 拋子類受檢異常 | 編譯錯誤 |
| 不拋異常 | 合法 |
| 拋非受檢異常 | 合法 |

### 其中一個介面同方法聲明非受檢異常

| **實現類的處理** | **結果** |
|------------------|----------|
| 拋子類非受檢異常 | 合法 |
| 拋父類非受檢異常 | 編譯錯誤 |
| 拋受檢異常 | 編譯錯誤 |
| 拋非子類非受檢異常 | 合法 |
| 不拋異常 | 合法 |
