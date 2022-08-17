package dev.simonestefani.harmonica

// https://stackoverflow.com/a/52225984/8220327
internal fun ByteArray.toHexString(): String = joinToString("") { "%02x".format(it) }
