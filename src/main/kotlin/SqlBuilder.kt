@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
annotation class SqlDsl

@SqlDsl
class SqlBuilder {

    private var columns = mutableListOf<String>()
    private lateinit var table: String;

    fun select(vararg columns: String) {
        this.columns.addAll(columns)
    }

    infix fun from(table: String) {
        this.table = table
    }

    fun build(): String {
        fun buildColumns(): String {
            if (columns.size == 0) {
                return "*"
            }
            return columns.joinToString(separator = ", ")
        }
        return "select ${buildColumns()} from $table"
    }

}

fun query(initializer: SqlBuilder.() -> Unit): String {
    return SqlBuilder().apply(initializer).build()
}

fun main() {
    println(
        query {
            select("id", "name")
            from("users")
        }
    )
}