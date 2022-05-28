# Building SQL DSL

Just building a custom SQL dsl for fun.

*Focus and persistence*

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