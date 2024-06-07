package io.delta.datase

class CreateWhereBuilder {
    private val reference = mutableListOf<String>()

    infix fun String.id(value: Any) {
        when (value) {
            is Number -> reference += "$this = $value"
            is String -> reference += "$this = '$value'"
        }
    }

    override fun toString(): String {
        return reference.joinToString(", ", "(", ")")
    }

}

/**
 * Sql Update Builder
 */
@SqlDsl
class SqlUpdateBuilder(private val table: String) {
    private var values: String? = null
    private var clause = CreateWhereBuilder()

    infix fun String.eq(target: Any) {
        values = "$this = $target"
    }

    fun where(init: CreateWhereBuilder.() -> Unit) {
        clause = CreateWhereBuilder().apply(init)
    }

    fun build(): String {
        return "UPDATE $table SET $values WHERE $clause"
    }
}

fun update(table: String, initializer: SqlUpdateBuilder.() -> Unit): String {
    return SqlUpdateBuilder(table).also(initializer).build()
}
