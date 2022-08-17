package dev.simonestefani.harmonica.table.column

import java.util.UUID

internal class UuidColumn(name: String) : AbstractColumn(name) {
    override var sqlDefault: String? = null

    var default: UUID? = null
        set(value) {
            field = value
            sqlDefault = value?.let { "'$value'" }
        }
}
