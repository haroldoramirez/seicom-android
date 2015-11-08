package br.com.anglo.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListaContatosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        String[] contatos = {"Thiago", "João", "Luciano", "Thiago", "João", "Luciano", "Thiago", "João", "Luciano", "Thiago", "João", "Luciano"};
        ListView listaContatos = (ListView) findViewById(R.id.lista_contatos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contatos);
        listaContatos.setAdapter(adapter);

        Button novoContato = (Button) findViewById(R.id.lista_novo_contato);
        novoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiParaFormulario = new Intent(ListaContatosActivity.this, FormularioActivity.class);
                startActivity(intentVaiParaFormulario);
            }
        });
    }
}
