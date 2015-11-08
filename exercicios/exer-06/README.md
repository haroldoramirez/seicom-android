# Exercício 06

## 1. Criar um objeto DAO (Data Access Object)
Iremos criar uma classe chamada __ContatoDAO__ no seguinte path:
> br > com > anglo > agenda > dao > ContatoDAO

A classe precisa extender de uma outra classe -> __SQLiteOpenHelper__. Depois é preciso implentar os métodos
necessários e um construtor fazendo a alteração dos parâmetros deste construtor.
```java
public class ContatoDAO extends SQLiteOpenHelper{
    public ContatoDAO(Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Contatos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, telefone TEXT, email TEXT, endereco TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Contatos";
        db.execSQL(sql);
        onCreate(db);
    }
}
```

## 2. Alterar o _FormularioActivity_ chamando o objeto DAO
```java
@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                Contato contato = helper.pegaContato();
                ContatoDAO dao = new ContatoDAO(FormularioActivity.this);
                dao.insere(contato);
                dao.close();
                Toast.makeText(FormularioActivity.this, "Contato " + contato.getNome() + " salvo!", Toast.LENGTH_SHORT).show();
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
```

## 3. Criar o método _insere()_ no _ContatoDAO_
```java
public void insere(Contato contato) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("nome", contato.getNome());
        dados.put("telefone", contato.getTelefone());
        dados.put("email", contato.getEmail());
        dados.put("endereco", contato.getEndereco());

        db.insert("Contatos", null, dados);
    }
```

## 4. Instanciar DAO e chamar novo método na _ListaContatosAcitivity_
É preciso remover aquele array de String que tinha, porque agora iremos implentar uma nova lista de contatos.
```java
ContatoDAO dao = new ContatoDAO(this);
List<Contato> contatos = dao.buscaContato();
dao.close();

ListView listaContatos = (ListView) findViewById(R.id.lista_contatos);
ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatos);
listaContatos.setAdapter(adapter);
```

## 5. Na classe _ContatoDAO_ criar o método _buscaContato()_
```java
public List<Contato> buscaContato() {
    String sql = "SELECT * FROM Contatos;";
    SQLiteDatabase db = getReadableDatabase();
    Cursor c = db.rawQuery(sql, null);

    List<Contato> contatos = new ArrayList<>();
    while (c.moveToNext()) {
        Contato contato = new Contato();
        contato.setId(c.getLong(c.getColumnIndex("id")));
        contato.setNome(c.getString(c.getColumnIndex("nome")));
        contato.setTelefone(c.getString(c.getColumnIndex("telefone")));
        contato.setEmail(c.getString(c.getColumnIndex("email")));
        contato.setEndereco(c.getString(c.getColumnIndex("endereco")));

        contatos.add(contato);
    }

    c.close();
    return contatos;
}
```

Lembre-se de reescrever o método __toString()__ na classe Contato
```java
@Override
    public String toString() {
        return getNome();
    }
```

## 6. Refatorar a nossa activity _ListaContatosActivity_ e adicionar no _onResume()_
É preciso refatorar a instância DAO e a listaContatos e chamar a refatoração no __onResume()__.
```java
private void carregaLista() {
    ContatoDAO dao = new ContatoDAO(this);
    List<Contato> contatos = dao.buscaContato();
    dao.close();

    ListView listaContatos = (ListView) findViewById(R.id.lista_contatos);
    ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatos);
    listaContatos.setAdapter(adapter);
}

@Override
protected void onResume() {
    super.onResume();
    carregaLista();
}
```







```java
```
