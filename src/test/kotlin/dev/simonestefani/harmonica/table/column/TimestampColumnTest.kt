package dev.simonestefani.harmonica.table.column

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class TimestampColumnTest : FunSpec({
    test("it can instantiate a timestamp column") {
        val timestampColumn = TimestampColumn("name")

        timestampColumn.name shouldBe "name"
        timestampColumn.hasDefault shouldBe false

        timestampColumn.default = "2018-02-13 11:12:13"

        timestampColumn.hasDefault shouldBe true
        timestampColumn.sqlDefault shouldBe "'2018-02-13T11:12:13'"
    }
})
