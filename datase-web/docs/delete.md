---
sidebar_position: 5
---

# Update

`DELETE` is used to delete records in a table.

`WHERE` clause any filter that you want to add to your query and don't forget to use it in order to achieve and delete only the record that you want to delete otherwise it will reflect in other items.

## Sample

```kotlin
fun main() {
    println(
        query {
            delete("Users") {
                "id" eq 1
            }
        }
    )
}
```

## Expected Result

```sql
DELETE FROM Users WHERE id = 1
```