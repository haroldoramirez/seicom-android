# Exercício 02

## 1. Vamos atualizar o arquivo _activity\_lista\_contatos_.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <ListView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/lista_contatos" />

</LinearLayout>
```

## 2. No _ListaContatosActivity_ adicionar um array de String e inseri-lo a nossa _ListView_ utilizando um _adapter_
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_formulario);

    String[] contatos = {"Thiago", "João", "Luciano"};
    ListView listaContatos = (ListView) findViewById(R.id.lista_contatos);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contatos);
    listaContatos.setAdapter(adapter);

}
```

[> Exercício 03](https://github.com/medeirosthiiago/seicom-android/tree/master/exercicios/exer-03)
