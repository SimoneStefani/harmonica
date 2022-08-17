package dev.simonestefani.harmonica.table.column

internal class TimestampColumn(name: String) : AbstractDateTimeColumn(name), TimeZoneInterface {
    override var withTimeZone = false
}
