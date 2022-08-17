package dev.simonestefani.harmonica

class MyMigration : dev.simonestefani.harmonica.AbstractMigration() {
    override fun up() {
        createTable("accounts") {
            integer("pew", default = 2).primaryKey()
            varchar("foo", 255).nullable()

        }

        createIndex("accounts", "pew")
    }
}
