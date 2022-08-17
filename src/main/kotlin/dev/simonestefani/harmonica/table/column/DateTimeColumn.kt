package dev.simonestefani.harmonica.table.column

internal class DateTimeColumn(name: String) : AbstractDateTimeColumn(name) {
    override var sqlDefault: String? = null
}
