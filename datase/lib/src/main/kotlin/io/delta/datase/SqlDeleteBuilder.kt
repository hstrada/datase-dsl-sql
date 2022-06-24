package io.delta.datase

/**
 * Sql Delete Builder
 */
@SqlDsl
class SqlDeleteBuilder(private val table: String) {
    private var clause: String? = null

    infix fun String.eq(target: Any) {
        clause = "$this = $target"
    }

    fun build(): String {
        return "DELETE FROM $table WHERE $clause"
    }
}

fun delete(table: String, initializer: SqlDeleteBuilder.() -> Unit): String {
    return SqlDeleteBuilder(table).also(initializer).build()
}
