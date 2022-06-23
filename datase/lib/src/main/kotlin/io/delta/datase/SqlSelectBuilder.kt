package io.delta.datase

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

    fun select(vararg columns: String) {
        this.columns.addAll(columns)
    }

    fun from(table: String) {
        this.table = table
    }

    fun where(initializer: Where.() -> Unit) {
        where = Where().apply(initializer)
    }

    fun build(): String {

        fun buildColumns(): String {
            if (columns.size == 0) {
                return "*"
            }
            return columns.joinToString(separator = ", ")
        }

        fun buildClause(): String {
            return where?.let {
                " where $where"
            }.orEmpty()
        }

        return "select ${buildColumns()} from $table${buildClause()}"
    }

}

fun query(initializer: SqlSelectBuilder.() -> Unit): String {
    return SqlSelectBuilder().apply(initializer).build()
}

fun main() {
    println(
        query {
            select("id", "nome")
            from("users")
            where {
                "id" eq 1
            }
        }
    )
}