package dev.simonestefani.harmonica

import dev.simonestefani.harmonica.table.column.TextColumn
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class AbstractMigrationTest : FunSpec({
    test("it can create a table") {
        val migration = StubMigration()

        migration.createTable("user") {
            tableNameIsInPluralForm shouldBe false
        }

        migration.tableNameIsInPluralForm = true
        migration.createTable("user") {
            tableNameIsInPluralForm shouldBe true
        }
    }

    test("it can add a text column") {
        val migration = StubMigration()
        migration.addTextColumn(
            "table_name",
            "column_name",
            nullable = false,
            default = "default value",
            first = true,
            justBeforeColumnName = "previous_column"
        )
        var textAddingColumn = migration.adapter.addingColumnList.first()
        textAddingColumn.tableName shouldBe "table_name"

        var textColumn = textAddingColumn.column as TextColumn
        textColumn.name shouldBe "column_name"
        textColumn.nullable shouldBe false
        textColumn.default shouldBe "default value"

        val addingOption = textAddingColumn.option
        addingOption.first shouldBe true
        addingOption.justBeforeColumn shouldBe "previous_column"

        val rawSql = RawSql("'A' || 'B'")
        migration.addTextColumn("table_name", "column_name", default = rawSql)

        textAddingColumn = migration.adapter.addingColumnList.last()
        textColumn = textAddingColumn.column as TextColumn
        textColumn.sqlDefault shouldBe rawSql.sql
    }
})
