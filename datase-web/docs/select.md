---
sidebar_position: 1
---

# Select

`SELECT` is used to select data from a database.

`FROM` specifies the table you want to select and `WHERE` clause any filter that you want to add to your query.

## Sample

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

## Expected Result

```sql
select id, name from users where (id = 1)
```