# Exercício 05

## 1. Precisamos agora criar uma classe _FormularioHelper_ que irá nos ajudar a persistir os dados no aplicativo
Precisamos intanciar uma classe para nos ajudar na persistência dos dados.
Essa classe irá se chamar __FormularioHelper__ e seu construtor deve ser desenvolvido da seguinte maneira:
```java
public FormularioHelper (FormularioActivity activity) {
    campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
    campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
    campoEmail = (EditText) activity.findViewById(R.id.formulario_email);
    campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
}
```
## 2. É preciso agora criar uma classe para o nosso Contato
Criar a classe Contato com o seguinte path:
> br > com > agenda > modelo > Contato

Agora é preciso declarar os atributos de um contato. Acrescentamos um atributo __id__ pois utilizaremos ele no nosso
banco de dados.
```java
private Long id;
private String nome;
private String telefone;
private String email;
private String endereco;
```
Os getters e setters podem ser gerados através do atalho __Alt + Insert__

## 3. Depois criamos um método _pegaContato()_ com a finalidade de pegar as informações de um contato
Este método irá instanciar um novo contato e depois setar os nomes dele utilizando os campos criados no construtor.
```java
public Contato pegaContato() {

    Contato contato = new Contato();
    contato.setNome(campoNome.getText().toString());
    contato.setTelefone(campoTelefone.getText().toString());
    contato.setEmail(campoEmail.getText().toString());
    contato.setEndereco(campoEndereco.getText().toString());

    return contato;
}
```

## 4. Na _FormularioActivity_ é preciso instanciar o _FormularioHelper_ e aplicar o método criado

```java
private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);
    }
```

Aplicar o método __pegaContato()__ e utilizar os métodos do __contato__ dentro do _Toast_:
```java
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
        case R.id.menu_formulario_ok:
            Contato contato = helper.pegaContato();
            Toast.makeText(FormularioActivity.this, "Contato " + contato.getNome() + " salvo!", Toast.LENGTH_SHORT).show();
            finish();
            break;
    }
    return super.onOptionsItemSelected(item);
}
```
