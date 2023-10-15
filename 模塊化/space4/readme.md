# 編譯模組專案
`D:\Projects\demos\zoo.animal.feeding>javac --module-path mods -d src src/zoo/animal/feeding/*.java src/module-info.java`
- 等價  
    1. `D:\Projects\demos\zoo.animal.feeding>javac -p mods -d src src/zoo/animal/feeding/*.java src/module-info.java`
    2. `D:\Projects\demos\zoo.animal.feeding>javac --class-path mods -d src src/zoo/animal/feeding/*.java src/module-info.java`
    3. `D:\Projects\demos\zoo.animal.feeding>javac -classpath mods -d src src/zoo/animal/feeding/*.java src/module-info.java`
    4. `D:\Projects\demos\zoo.animal.feeding>javac -cp mods -d src src/zoo/animal/feeding/*.java src/module-info.java`

# 執行模組專案
`D:\Projects\demos\zoo.animal.feeding>java --module-path src -m zoo.animal.feeding/zoo.animal.feeding.Task`
- 等價  
    1. `D:\Projects\demos\zoo.animal.feeding>java -p src -m zoo.animal.feeding/zoo.animal.feeding.Task`

# 打包模組專案
`D:\Projects\demos\zoo.animal.feeding>jar -cvf mods/zoo.animal.feeding.jar -C src/ .`
	
# 執行打包模組專案
`D:\Projects\demos\zoo.animal.feeding>java --module-path mods -m zoo.animal.feeding/zoo.animal.feeding.Task`
