package tonacorp.trabajandoconjson;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Tona on 09/02/2017.
 */

public class ConexionAsync extends AsyncTask<Void, Void, Void> {

    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private String url;
    private ProgressDialog progressDialog;
    private Context context;


    public ConexionAsync (Context context, ArrayAdapter<String> adapter, String url){
        this.adapter = adapter;
        this.url = url;
        progressDialog = new ProgressDialog(context);
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(context.getResources().getString(R.string.mensaje));
        progressDialog.setTitle(R.string.titulo);
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        for(String link : arrayList){
            adapter.add(link);
            adapter.notifyDataSetChanged(); //le indicamos al adapter que su contenido ha cambiado para que actualice el listView
            progressDialog.dismiss();
        }
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Void... params) {
        ConectorJSON conectorJSON = new ConectorJSON(url);
        try {
            JSONObject object = conectorJSON.execute();
            arrayList = new ColeccionJSON(object).getArrayList();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
