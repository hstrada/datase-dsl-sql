# Building SQL DSL

Just building a custom SQL dsl for fun.

_Focus and persistence._

## Configure and install

```xml
<dependency>
  <groupId>io.delta</groupId>
  <artifactId>datase</artifactId>
  <version>0.1.1</version>
</dependency>
```

## :dart: Requirements

- [ ] IntelliJ IDEA or any editor/IDE
- [ ] Java 11+
- [ ] Kotlin

## :computer: Usage

### select

```kotlin
fun main() {
    println(
        query {
            select("id", "name")
            from("users")
            where {
                "id" eq 1
            }
        }
    )
}
```

### create

```kotlin
fun main() {
    println(
        create("Users") {
            with {
                "Id" to "int not null identity"
                "Name" to "varchar(255) null"
            }
        }
    )
}
```
