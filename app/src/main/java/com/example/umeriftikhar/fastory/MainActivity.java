package com.example.umeriftikhar.fastory;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    EditText email,password;
    String emailtxt,passwordtxt,url;
    Button login;
    List<NameValuePair> params;
    ServerRequest sr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login_button);
        url = "http://192.168.1.104:8080/login";
        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                emailtxt = email.getText().toString();
                passwordtxt = password.getText().toString();
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("email", emailtxt));
                params.add(new BasicNameValuePair("password", passwordtxt));
                ServerRequest sr = new ServerRequest();
                //JSONObject json = sr.getJSON("http://10.0.2.2:8080/login",params);
                //JSONObject json = sr.getJSON("http://192.168.1.101:8080/login",params);
                sr.check_json = false;
                boolean user_valid = sr.getJSON("http://192.168.1.104:8080/login",params);

               // if(json != null){
                 if(user_valid){
                    boolean x = false;

                     Intent i=new Intent(MainActivity.this, ProfileActivity.class);
                     Bundle bundle = new Bundle();
                     bundle.putString("user_email", emailtxt);
                     //i.putExtra("useremail", String.valueOf(emailtxt));
                     i.putExtras(bundle);
                     startActivity(i);

                }
            }
        });




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
}
