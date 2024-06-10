---
sidebar_position: 4
---

# Update

`UPDATE` is used to update records in a table.

`WHERE` clause any filter that you want to add to your query and don't forget to use it in order to achieve and update only the record that you want to update.

## Sample

```kotlin
fun main() {
    println(
        query {
            update("Users") {
                "nome" eq "Helena"
                where {
                    "id" id 1
                }
            }
        }
    )
}
```

## Expected Result

```sql
UPDATE Users SET nome = Helena WHERE id = 1
```