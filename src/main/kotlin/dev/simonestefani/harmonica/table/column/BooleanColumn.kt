package dev.simonestefani.harmonica.table.column


internal class BooleanColumn(name: String) : AbstractColumn(name) {
    override var sqlDefault: String? = null

    var default: Boolean? = null
        set(value) {
            field = value
            sqlDefault = value?.let { if (it) "TRUE" else "FALSE" }
        }
}
