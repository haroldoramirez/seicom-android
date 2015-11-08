# Exercício 03

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

```java
Button salvarContato = (Button) findViewById(R.id.formulario_salvar_contato);
salvarContato.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(FormularioActivity.this, "Contato salvo!", Toast.LENGTH_SHORT).show();
    }
});
```
