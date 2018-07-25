package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import ltd.boku.jokedisplay.JokeDisplayActivity;

import static ltd.boku.jokedisplay.JokeDisplayActivity.JOKES;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        Toast.makeText(this, "Getting Jokes", Toast.LENGTH_SHORT).show();
        new EndPointAsyncTask().execute(3);
       // Intent intent=new Intent(this,JokeDisplayActivity.class);
    }

    public  class EndPointAsyncTask extends AsyncTask<Integer, Void, List<String>> {
        // private jokeTellingApi mJokeTellingApi=null;MyBean
        private MyApi myApiService = null;

        @Override
        protected void onPostExecute(List<String> jokes) {
            for (String joke: jokes){
                Log.d(TAG, "onPostExecute:  Jokes: " +joke);
            }
            Intent intent=new Intent(MainActivity.this,JokeDisplayActivity.class);
            intent.putExtra(JOKES,(Serializable)jokes);
            startActivity(intent);
        }

        @Override
        protected List<String> doInBackground(Integer... integers) {
            if (myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver
                myApiService = builder.build();
            }
            try {
                return myApiService.getJokes(integers[0]).execute().getJokes();
            } catch (IOException e) {
                Log.d(TAG, "doInBackground:  " + e.getMessage());
                return null;
            }
        }

    }

}
