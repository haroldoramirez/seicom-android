# Exercício 03
Este exercício tem como objetivo criar uma nova _activity_ chamada de __FormularioActivity__ que vai disponibilizar uma tela com campos textuais para o usuário inserir os dados do contato.

Feito o layout do nosso formulário, iremos criar um comportamento para o botão salvar, afim dele mostrar uma mensagem (__Toast__) que o contato foi salvo.

## 1. Neste exercício iremos criar uma nova _activity_
Através do menu podemos criá-lo da seguinte maneira:
> File > New > Activity > Empty Activity > FormularioActivity > Finish

## 2. Editar o layout da nova _activity_
Um layout referente a _FormularioActivity_ será criado (_activity\_formulario.xml_), então é necessário adicionar as novas __views__ para editar o texto e um botão para salvar o contato.
```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Nome"
        android:id="@+id/formulario_nome" />

    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Telefone"
        android:id="@+id/formulario_telefone" />

    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Email"
        android:id="@+id/formulario_email" />

    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Endereco"
        android:id="@+id/formulario_endereco" />

    <Button
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="Salvar Contato"
        android:id="@+id/formulario_salvar_contato"/>

</LinearLayout>
```

## 3. Com o layout pronto é preciso criar um comportamento para o botão
Primeiro iremos criar o nosso botão na _activity_ e depois colocá-lo para _escutar_ alguma interação através do método __setOnClickListener__.
Depois utilizaremos uma __Toast__ para mostrar a mensagem de "Contato salvo!".
```java
Button salvarContato = (Button) findViewById(R.id.formulario_salvar_contato);
salvarContato.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(FormularioActivity.this, "Contato salvo!", Toast.LENGTH_SHORT).show();
    }
});
```

[➜ Exercício 04](https://github.com/medeirosthiiago/seicom-android/tree/master/exercicios/exer-04)
