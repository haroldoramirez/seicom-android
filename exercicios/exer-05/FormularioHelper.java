package br.com.anglo.agenda;

import android.widget.EditText;

import br.com.anglo.agenda.modelo.Contato;

/**
 * Created by thiago on 11/8/15.
 */
public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoTelefone;
    private final EditText campoEmail;
    private final EditText campoEndereco;

    public FormularioHelper (FormularioActivity activity) {
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        campoEmail = (EditText) activity.findViewById(R.id.formulario_email);
        campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
    }

    public Contato pegaContato() {

        Contato contato = new Contato();
        contato.setNome(campoNome.getText().toString());
        contato.setTelefone(campoTelefone.getText().toString());
        contato.setEmail(campoEmail.getText().toString());
        contato.setEndereco(campoEndereco.getText().toString());

        return contato;
    }
}
