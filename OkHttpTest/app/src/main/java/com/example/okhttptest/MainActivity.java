package com.example.okhttptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendRequest = (Button)findViewById(R.id.send_request);
        responseText = (TextView)findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        if(v.getId() == R.id.send_request)
        {
            sendRequestWithOkHttpConnection();
            Log.d("MainActivity.this","onClick");
        }
    }

    private void sendRequestWithOkHttpConnection()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("https://www.baidu.com/").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("MainActivity.this","responseData"+responseData  );
                    showResponse(responseData);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void showResponse(final String response)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("MainActivity.this","response"+response  );
                responseText.setText(response);
            }
        });
    }
}

