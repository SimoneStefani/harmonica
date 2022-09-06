package dev.simonestefani.harmonica

import io.github.classgraph.ClassGraph
import io.github.classgraph.ClassInfo
import kotlin.reflect.full.createInstance

class Migrator(block: Config.() -> Unit) {
    val config: Config = Config().apply(block)

    fun migrate() {
        val migrations: List<dev.simonestefani.harmonica.AbstractMigration> =
            ClassGraph().disableNestedJarScanning().enableClassInfo()
                .acceptPackages(config.packageScan).scan()
                .getSubclasses(dev.simonestefani.harmonica.AbstractMigration::class.java.name)
                .map { classInfo -> classInfo.load() }

        migrations.forEach { it.persist(config.destinationPath) }
    }

    class Config {
        lateinit var destinationPath: String
        lateinit var packageScan: String
    }

    inline fun <reified T : Any> ClassInfo.load(): T {
        return loadClass().kotlin.let { it.objectInstance ?: it.createInstance() } as T
    }
}


fun main() {
    Migrator {
        packageScan = "dev.simonestefani.harmonica"
        destinationPath = "src/main/resources/migrations"
    }.migrate()
}
