package com.cyberpunktech.elevator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    public static String elevatorIP = "192.168.2.172";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataToServer(elevatorIP, "elevator");
            }
        });
    }

    private void sendDataToServer(String IP, String message)
    {
        try
        {
            // need host and port, we want to connect to the ServerSocket at port 7777
            Socket socket = new Socket();
            socket.setSoTimeout(800);
            socket.connect(new InetSocketAddress(IP, 80), 800);
            System.out.println("Connected!");

            // get the output stream from the socket.
            OutputStream outputStream = socket.getOutputStream();
            // create a data output stream from the output stream so we can send data through it
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            System.out.println("Sending string to the ServerSocket");

            // write the message we want to send
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush(); // send the message
            dataOutputStream.close(); // close the output stream when we're done.

            System.out.println("Closing socket.");
            socket.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}