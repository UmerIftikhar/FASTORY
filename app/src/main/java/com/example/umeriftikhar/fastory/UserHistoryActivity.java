package com.example.umeriftikhar.fastory;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserHistoryActivity extends Activity {

    List<NameValuePair> params;
    ServerRequest sr;
    String url;
    //String email_user;
    TextView test_View;
    String test_string;
    private String sent_userName;
    private static InputStream is = null;
    ListView list;
    ArrayList<HashMap<String, String>> mylistData;
//    private static JSONObject jObj = null;
//    public boolean justTest = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history);

        list = (ListView) findViewById(R.id.listView);
        mylistData = new ArrayList<HashMap<String, String>>();

//        test_View = (TextView)findViewById(R.id.textView4);
//        test_string = test_View.toString();
        //final String email_user= getIntent().getStringExtra("useremail");
        //email_user= getIntent().getStringExtra("useremail");
        Bundle bundle = getIntent().getExtras();
        sent_userName= bundle.getString("user_email");

        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("loggedinUser", sent_userName));
        url = "http://192.168.1.104:8080/getuserhistory";
        ServerRequest sr = new ServerRequest();
        boolean user_valid = sr.getJSON("http://192.168.1.104:8080/getuserhistory",params);

        int x =0;
        int y =0;
        JSONArray getUserHistory = sr.myJsonArray;
        String[] columnTags = new String[] {"col1", "col2", "col3"};
        int[] columnIds = new int[] {R.id.column1, R.id.column2, R.id.column3};

    try {
        for (int i = 0; i < getUserHistory.length(); i++) {
            JSONObject object = getUserHistory.getJSONObject(i);
            JSONObject userRecord = object.getJSONObject("localrecord");
            String commands = userRecord.getString("commands");
            String sampledata1 = userRecord.getString("sampledata1");
            String sampledata2 = userRecord.getString("sampledata2");

            HashMap<String, String> map = new HashMap<String, String>();
            map.put(columnTags[0], commands);
            map.put(columnTags[1], sampledata1);
            map.put(columnTags[2], sampledata2);
            mylistData.add(map);
        }

        SimpleAdapter arrayAdapter = new SimpleAdapter(this, mylistData, R.layout.mylistrow, columnTags , columnIds);
        list.setAdapter(arrayAdapter);
        //JSONObject localRecord = new JSONObject(getUserHistory.getJSONObject(0).getJSONObject("localrecord"));
        //sr.myJsonArray.getJSONObject(0).getJSONObject("localrecord").get("email");
    }
    catch (JSONException e){
        e.printStackTrace();
    }





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_history, menu);
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
}
