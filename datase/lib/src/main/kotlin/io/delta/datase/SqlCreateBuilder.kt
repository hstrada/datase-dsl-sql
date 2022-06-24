package io.delta.datase

class CreateClauseBuilder {
    private val columns = mutableListOf<String>()

    infix fun String.to(target: String) {
        columns += "$this $target"
    }

    override fun toString(): String {
        return columns.joinToString(", ", "(", ")")
    }

}

class SqlCreateBuilder(private val table: String) {

    private var clause = CreateClauseBuilder()

    fun with(init: CreateClauseBuilder.() -> Unit) {
        clause = CreateClauseBuilder().apply(init)
    }

    fun build(): String {
        return "CREATE TABLE $table $clause"
    }

}

fun create(table: String, initializer: SqlCreateBuilder.() -> Unit): String {
    return SqlCreateBuilder(table).apply(initializer).build()
}
