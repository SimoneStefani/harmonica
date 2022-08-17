package dev.simonestefani.harmonica.table.column

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class AbstractColumnTest : FunSpec({
    test("it can handle references and defaults") {
        val column = object : AbstractColumn("column") {
            override var sqlDefault: String? = null
        }

        column.hasReference shouldBe false

        column.referenceTable = " "
        column.hasReference shouldBe false

        column.referenceColumn = " "
        column.hasReference shouldBe false

        column.referenceTable = "reference_table"
        column.referenceColumn = "reference_column"
        column.hasReference shouldBe true

        column.hasDefault shouldBe false
        column.sqlDefault = ""
        column.hasDefault shouldBe true
        column.sqlDefault = null
        column.hasDefault shouldBe false
    }
})
