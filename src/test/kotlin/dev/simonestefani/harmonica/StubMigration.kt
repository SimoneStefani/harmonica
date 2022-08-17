package dev.simonestefani.harmonica

internal class StubMigration : dev.simonestefani.harmonica.AbstractMigration() {
    private val delegateAdapterField =
        dev.simonestefani.harmonica.AbstractMigration::class.java.getDeclaredField("adapter\$delegate")
            .also { it.isAccessible = true }

    val adapter = StubDbAdapter()

    init {
        connection = StubConnection()
        delegateAdapterField.also { field ->
            field.isAccessible = true
            field.set(this as dev.simonestefani.harmonica.AbstractMigration, lazyOf(adapter))
        }
    }
}
