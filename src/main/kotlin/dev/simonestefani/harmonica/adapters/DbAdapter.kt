package dev.simonestefani.harmonica.adapters

import dev.simonestefani.harmonica.table.IndexMethod
import dev.simonestefani.harmonica.table.TableBuilder
import dev.simonestefani.harmonica.table.column.AbstractColumn
import dev.simonestefani.harmonica.table.column.AddingColumnOption
import dev.simonestefani.harmonica.table.column.BigIntegerColumn
import dev.simonestefani.harmonica.table.column.BlobColumn
import dev.simonestefani.harmonica.table.column.BooleanColumn
import dev.simonestefani.harmonica.table.column.DateColumn
import dev.simonestefani.harmonica.table.column.DateTimeColumn
import dev.simonestefani.harmonica.table.column.DecimalColumn
import dev.simonestefani.harmonica.table.column.DoubleColumn
import dev.simonestefani.harmonica.table.column.IntegerColumn
import dev.simonestefani.harmonica.table.column.JsonbColumn
import dev.simonestefani.harmonica.table.column.TextColumn
import dev.simonestefani.harmonica.table.column.TimeColumn
import dev.simonestefani.harmonica.table.column.TimestampColumn
import dev.simonestefani.harmonica.table.column.UuidColumn
import dev.simonestefani.harmonica.table.column.VarcharColumn
import java.io.File
import kotlin.io.path.Path

internal abstract class DbAdapter() {
    /** To show sql before sql execution */
    var dispSql = false
    var isReview = false

    val statements = mutableListOf<String>()

    fun createTable(tableName: String, block: TableBuilder.() -> Any) {
        createTable(tableName, TableBuilder().apply { block() })
    }

    protected fun execute(sql: String) {
        statements.add(sql)
        if (dispSql || isReview) println(sql)
    }

    fun persist(migrationName: String, destinationPath: String) {
        val migrationQuery = statements.joinToString("\n")
        val path = Path(destinationPath, migrationName)
        File("$path.sql").writeText(migrationQuery)
        statements.clear()
    }

    abstract fun createTable(tableName: String, tableBuilder: TableBuilder)

    fun dropTable(tableName: String) {
        execute("DROP TABLE $tableName")
    }

    abstract fun createIndex(
        tableName: String, columnNameArray: Array<String>, unique: Boolean = false,
        method: IndexMethod? = null
    )

    abstract fun dropIndex(tableName: String, indexName: String)

    abstract fun addColumn(
        tableName: String, column: AbstractColumn, option: AddingColumnOption
    )

    fun removeColumn(tableName: String, columnName: String) {
        execute("ALTER TABLE $tableName DROP COLUMN $columnName;")
    }

    abstract fun renameTable(oldTableName: String, newTableName: String)

    fun renameColumn(
        tableName: String, oldColumnName: String, newColumnName: String
    ) {
        execute(
            "ALTER TABLE $tableName" +
                    " RENAME COLUMN $oldColumnName TO $newColumnName;"
        )
    }

    abstract fun renameIndex(
        tableName: String, oldIndexName: String, newIndexName: String
    )

    abstract fun addForeignKey(
        tableName: String, columnName: String,
        referencedTableName: String, referencedColumnName: String
    )

    fun dropForeignKey(
        tableName: String, columnName: String
    ) {
        dropForeignKey(
            tableName, columnName,
            buildForeignKeyName(tableName, columnName)
        )
    }

    abstract fun dropForeignKey(
        tableName: String, columnName: String,
        keyName: String
    )

    protected open fun buildForeignKeyName(
        tableName: String, columnName: String
    ) = "${tableName}_${columnName}_fkey"

    internal abstract class CompanionInterface {
        protected open fun sqlType(column: AbstractColumn): String {
            return when (column) {
                is IntegerColumn -> "INTEGER"
                is BigIntegerColumn -> "BIGINT"
                is VarcharColumn -> "VARCHAR"
                is DecimalColumn -> "DECIMAL"
                is BooleanColumn -> "BOOL"
                is TextColumn -> "TEXT"
                is BlobColumn -> "BLOB"
                is DateColumn -> "DATE"
                is TimeColumn -> "TIME"
                is DateTimeColumn -> "DATETIME"
                is TimestampColumn -> "TIMESTAMP"
                is UuidColumn -> "UUID"
                is DoubleColumn -> "DOUBLE PRECISION"
                is JsonbColumn -> "JSONB"
                else -> throw Exception()
            }
        }

        protected abstract fun sqlIndexMethod(method: IndexMethod?): String?
    }
}
