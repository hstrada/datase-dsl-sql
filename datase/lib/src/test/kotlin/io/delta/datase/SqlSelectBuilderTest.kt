package io.delta.datase

import org.junit.Test
import kotlin.test.assertEquals

class SqlSelectBuilderTest {

    @Test
    fun `when no columns are specified`() {
        val sample = query {
            from("users")
        }
        assertEquals("select * from users", sample)
    }

    @Test
    fun `when columns are specified`() {
        val sample = query {
            select("id", "name")
            from("users")
        }
        assertEquals("select users.id, users.name from users", sample)
    }

    @Test
    fun `when where clause is specified`() {
        val sample = query {
            select("id", "name")
            from("users")
            where {
                "id" eq 1
            }
        }
        assertEquals("select users.id, users.name from users where (id = 1)", sample)
    }

    @Test
    fun `when join clause is specified`() {
        val sample = query {
            select("id", "name")
            from("users")
            join {
                "permissions" compare mapOf("users.permissionId" to "permissions.id")
            }
        }
        assertEquals("select users.id, users.name from users inner join permissions on users.permissionId = permissions.id", sample)
    }

}