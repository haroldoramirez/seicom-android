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

    private Contato contato;

    public FormularioHelper (FormularioActivity activity) {
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        campoEmail = (EditText) activity.findViewById(R.id.formulario_email);
        campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);

        contato = new Contato();
    }

    public Contato pegaContato() {

        contato.setNome(campoNome.getText().toString());
        contato.setTelefone(campoTelefone.getText().toString());
        contato.setEmail(campoEmail.getText().toString());
        contato.setEndereco(campoEndereco.getText().toString());

        return contato;
    }

    public void preencheFormulario(Contato contato) {

        campoNome.setText(contato.getNome());
        campoTelefone.setText(contato.getTelefone());
        campoEmail.setText(contato.getEmail());
        campoEndereco.setText(contato.getEndereco());
        this.contato = contato;
    }
}
