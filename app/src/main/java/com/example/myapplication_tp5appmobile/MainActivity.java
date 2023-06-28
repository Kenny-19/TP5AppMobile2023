package com.example.myapplication_tp5appmobile;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);



        String jsonStr = loadJSONFromAsset("Donnees.json");

        try {
            // Conversion de la chaîne JSON en objet JSON
            JSONObject jsonObj = new JSONObject(jsonStr);


            String name = jsonObj.getString("title");
            String description = jsonObj.getString("data");


            textView.setText("Name: " + name + "\n" + "Description: " + description);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}