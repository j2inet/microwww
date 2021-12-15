package net.j2i.webserver.Http

enum class Status(val code:Int, val description:String) {
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request")
}
