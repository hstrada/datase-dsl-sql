package io.delta.datase

class SqlCreateBuilder(val table: String) {
//    private var columns = mutableListOf<String>()
//
//    fun clauses(vararg columns: String) {
//        this.columns.addAll(columns)
//    }

    fun build(): String {
        return "CREATE TABLE $table"
    }
}

fun create(table: String, initializer: SqlCreateBuilder.() -> Unit): String {
    return SqlCreateBuilder(table).apply(initializer).build()
}

fun main() {
    println(
        create("users") {

        }
    )
}