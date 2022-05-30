# Building SQL DSL

Just building a custom SQL dsl for fun.

*Focus and persistence.*

## Configure and install

```xml
<dependency>
  <groupId>io.delta</groupId>
  <artifactId>dprop</artifactId>
  <version>0.1.0</version>
</dependency>
```

## :dart: Requirements

- [ ] IntelliJ IDEA or any editor/IDE
- [ ] Java 11+
- [ ] Kotlin

## :computer: Usage

```kotlin
fun main() {
    println(
        query {
            select("id", "name")
            from("users")
        }
    )
}
```
