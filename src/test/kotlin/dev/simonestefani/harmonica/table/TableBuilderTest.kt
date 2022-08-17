package dev.simonestefani.harmonica.table

import dev.simonestefani.harmonica.table.column.BigIntegerColumn
import dev.simonestefani.harmonica.table.column.IntegerColumn
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class TableBuilderTest : FunSpec({
    test("it can add an integer column") {
        var tb = TableBuilder()
        tb.integer("name")

        var integerColumn = tb.columnList.first() as IntegerColumn
        integerColumn.name shouldBe "name"
        integerColumn.nullable shouldBe false
        integerColumn.default shouldBe null

        tb = TableBuilder()
        tb.integer("name", false, 1)

        integerColumn = tb.columnList.first() as IntegerColumn
        integerColumn.name shouldBe "name"
        integerColumn.nullable shouldBe false
        integerColumn.default shouldBe 1L
    }

    test("it can add a biginteger column") {
        var tb = TableBuilder()
        tb.bigInteger("name")
        var bigInteger = tb.columnList.first() as BigIntegerColumn
        bigInteger.name shouldBe "name"
        bigInteger.nullable shouldBe false
        bigInteger.default shouldBe null

        tb = TableBuilder()
        tb.bigInteger("name", false, 1L)
        bigInteger = tb.columnList.first() as BigIntegerColumn
        bigInteger.name shouldBe "name"
        bigInteger.nullable shouldBe false
        bigInteger.default shouldBe 1L
    }
})
