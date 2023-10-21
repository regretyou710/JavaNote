# 建立服務提供者介面模組:travel.api
> #### 將travel.agency.jar加入mods
> #### 編譯模組專案(中文註解會造成錯誤)
> `D:\Projects\demos\travel.mix>javac --module-path mods -d bin src\travel\mix\*.java src\module-info.java`

> #### 打包模組專案
> `D:\Projects\demos\travel.mix>jar -cvf mods\travel.mix.jar -C bin\ .`

> #### 執行打包模組專案
> `D:\Projects\demos\travel.mix>java --module-path mods -m travel.mix/travel.mix.TourPriceCheck`