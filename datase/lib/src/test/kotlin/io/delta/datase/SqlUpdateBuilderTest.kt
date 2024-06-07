package io.delta.datase

import org.junit.Test
import kotlin.test.assertEquals

class SqlUpdateBuilderTest {

    @Test
    fun `when update is specified`() {
        val sample = update("Users") {
            "name" eq "Helena"
            where {
                "id" id 1
            }
        }
        assertEquals("UPDATE Users SET name = Helena WHERE (id = 1)", sample)
    }

}