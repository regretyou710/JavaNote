# 建立服務提供者介面模組:travel.api
> #### 編譯模組專案
> `D:\Projects\demos\travel.api>javac --module-path mods -d bin src\travel\api\*.java src\module-info.java`

> #### 打包模組專案
> `D:\Projects\demos\travel.api>jar -cvf mods\travel.api.jar -C bin\ .`

# 建立服務定位器模組:travel.reservations
> #### 編譯模組專案
> `D:\Projects\demos\travel.reservations>javac --module-path mods -d bin src\travel\reservations\*.java src\module-info.java`

> #### 打包模組專案
> `D:\Projects\demos\travel.reservations>jar -cvf mods\travel.reservations.jar -C bin\ .`

# 建立用戶端程式模組:travel.buyer
> #### 編譯模組專案
> `D:\Projects\demos\travel.buyer>javac --module-path mods -d bin src\travel\buyer\*.java src\module-info.java`

> #### 打包模組專案
> `D:\Projects\demos\travel.buyer>jar -cvf mods\travel.buyer.jar -C bin\ .`

> #### 執行打包模組專案
> `D:\Projects\demos\travel.buyer>java --module-path mods -m travel.buyer/travel.buyer.TourBuyer`

# 建立服務提供者介面實作模組:travel.agency
> #### 編譯模組專案
> `D:\Projects\demos\travel.agency>javac --module-path mods -d bin src\travel\agency\*.java src\module-info.java`

> #### 打包模組專案
> `D:\Projects\demos\travel.agency>jar -cvf mods\travel.agency.jar -C bin\ .`  
> 將打包後travel.agency模組放到travel.buyer\mods中並再次執行travel.buyer.jar模組