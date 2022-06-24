package io.delta.datase

import org.junit.Test
import kotlin.test.assertEquals

class SqlDeleteBuilderTest {

    @Test
    fun `when delete is specified`() {
        val sample = delete("Users") {
            "id" eq 1
        }
        assertEquals("DELETE FROM Users WHERE id = 1", sample)
    }

}