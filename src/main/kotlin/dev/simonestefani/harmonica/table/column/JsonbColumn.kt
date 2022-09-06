package dev.simonestefani.harmonica.table.column

internal class JsonbColumn(name: String) : AbstractColumn(name) {
    override var sqlDefault: String? = null

    var default: String? = null
        set(value) {
            field = value
            sqlDefault = value?.let { "'$value'" }
        }
}
