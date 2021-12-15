package net.j2i.webserver.Http

data class Response (
    val status:Status,
    val body:ByteArray
)