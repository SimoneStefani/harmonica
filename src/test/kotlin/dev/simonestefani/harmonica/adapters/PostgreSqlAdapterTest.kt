//package dev.simonestefani.harmonica.adapters
//
//import io.kotest.core.spec.style.FunSpec
//import io.kotest.matchers.shouldBe
//import dev.simonestefani.harmonica.StubConnection
//import dev.simonestefani.harmonica.table.column.AbstractColumn
//import dev.simonestefani.harmonica.table.column.AddingColumnOption
//import dev.simonestefani.harmonica.table.column.BlobColumn
//import dev.simonestefani.harmonica.table.column.IntegerColumn
//
//internal class PostgreSqlAdapterTest : FunSpec({
//    val buildColumnDeclarationFunctionForTest =
//        PostgreSqlAdapter.Companion::class.java.getDeclaredMethod(
//            "buildColumnDeclarationForCreateTableSql",
//            AbstractColumn::class.java
//        ).also { it.isAccessible = true }
//
//    test("it can build a column declaration for integer") {
//        val integerColumn = IntegerColumn("int")
//
//        fun buildIntegerDeclaration() =
//            buildColumnDeclarationFunctionForTest.invoke(PostgreSqlAdapter, integerColumn) as String
//
//        buildIntegerDeclaration() shouldBe "int INTEGER"
//
//        integerColumn.nullable = false
//        buildIntegerDeclaration() shouldBe "int INTEGER NOT NULL"
//
//        integerColumn.default = 1
//        buildIntegerDeclaration() shouldBe "int INTEGER NOT NULL DEFAULT 1"
//
//        integerColumn.nullable = true
//        buildIntegerDeclaration() shouldBe "int INTEGER DEFAULT 1"
//    }
//
//    test("it can add a column for integer") {
//        val connection = StubConnection()
//        val adapter = PostgreSqlAdapter(connection)
//
//        val integerColumn = IntegerColumn("integer")
//
//        adapter.addColumn("table_name", integerColumn, AddingColumnOption())
//
//        connection.executedSqlList.first() shouldBe "ALTER TABLE table_name ADD COLUMN integer INTEGER;"
//    }
//
//    test("it can add a column for blob") {
//        val connection = StubConnection()
//        val adapter = PostgreSqlAdapter(connection)
//
//        val blobColumn = BlobColumn("blob")
//
//        adapter.addColumn("table_name", blobColumn, AddingColumnOption())
//
//        connection.executedSqlList.first() shouldBe "ALTER TABLE table_name ADD COLUMN blob BYTEA;"
//    }
//
//    test("it should drop index") {
//        val connection = StubConnection()
//        val adapter = PostgreSqlAdapter(connection)
//
//        adapter.dropIndex("table_name", "index_name")
//
//        connection.executedSqlList.first() shouldBe "DROP INDEX index_name;"
//    }
//
//    test("it should rename a column") {
//        val connection = StubConnection()
//        val adapter: DbAdapter = PostgreSqlAdapter(connection)
//
//        adapter.renameColumn("table_name", "old_column_name", "new_column_name")
//
//        connection.executedSqlList.first() shouldBe "ALTER TABLE table_name RENAME COLUMN old_column_name TO new_column_name;"
//    }
//
//    test("it should add a foreign key") {
//        val connection = StubConnection()
//        val adapter: DbAdapter = PostgreSqlAdapter(connection)
//
//        adapter.addForeignKey(
//            tableName = "table_name",
//            columnName = "column_name",
//            referencedTableName = "referenced_table_name",
//            referencedColumnName = "referenced_column_name"
//        )
//
//        connection.executedSqlList.first() shouldBe
//                "ALTER TABLE table_name" +
//                " ADD CONSTRAINT table_name_column_name_fkey" +
//                " FOREIGN KEY (column_name)" +
//                " REFERENCES referenced_table_name (referenced_column_name);"
//    }
//})
