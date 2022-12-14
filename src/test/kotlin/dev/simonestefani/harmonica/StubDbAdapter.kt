package dev.simonestefani.harmonica

import dev.simonestefani.harmonica.adapters.DbAdapter
import dev.simonestefani.harmonica.table.IndexMethod
import dev.simonestefani.harmonica.table.TableBuilder
import dev.simonestefani.harmonica.table.column.AbstractColumn
import dev.simonestefani.harmonica.table.column.AddingColumnOption

internal class StubDbAdapter : DbAdapter() {
    val addingColumnList = mutableListOf<AddingColumn>()
    val addingForeignKeyList = mutableListOf<AddingForeignKey>()

    data class AddingColumn(
        val tableName: String,
        val column: AbstractColumn,
        val option: AddingColumnOption
    )

    data class AddingForeignKey(
        val tableName: String,
        val columnName: String,
        val referencedTableName: String,
        val referencedColumnName: String
    )

    override fun createTable(tableName: String, tableBuilder: TableBuilder) {
    }

    override fun renameTable(oldTableName: String, newTableName: String) {
    }

    override fun createIndex(tableName: String, columnNameArray: Array<String>, unique: Boolean, method: IndexMethod?) {
    }

    override fun renameIndex(tableName: String, oldIndexName: String, newIndexName: String) {
    }

    override fun dropIndex(tableName: String, indexName: String) {
    }

    override fun addColumn(tableName: String, column: AbstractColumn, option: AddingColumnOption) {
        addingColumnList.add(AddingColumn(tableName, column, option))
    }

    override fun addForeignKey(
        tableName: String,
        columnName: String,
        referencedTableName: String,
        referencedColumnName: String
    ) {
        addingForeignKeyList.add(
            AddingForeignKey(
                tableName, columnName, referencedTableName, referencedColumnName
            )
        )
    }

    override fun dropForeignKey(tableName: String, columnName: String, keyName: String) {
        TODO("not implemented")
    }
}
