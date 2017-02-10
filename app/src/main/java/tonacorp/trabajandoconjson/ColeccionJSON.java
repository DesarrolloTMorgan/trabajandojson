package tonacorp.trabajandoconjson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Tona on 09/02/2017.
 */

public class ColeccionJSON {

    JSONObject object;

    public ColeccionJSON (JSONObject object){
        this.object = object;
    }

    public ArrayList<String> getArrayList() throws JSONException {
        ArrayList<String> data = new ArrayList<String>();
        if(!object.equals(new JSONObject())){
            JSONArray array = object.getJSONArray("items");
            for (int i=0; i<array.length(); i++){
                JSONObject object = array.getJSONObject(i);
                data.add(object.getJSONObject("media").getString("m")); //Esto puede variar según el JSON con el que se esté trabajando
                //Tod o dependerá de la estructura del JSON, en este caso buscamos la clave: "media" y el objeto es: "m"
            }
        }

        return data;
    }
}
