# Exercício 04
Vamos criar um botão na _activity_ __ListaContatosActivity__ com o objetivo de adicionar um contato novo. Para isso será necessário criar uma intenção (__intent__) que vai pular de uma _activity_ para outra.

Depois criaremos uma opção de menu para salvar o contato na __FormularioActivity__.

## 1. Criar um botão para a _activity_ _ListaContatosActivity_
```xml
 <Button
    android:layout_width="56dp"
    android:layout_height="56dp"
    android:text="+"
    android:textColor="#FFFFFF"
    android:textSize="24dp"
    android:layout_alignParentBottom="true"
    android:layout_alignParentRight="true"
    android:layout_marginBottom="16dp"
    android:layout_marginRight="16dp"
    android:background="@drawable/fundo"
    android:elevation="6dp"
    android:stateListAnimator="@null"
    android:id="@+id/lista_novo_contato" />
```

Criar um novo __drawable__ que se encontra dentro do diretório __res__. Vamos chamá-lo de __fundo.xml__.
```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval">

    <solid android:color="#F50057" />

</shape>
```

## 2. Criar uma intenção para trocarmos de _activity_ através do botão criado na _ListaContatosActivity_
```java
Button novoContato = (Button) findViewById(R.id.lista_novo_contato);
novoContato.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intentVaiParaFormulario = new Intent(ListaContatosActivity.this, FormularioActivity.class);
        startActivity(intentVaiParaFormulario);
    }
});
```

## 3. Criar uma opção de menu para _FormularioActivity_
Primeiro é necessário criar um novo diretório dentro de __res__. Esse diretório se chamará __menu__ e então iremos criar um drawable resource file chamado de __menu_formulario.xml__.
```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  xmlns:tools="http://schemas.android.com/tools"
  tools:context="br.com.anglo.agenda.FormularioActivity">

  <item android:id="@+id/menu_formulario_ok"
      android:title="Ok"
      android:icon="@drawable/ic_confirmar"
      app:showAsAction="always" />

</menu>
```

Próximo passo é criar um meno no __FormularioActivity__, 
```java
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_formulario, menu);
    return super.onCreateOptionsMenu(menu);
}
```
E então criar um método que habilitar a opção selecionada e terminar a intenção voltando para nossa activity principal
```java
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
        case R.id.menu_formulario_ok:
            Toast.makeText(FormularioActivity.this, "Contato salvo!", Toast.LENGTH_SHORT).show();
            finish();
    }
    return super.onOptionsItemSelected(item);
}
```

[⇦ Exercício 03](https://github.com/medeirosthiiago/seicom-android/tree/master/exercicios/exer-03)

[⇨ Exerícico 05](https://github.com/medeirosthiiago/seicom-android/tree/master/exercicios/exer-05)
