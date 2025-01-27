package com.example.binderdemo

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.binderdemo.aidl.UserManager
import com.example.binderdemo.model.User
import com.example.binderdemo.ui.theme.BinderDemoTheme

class MainActivity : ComponentActivity() {

    private var userManager: UserManager? = null

    private val connection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d("MainActivity", "onServiceConnected")
            userManager = UserManager.Stub.asInterface(service)
            userManager?.addUser(User("Jacky", "1"))
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d("MainActivity", "onServiceDisconnected")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = { bindService() }) {
                    Text(text = "Bind Service")
                }
            }
        }
    }

    private fun bindService() {
        val intent = Intent("com.example.binderdemo.service.UserService")
        intent.`package` = "com.example.binderdemo"
        val successful = bindService(intent, connection, BIND_AUTO_CREATE)
        Log.d("MainActivity", "bindService successful: $successful")
    }
}

@Composable
fun Greeting(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "Binder服务端",
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BinderDemoTheme {
        Greeting()
    }
}