package br.com.anglo.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.anglo.agenda.dao.ContatoDAO;
import br.com.anglo.agenda.modelo.Contato;

public class ListaContatosActivity extends AppCompatActivity {

    private ListView listaContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        listaContatos = (ListView) findViewById(R.id.lista_contatos);

        listaContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Contato contato = (Contato) listaContatos.getItemAtPosition(position);
                Intent intentVaiProFormulario = new Intent(ListaContatosActivity.this, FormularioActivity.class);
                intentVaiProFormulario.putExtra("contato", contato);
                startActivity(intentVaiProFormulario);
            }
        });

        Button novoContato = (Button) findViewById(R.id.lista_novo_contato);
        novoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiParaFormulario = new Intent(ListaContatosActivity.this, FormularioActivity.class);
                startActivity(intentVaiParaFormulario);
            }
        });

        registerForContextMenu(listaContatos);
    }

    private void carregaLista() {
        ContatoDAO dao = new ContatoDAO(this);
        List<Contato> contatos = dao.buscaContato();
        dao.close();

        ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatos);
        listaContatos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem remover = menu.add("Remover");
        remover.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Contato contato = (Contato) listaContatos.getItemAtPosition(info.position);

                ContatoDAO dao = new ContatoDAO(ListaContatosActivity.this);
                dao.remove(contato);
                dao.close();
                carregaLista();

                Toast.makeText(ListaContatosActivity.this, contato.getNome() + " removido!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
