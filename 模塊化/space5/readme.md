# zoo.animal.feeding
> #### 編譯模組專案
> `D:\Projects\demos\zoo.animal.feeding>javac --module-path mods -d src src/zoo/animal/feeding/*.java src/module-info. java`
	
> #### 打包模組專案
> `D:\Projects\demos\zoo.animal.feeding>jar -cvf mods/zoo.animal.feeding.jar -C src/ .`

# zoo.animal.care	
> #### 編譯模組專案
> `D:\Projects\demos\zoo.animal.care>javac --module-path mods -d src src/zoo/animal/care/details/*.java src/zoo/animal/care/medical/*.java src/module-info.java`
	
> #### 打包模組專案
> `D:\Projects\demos\zoo.animal.care>jar -cvf mods/zoo.animal.feeding.jar -C src/ .`

# zoo.animal.shows
> #### 編譯模組專案
> `D:\Projects\demos\zoo.animal.shows>javac --module-path mods -d src src\zoo\animal\shows\content\*.java src\zoo\animal\shows\media\*.java src\zoo\animal\shows\schedule\*.java src\module-info.java`

> #### 打包模組專案
> `D:\Projects\demos\zoo.animal.shows>jar -cvf mods\zoo.animal.shows.jar -C src\ .`
	
# zoo.staff
> #### 編譯模組專案
> `D:\Projects\demos\zoo.staff>javac --module-path mods -d src src\zoo\staff\*.java src\module-info.java`

> #### 打包模組專案
> `D:\Projects\demos\zoo.staff>jar -cvf mods\zoo.staff.jar -C src\ .`