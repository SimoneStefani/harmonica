package dev.simonestefani.harmonica.table.column

internal class IntegerColumn(name: String) : AbstractColumn(name) {
    override var sqlDefault: String? = null

    var default: Long?
        get() = sqlDefault?.toLongOrNull()
        set(value) {
            sqlDefault = value?.toString()
        }

    var unsigned = false
}
