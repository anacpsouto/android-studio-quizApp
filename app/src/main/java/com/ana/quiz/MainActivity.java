package com.ana.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Constante para identificar a resposta da pergunta 1 entre Activities
    public static final String EXTRA_RESPOSTA1 = "RESPOSTA_PERGUNTA1";

    private RadioGroup radioGroupPergunta1;
    private String respostaSelecionada = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Define qual layout esta Activity vai usar
        setContentView(R.layout.activity_main);

        // Conecta os elementos do layout com variáveis Java
        radioGroupPergunta1 = findViewById(R.id.radioGroupPergunta1);
        Button buttonPergunta2 = findViewById(R.id.buttonPergunta2);
        Button buttonPergunta3 = findViewById(R.id.buttonPergunta3);

        // Listener que detecta quando o usuário seleciona uma opção
        radioGroupPergunta1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Verifica qual RadioButton foi selecionado e salva a resposta
                if (checkedId == R.id.radioButton1) {
                    respostaSelecionada = "Lisboa";
                } else if (checkedId == R.id.radioButton2) {
                    respostaSelecionada = "Porto";
                } else if (checkedId == R.id.radioButton3) {
                    respostaSelecionada = "Coimbra";
                }
            }
        });

        // Botão para navegar para a Pergunta 2
        buttonPergunta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria uma Intent para iniciar a SecondQuestionActivity
                Intent intent = new Intent(MainActivity.this, SecondQuestionActivity.class);
                // Envia a resposta da pergunta 1 junto com a Intent
                intent.putExtra(EXTRA_RESPOSTA1, respostaSelecionada);
                startActivity(intent);
            }
        });

        // Botão para navegar para a Pergunta 3
        buttonPergunta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirdQuestionActivity.class);
                intent.putExtra(EXTRA_RESPOSTA1, respostaSelecionada);
                startActivity(intent);
            }
        });
    }
}