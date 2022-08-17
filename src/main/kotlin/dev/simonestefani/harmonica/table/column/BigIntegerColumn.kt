package dev.simonestefani.harmonica.table.column

// Exposed take a Long type as BigInteger in database, actually BigInteger can be bigger than Long
// but for now we limit it to the size of a Long
internal class BigIntegerColumn(name: String) : AbstractColumn(name) {
    override var sqlDefault: String? = null

    var default: Long?
        get() = sqlDefault?.toLongOrNull()
        set(value) {
            sqlDefault = value?.toString()
        }

    var unsigned = false
}
