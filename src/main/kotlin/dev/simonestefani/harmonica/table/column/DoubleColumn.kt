package dev.simonestefani.harmonica.table.column

internal class DoubleColumn(name: String) : AbstractColumn(name) {
    override var sqlDefault: String? = null

    var default: Double?
        get() = sqlDefault?.toDoubleOrNull()
        set(value) {
            sqlDefault = value?.toString()
        }

    var unsigned = false
}
