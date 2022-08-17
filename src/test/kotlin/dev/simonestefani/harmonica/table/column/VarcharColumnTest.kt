package dev.simonestefani.harmonica.table.column

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class VarcharColumnTest : FunSpec({
    test("it can instantiate a varchar column") {
        val varcharColumn = VarcharColumn("name")

        varcharColumn.name shouldBe "name"
        varcharColumn.hasDefault shouldBe false

        varcharColumn.default = "text"

        varcharColumn.hasDefault shouldBe true
        varcharColumn.sqlDefault shouldBe "'text'"
    }
})
