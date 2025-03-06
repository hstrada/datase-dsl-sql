package io.delta.datase

class Join {
    private lateinit var joinTable: String
    infix fun String.compare(columnToCompare: Map<String, String>) {
        println()
        joinTable = "$this on ${columnToCompare.firstNotNullOf { it.key }} = ${columnToCompare.firstNotNullOf { it.value }}"
    }

    override fun toString(): String {
        return joinTable
    }
}

class Where {
    private var clauses = mutableListOf<String>()
    infix fun String.eq(value: Any) {
        when (value) {
            is Number -> clauses += "$this = $value"
            is String -> clauses += "$this = '$value'"
        }
    }

    override fun toString(): String {
        return clauses.joinToString("", "(", ")")
    }
}

/**
 * Sql Select Builder
 */
@SqlDsl
class SqlSelectBuilder {

    private var columns = mutableListOf<String>()
    private lateinit var table: String
    private var where: Where? = null
    private var join: Join? = null

    fun select(vararg columns: String) {
        this.columns.addAll(columns)
    }

    fun from(table: String) {
        this.table = table
    }

    fun where(initializer: Where.() -> Unit) {
        where = Where().apply(initializer)
    }

    fun join(initializer: Join.() -> Unit) {
        join = Join().apply(initializer)
    }

    fun build(): String {

        fun columns(): String {
            if (columns.size == 0) {
                return "*"
            }
            return columns.joinToString { "$table.$it" }
        }

        fun where(): String {
            return where?.let {
                " where $where"
            }.orEmpty()
        }

        fun join(): String {
            return join?.let {
                " inner join $join"
            }.orEmpty()
        }

        return "select ${columns()} from $table${join()}${where()}"
    }

}

fun query(initializer: SqlSelectBuilder.() -> Unit): String {
    return SqlSelectBuilder().apply(initializer).build()
}
