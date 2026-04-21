# KafkaLog4jAppender

Реализован простой аппендер для log4j2, который будет отправлять логи в топик Kafka. 
Настройки конфигурируемы через log4j2.xml. Проект выложен на github, используется как maven артефакт.

## Чтобы использовать в своём проекте нужно:
1) Добавить в pom.xml
'''xml
   <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
'''

3) Добавьте зависимость
'''xml
	<dependency>
	    <groupId>com.github.User</groupId>
	    <artifactId>Repo</artifactId>
	    <version>Tag</version>
	</dependency>
'''
Вот и всё! При первом запросе проекта JitPack проверяет код, собирает его и предоставляет артефакты сборки (jar, aar).
