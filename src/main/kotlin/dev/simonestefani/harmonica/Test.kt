package dev.simonestefani.harmonica

class MyMigration : AbstractMigration() {
    override fun up() {
        createTable("accounts") {
            jsonb("foo").nullable()

        }

        addJsonbColumn("accounts", "pew")
    }
}


