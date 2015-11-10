package br.com.anglo.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.anglo.agenda.dao.ContatoDAO;
import br.com.anglo.agenda.modelo.Contato;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Contato contato = (Contato) intent.getSerializableExtra("contato");
        if (contato != null) {
            helper.preencheFormulario(contato);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                Contato contato = helper.pegaContato();
                ContatoDAO dao = new ContatoDAO(FormularioActivity.this);

                if (contato.getId() != null) {
                    dao.altera(contato);
                } else {
                    dao.insere(contato);
                }
                dao.close();

                Toast.makeText(FormularioActivity.this, "Contato " + contato.getNome() + " salvo!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
