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
        assertEquals("select id, name from users", sample)
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
        assertEquals("select id, name from users where (id = 1)", sample)
    }

}