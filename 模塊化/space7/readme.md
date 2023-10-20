# 分析為模組化JAR
> #### 編譯專案
> `D:\Projects\demos\zoo.legacy>javac -XDignore.symbol.file src\zoo\legacy\UnsafeBean.java`

> #### 打包專案
> `D:\Projects\demos\zoo.legacy>jar -cvf mods\zoo.legacy.jar .`

> #### 分析依賴關係
> `D:\Projects\demos\zoo.legacy>jdeps mods\zoo.legacy.jar`
> `D:\Projects\demos\zoo.legacy>jdeps -s mods\zoo.legacy.jar`
> `D:\Projects\demos\zoo.legacy>jdeps --jdk-internals mods\zoo.legacy.jar`