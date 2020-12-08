package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONException;
import org.parceler.Parcel;
import org.parceler.Parcels;

import okhttp3.Headers;

public class ComposeActivity extends AppCompatActivity {
    public static final String TAG = "ComposeActivity";
    EditText etCompose;
    Button buttonTweet;
    TwitterClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        etCompose = findViewById(R.id.etCompose);
        buttonTweet = findViewById(R.id.buttonTweet);
        client = TwitterApp.getRestClient(this);

        buttonTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweetStr = etCompose.getText().toString();
                if(tweetStr.isEmpty()){
                    Toast.makeText(ComposeActivity.this, "You shoudl write something", Toast.LENGTH_LONG).show();
                    return;
                }
                if(tweetStr.length() > 140){
                    Toast.makeText(ComposeActivity.this, "Only 140 characters allows!", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(ComposeActivity.this, "Ok got it", Toast.LENGTH_LONG).show();
                client.publishTweet(tweetStr, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        Log.i(TAG, "publish tweet success");
                        try {
                            Tweet tw = Tweet.fromJson(json.jsonObject);
                            Intent intent = new Intent();
                            intent.putExtra("tweet", Parcels.wrap(tw));
                            setResult(RESULT_OK, intent);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                        Log.e(TAG, "publish tweet failed", throwable);
                    }
                });
            }
        });
    }
}