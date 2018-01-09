package com.vndroids.android.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String server_response;
    public static String TAG="MainActivity";
    String post_url="https://jsonplaceholder.typicode.com/posts";
    ListView listView;
    ArrayList<PostData_Model> al_Data_models;
    DataListAdapter dataListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        al_Data_models=new ArrayList<>();
        dataListAdapter=new DataListAdapter(al_Data_models);
        listView=(ListView)findViewById(R.id.listview);
        listView.setAdapter(dataListAdapter);
        new Postdata().execute(post_url);
    }

    public class Postdata extends AsyncTask<String, Void, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                int responseCode = urlConnection.getResponseCode();

                if(responseCode == HttpURLConnection.HTTP_OK){
                    server_response = readStream(urlConnection.getInputStream());
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return server_response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Log.d(TAG, "onPostExecute: "+result);
            try {
                JSONArray jsonArray=new JSONArray(result);

                for (int i = 0; i <jsonArray.length() ; i++) {
                    JSONObject data_object = jsonArray.getJSONObject(i);
                    String post_id=data_object.getString("id");
                    String user_id=data_object.getString("userId");
                    String title=data_object.getString("title");
                    String description=data_object.getString("title");
                    PostData_Model postData_model=new PostData_Model();
                    postData_model.setPost_id(post_id);
                    postData_model.setPost_description(description);
                    postData_model.setPost_title(title);
                    postData_model.setUser_id(user_id);

                    al_Data_models.add(postData_model);
                }
                dataListAdapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


    }



    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }


}
