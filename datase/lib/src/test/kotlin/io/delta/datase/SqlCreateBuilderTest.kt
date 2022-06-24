package io.delta.datase

import org.junit.Test
import kotlin.test.assertEquals

class SqlCreateBuilderTest {

    @Test
    fun `when create is specified`() {
        val sample = create("Users") {
            with {
                "Id" to "int not null identity"
                "Name" to "varchar(255) null"
            }
        }
        assertEquals("CREATE TABLE Users (Id int not null identity, Name varchar(255) null)", sample)
    }

}