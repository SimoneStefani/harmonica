package dev.simonestefani.harmonica.table.column

internal class DecimalColumn(name: String) : AbstractColumn(name) {
    override var sqlDefault: String? = null

    var default: Double?
        get() = sqlDefault?.toDoubleOrNull()
        set(value) {
            sqlDefault = value?.toString()
        }

    var precision: Int? = null
    var scale: Int? = null

}
