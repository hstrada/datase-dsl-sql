package io.delta.datase

abstract class Clause {
    fun and(init: Clause.() -> Unit) = displayClause(And().apply(init))
    infix fun String.eq(value: Any) = displayClause(Eq(this, value))
    abstract fun displayClause(clause: Clause)
}

class Eq(private val column: String, private val value: Any) : Clause() {
    override fun displayClause(clause: Clause) {
        throw Error("cannot create inline")
    }

    override fun toString() = "$column = $value"
}

class And : Where("AND")

open class Where(private val operator: String) : Clause() {
    private val clauses = mutableListOf<Clause>()

    override fun displayClause(clause: Clause) {
        clauses += clause
    }

    override fun toString(): String {
        return clauses.joinToString(prefix = "(", postfix = ")", separator = " $operator ") {
            "$it"
        }
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

    fun where(init: Clause.() -> Unit) {
        where = And().apply(init)
    }

    fun build(): String {

        fun buildColumns(): String {
            if (columns.size == 0) {
                return "*"
            }
            return columns.joinToString(separator = ", ")
        }

        return "select ${buildColumns()} from $table where $where"
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