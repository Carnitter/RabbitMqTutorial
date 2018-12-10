package com.example.rotto.rabbitmqtutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity implements RecvListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Send send = new Send();
                    Recv recv = new Recv();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();




        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Send send = new Send();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (TimeoutException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
            });
    }

    @Override
    public void onMsgReceive(String message) {
        Log.d("Received", message);
    }
}
