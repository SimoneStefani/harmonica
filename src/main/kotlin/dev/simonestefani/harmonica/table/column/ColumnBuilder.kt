package dev.simonestefani.harmonica.table.column

class ColumnBuilder internal constructor(
    internal val column: AbstractColumn
) {
    fun refer(tableName: String, columnName: String? = null): ColumnBuilder {
        column.let {
            it.referenceTable = tableName
            it.referenceColumn = columnName
        }
        return this
    }

    fun comment(text: String): ColumnBuilder {
        column.comment = text
        return this
    }

    fun nullable(): ColumnBuilder {
        column.nullable = true
        return this
    }

    fun primaryKey(): ColumnBuilder {
        column.primaryKey = true
        return this
    }

    internal fun build(): AbstractColumn {
        return column
    }
}
