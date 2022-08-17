package dev.simonestefani.harmonica.table

enum class IndexMethod {
    BTree,
    Hash,

    /** Only for PostgreSQL */
    Gist,
    SpGist,
    Gin,
    BRin
}
