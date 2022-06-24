---
sidebar_position: 3
---

# Create

`CREATE` is used to create a database or a table.

`with` specifies the name of the table among with the columns names and types.

```java
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