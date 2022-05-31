package io.delta.datase

import org.junit.Test
import kotlin.test.assertEquals

class SqlSelectBuilderTest {

    @Test
    fun `when no columns are specified`() {
        val sample = query {
            from("sample")
        }
        assertEquals("select * from sample", sample)
    }

    @Test
    fun `when columns are specified`() {
        val sample = query {
            select("id")
            from("sample")
        }
        assertEquals("select id from sample", sample)
    }

}