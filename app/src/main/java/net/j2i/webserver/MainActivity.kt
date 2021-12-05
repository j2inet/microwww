package net.j2i.webserver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import net.j2i.webserver.Service.IStatusUpdateReceiver
import net.j2i.webserver.Service.WebServer

class MainActivity : AppCompatActivity() {
    lateinit var webServer:WebServer
    lateinit var txtIpAddress:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.txtIpAddress = findViewById(R.id.txtIpAddress)

        this.webServer = WebServer(8888, this)
        this.webServer.statusReceiver = object:IStatusUpdateReceiver {
            override fun updateStatus(ipAddress:String, clientCount:Int) {
                runOnUiThread {
                    txtIpAddress.text = ipAddress
                }
            }
        }
        this.webServer.start()
    }
}