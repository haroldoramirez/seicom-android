# Exercício 07
Nesta atividade iremos criar um menu de contexto na _activity_ __ListaContatos__, com a finalidade de remover um contato.

Depois iremos preencher o formulário do contato para alterações.

## 1. Declarar listaContatos como um campo global na nossa _activity_ e registrar ela como um menu de contexto
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lista_contatos);

    listaContatos = (ListView) findViewById(R.id.lista_contatos);

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
```

Criar um menu de contexto __onCreateContextMenu()__ e especificar o item selecionado
```java
@Override
public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
    MenuItem remover = menu.add("Remover");
    remover.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            Contato contato = (Contato) listaContatos.getItemAtPosition(info.position);
            Toast.makeText(ListaContatosActivity.this, contato.getNome() + " removido!", Toast.LENGTH_SHORT).show();
            return false;
        }
    });
}
```

## 2. Para a remoção do contato é necessário chamar o DAO dentro do meu _onCreateContextMenu()_
```java
ContatoDAO dao = new ContatoDAO(ListaContatosActivity.this);
dao.remove(contato);
dao.close();
```

Crie o método __remove()__ na classe __ContatoDAO__
```java
public void remove(Contato contato) {
    SQLiteDatabase db = getWritableDatabase();
    String[] params = {contato.getId().toString()};
    db.delete("Contatos", "id = ?", params);
}
```

Lembre-se de carregar a lista após o __dao.close()__ dentro do __onCreateContextMenu()__
```java
ContatoDAO dao = new ContatoDAO(ListaContatosActivity.this);
dao.remove(contato);
dao.close();
carregaLista();
```

## 3. No método _onCreate()_ da _activity_ _ListaContatosActivity_ é necessário escutar um click no item
```java
listaContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
        Contato contato = (Contato) listaContatos.getItemAtPosition(position);
        Intent intentVaiProFormulario = new Intent(ListaContatosActivity.this, FormularioActivity.class);
        intentVaiProFormulario.putExtra("contato", contato);
        startActivity(intentVaiProFormulario);
    }
});
```

Após isso, é preciso ir no __FormularioActivity__ para receber a intenção com o contato. Iremos fazer essas alterações
no método __onCreate()__ da _activity_
```java
Intent intent = getIntent();
Contato contato = (Contato) intent.getSerializableExtra("contato");
if (contato != null) {
    helper.preencheFormulario(contato);
}
```

Criar o método __preencheFormulario()__ na classe __FormularioHelper__.
É preciso também instanciar o contato no construtor e não mais na __pegaContato()__.
```java
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
```

## 4. Para não inserir um novo Contato e sim atualizá-lo é preciso alterar o _FormularioActivity_
No método __onOptionsItemSelected(MenuItem item)__ é preciso adicionar a seguinte condição
```java
Contato contato = helper.pegaContato();
ContatoDAO dao = new ContatoDAO(FormularioActivity.this);

if (contato.getId() != null) {
    dao.altera(contato);
} else {
    dao.insere(contato);
}
dao.close();
```

Agora iremos criar o método __altera()__ na classe __ContatoDAO__. Para isso é necessário extrair o __Content Values__
para um método separado e chamá-lo novamente dentro do método __altera()__
```java
public void altera(Contato contato) {
    SQLiteDatabase db = getWritableDatabase();
    ContentValues dados = getContentValuesContato(contato);
    String[] params = {contato.getId().toString()};
    db.update("Contatos", dados, "id = ?", params);
}
```

Método __Content Values__ extraído:
```java
@NonNull
private ContentValues getContentValuesContato(Contato contato) {
    ContentValues dados = new ContentValues();
    dados.put("nome", contato.getNome());
    dados.put("telefone", contato.getTelefone());
    dados.put("email", contato.getEmail());
    dados.put("endereco", contato.getEndereco());
    return dados;
}
```

[⇦ Exercício 06](https://github.com/medeirosthiiago/seicom-android/tree/master/exercicios/exer-06)
