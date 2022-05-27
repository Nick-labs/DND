package com.example.dnd;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class RoomActivity extends AppCompatActivity {
    Button exitButton;
    TextView diceHistory;

    ServerSocket serverSocket;
    Thread Thread1 = null;
    TextView tvIP;
    public static String SERVER_IP = "";
    public static final int SERVER_PORT = 8080;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        tvIP = findViewById(R.id.tvIP);
//        tvMessages = findViewById(R.id.tvMessages);
//        btnSend = findViewById(R.id.btnSend);
        try {
            SERVER_IP = getLocalIpAddress();
            tvIP.setText(SERVER_IP);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Thread1 = new Thread(new Thread1());
        Thread1.start();

        exitButton = findViewById(R.id.exitButton);
        diceHistory = findViewById(R.id.diceHistory);

        exitButton.setOnClickListener(view -> finish());
    }

    private String getLocalIpAddress() throws UnknownHostException {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        assert wifiManager != null;
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipInt = wifiInfo.getIpAddress();
        return InetAddress.getByAddress(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(ipInt).array()).getHostAddress();
    }

    private PrintWriter output;
    private BufferedReader input;

    class Thread1 implements Runnable {
        @Override
        public void run() {
            Socket socket;
            try {
                serverSocket = new ServerSocket(SERVER_PORT);
                runOnUiThread(() -> tvIP.setText("IP: " + SERVER_IP));
                try {
                    socket = serverSocket.accept();
                    output = new PrintWriter(socket.getOutputStream());
                    input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    runOnUiThread(() -> Toast.makeText(RoomActivity.this, "Connected", Toast.LENGTH_SHORT).show());
                    output.flush();
                    new Thread2().start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class Thread2 extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println(1);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String message = input.readLine();
                    System.out.println(message);
                    if (message != null) {
                        runOnUiThread(() ->
                                diceHistory.append(message + "\n"));

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    class Thread3 implements Runnable {
//        private String message;
//
//        Thread3(String message) {
//            this.message = message;
//        }
//
//        @Override
//        public void run() {
//            if (output != null) {
//                output.println(message);
//                output.flush();
//                System.out.println(message);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        diceHistory.append("server: " + message + "\n");
//                    }
//                });
//            }
//        }
//    }
}

