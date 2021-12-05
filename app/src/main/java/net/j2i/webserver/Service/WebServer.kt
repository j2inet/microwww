package net.j2i.webserver.Service

import android.content.Context
import android.net.wifi.WifiManager
import android.text.format.Formatter
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import kotlin.concurrent.thread

class WebServer {

    companion object {
    }

    val port:Int;
    lateinit var receiveThread:Thread
    lateinit var  listenerSocket:ServerSocket;
    var keepRunning = true;
    val context:Context;

    var statusReceiver:IStatusUpdateReceiver



    constructor(port:Int, context: Context) {
        this.port = port;
        this.context = context;
        this.statusReceiver = object : IStatusUpdateReceiver {
            override fun updateStatus(ipAddress: String, clientCount: Int) {
                fun updateStatus(ipAddress: String, clientCount: Int) {

                }
            }
        }
    }

    fun start() {
        keepRunning = true;
        val wifiManager:WifiManager =
            this.context.getSystemService(Context.WIFI_SERVICE) as WifiManager;
        val wifiIpAddress:String = Formatter.formatIpAddress(wifiManager.connectionInfo.ipAddress);
        this.statusReceiver.updateStatus(wifiIpAddress, 0)

        this.listenerSocket = ServerSocket();
        this.listenerSocket.reuseAddress = true;
        this.listenerSocket.bind(InetSocketAddress(wifiIpAddress, this.port))

        this.receiveThread = thread(start = true) {
                this.listenerThread()
        }
        //this.receiveThread.start()
    }

    fun listenerThread() {
        while(keepRunning) {
            var clientSocket: Socket = this.listenerSocket.accept()
            val clientSocketHandler = ClientSocketHandler(clientSocket)
            clientSocketHandler.respondAsync()
        }
    }
}