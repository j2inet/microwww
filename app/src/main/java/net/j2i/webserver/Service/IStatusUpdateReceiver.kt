package net.j2i.webserver.Service

interface IStatusUpdateReceiver {
    fun updateStatus(ipAddress:String, clientCount:Int);
}