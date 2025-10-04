package com.ana.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class SecondQuestionActivity extends AppCompatActivity {

    // Constante para identificar a resposta da pergunta 2
    public static final String EXTRA_RESPOSTA2 = "RESPOSTA_PERGUNTA2";

    private RadioGroup radioGroupPergunta2;
    private String respostaSelecionada = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_question);

        // Conecta com os elementos do layout
        radioGroupPergunta2 = findViewById(R.id.radioGroupPergunta2);
        Button buttonMostrarResultado = findViewById(R.id.buttonMostrarResultado);

        // Listener para as opções de resposta da pergunta 2
        radioGroupPergunta2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton4) {
                    respostaSelecionada = "Lyon";
                } else if (checkedId == R.id.radioButton5) {
                    respostaSelecionada = "Paris";
                } else if (checkedId == R.id.radioButton6) {
                    respostaSelecionada = "Marselha";
                }
            }
        });

        // Botão para mostrar o resultado final
        buttonMostrarResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria Intent para a ResultActivity
                Intent intent = new Intent(SecondQuestionActivity.this, ResultActivity.class);

                // Pega a resposta da pergunta 1 que veio da MainActivity
                String respostaPergunta1 = getIntent().getStringExtra(MainActivity.EXTRA_RESPOSTA1);

                // Envia todas as respostas para a ResultActivity
                intent.putExtra(MainActivity.EXTRA_RESPOSTA1, respostaPergunta1);
                intent.putExtra(EXTRA_RESPOSTA2, respostaSelecionada);
                // Marca que a pergunta 3 NÃO foi respondida
                intent.putExtra("PERGUNTA3_RESPONDIDA", false);

                startActivity(intent);
            }
        });
    }
}