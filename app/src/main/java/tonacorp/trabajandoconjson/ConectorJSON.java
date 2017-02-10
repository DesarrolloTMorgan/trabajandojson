package tonacorp.trabajandoconjson;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


/**
 * Created by Tona on 09/02/2017.
 */

public class ConectorJSON {

    private String url;

    public ConectorJSON(String url){
        this.url = url;
    }

    public JSONObject execute() throws ClientProtocolException, IOException, IllegalStateException, JSONException{
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        HttpResponse response = client.execute(post);
        String feed = inputStreamToString(response.getEntity().getContent());
        String documentoJSON = feed.split("jsonFlickrFeed\\(")[1]; //Objeto JSON
        JSONObject object = new JSONObject(documentoJSON.substring(0, documentoJSON.length() - 1));

        return object;
    }

    private String inputStreamToString (InputStream inputStream) throws UnsupportedEncodingException{
        String linea = "";
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);

        try {
            while (((linea = bufferedReader.readLine())) != null){
                stringBuilder.append(linea.trim());
            }
        } catch (Exception ex){
            Log.v("Aviso ", ex.toString());
        }

        return stringBuilder.toString();
    }
}
