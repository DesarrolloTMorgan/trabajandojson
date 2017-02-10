package tonacorp.trabajandoconjson;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listado;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listado = (ListView) findViewById(R.id.listado);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        listado.setAdapter(arrayAdapter);
        listado.setOnItemClickListener(this);
        ((AplicacionJSON) getApplication()).getData(this, arrayAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        String link = arrayAdapter.getItem(i);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(link));
        startActivity(intent);
    }
}
