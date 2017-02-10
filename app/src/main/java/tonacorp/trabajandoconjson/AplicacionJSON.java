package tonacorp.trabajandoconjson;

import android.app.Application;
import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by Tona on 09/02/2017.
 */

public class AplicacionJSON extends Application {

    private final static String URL = "https://api.flickr.com/services/feeds/photos_public.gne?format=json"; //URL del documento JSON.

    public void getData(Context context, ArrayAdapter<String> adapter){
        new ConexionAsync(context, adapter, URL).execute();
    }
}
