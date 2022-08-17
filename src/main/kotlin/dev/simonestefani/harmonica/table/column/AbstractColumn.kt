package dev.simonestefani.harmonica.table.column

import dev.simonestefani.harmonica.Index

internal typealias Type = Int

internal abstract class AbstractColumn(val name: String) {
    val indexList: MutableList<Index> = mutableListOf()

    fun index() {
        indexList.add(Index())
    }

    var primaryKey = false

    var nullable = false

    abstract var sqlDefault: String?

    val hasDefault: Boolean
        get() = sqlDefault != null

    var referenceTable: String? = null
    var referenceColumn: String? = null

    val hasReference: Boolean
        get() = !(referenceTable.isNullOrBlank() || referenceColumn.isNullOrBlank())

    var comment: String? = null

    val hasComment: Boolean
        get() = comment != null
}
