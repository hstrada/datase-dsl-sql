---
sidebar_position: 1
---

# Select

`SELECT` is used to select data from a database.

`FROM` specifies the table you want to select and `WHERE` clause any filter that you want to add to your query.

## Sample with Where clause

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
select users.id, users.name from users where (id = 1)
```

## Sample with Join clause

```kotlin
fun main() {
    println(
        query {
            select("id", "name")
            from("users")
            join {
                "permissions" compare mapOf("users.permissionId" to "permissions.id")
            }
        }
    )
}
```

## Expected Result

```sql
select users.id, users.name from users inner join permissions on users.permissionId = permissions.id
```