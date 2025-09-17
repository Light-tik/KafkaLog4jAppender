Добавить в pom.xml


    <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

Шаг 2. Добавьте зависимость

	<dependency>
	    <groupId>com.github.User</groupId>
	    <artifactId>Repo</artifactId>
	    <version>Tag</version>
	</dependency>
Вот и всё! При первом запросе проекта JitPack проверяет код, собирает его и предоставляет артефакты сборки (jar, aar).
