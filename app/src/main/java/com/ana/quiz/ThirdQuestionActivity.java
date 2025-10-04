package com.ana.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdQuestionActivity extends AppCompatActivity {

    // Constante para identificar a resposta da pergunta 3
    public static final String EXTRA_RESPOSTA3 = "RESPOSTA_PERGUNTA3";

    private RadioGroup radioGroupPergunta3;
    private String respostaSelecionada = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_question);

        // Conecta com os elementos do layout
        radioGroupPergunta3 = findViewById(R.id.radioGroupPergunta3);
        Button buttonMostrarResultado = findViewById(R.id.buttonMostrarResultado);

        // Listener para as opções de resposta da pergunta 3
        radioGroupPergunta3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton7) {
                    respostaSelecionada = "Barcelona";
                } else if (checkedId == R.id.radioButton8) {
                    respostaSelecionada = "Madrid";
                } else if (checkedId == R.id.radioButton9) {
                    respostaSelecionada = "Sevilha";
                }
            }
        });

        // Botão para mostrar o resultado final
        buttonMostrarResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria Intent para a ResultActivity
                Intent intent = new Intent(ThirdQuestionActivity.this, ResultActivity.class);

                // Pega a resposta da pergunta 1 que veio da MainActivity
                String respostaPergunta1 = getIntent().getStringExtra(MainActivity.EXTRA_RESPOSTA1);

                // Envia todas as respostas para a ResultActivity
                intent.putExtra(MainActivity.EXTRA_RESPOSTA1, respostaPergunta1);
                intent.putExtra(EXTRA_RESPOSTA3, respostaSelecionada);
                // Marca que a pergunta 2 NÃO foi respondida
                intent.putExtra("PERGUNTA2_RESPONDIDA", false);

                startActivity(intent);
            }
        });
    }
}