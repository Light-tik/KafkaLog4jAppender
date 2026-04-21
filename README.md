# KafkaLog4jAppender

Простой кастомный appender для Log4j2, который отправляет логи в Kafka-топик.  
Все настройки конфигурируются через `log4j2.xml`.
Проект опубликован на GitHub и доступен как Maven-артефакт через JitPack.

## Подключение к проекту

1. Добавьте JitPack в `pom.xml`

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
2. Добавьте зависимость
```xml
<dependency>
    <groupId>com.github.User</groupId>
    <artifactId>Repo</artifactId>
    <version>Tag</version>
</dependency>
```
Вот и всё! При первом запросе проекта JitPack проверяет код, собирает его и предоставляет артефакты сборки (jar, aar).
