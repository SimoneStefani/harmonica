package dev.simonestefani.harmonica

open class DbConfig() {
    lateinit var dbms: Dbms
    var host: String = "127.0.0.1"
    var port: Int = -1
    lateinit var dbName: String
    lateinit var user: String
    lateinit var password: String
    var sslmode: Boolean = false

    constructor(block: DbConfig.() -> Unit) : this() {
        this.block()

        if (port == -1) {
            port = when (dbms) {
                Dbms.PostgreSQL -> 5432
                Dbms.MySQL -> 3306
                Dbms.SQLite -> 0
                Dbms.Oracle -> 1521
                Dbms.SQLServer -> 0
                Dbms.H2 -> 0
            }
        }
    }

    companion object {
        fun create(block: DbConfig.() -> Unit): DbConfig {
            return DbConfig(block)
        }
    }
}

operator fun DbConfig.invoke(block: DbConfig.() -> Unit): DbConfig {
    this.block()
    return this
}
