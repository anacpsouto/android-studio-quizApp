package com.ana.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView textViewResultado = findViewById(R.id.textViewResultado);
        Button buttonReiniciar = findViewById(R.id.buttonReiniciar);

        // Recebe todas as respostas das Intents
        String respostaPergunta1 = getIntent().getStringExtra(MainActivity.EXTRA_RESPOSTA1);
        String respostaPergunta2 = getIntent().getStringExtra(SecondQuestionActivity.EXTRA_RESPOSTA2);
        String respostaPergunta3 = getIntent().getStringExtra(ThirdQuestionActivity.EXTRA_RESPOSTA3);

        // Verifica quais perguntas foram respondidas
        boolean pergunta2Respondida = getIntent().getBooleanExtra("PERGUNTA2_RESPONDIDA", true);
        boolean pergunta3Respondida = getIntent().getBooleanExtra("PERGUNTA3_RESPONDIDA", true);

        // Constrói o texto do resultado
        String resultado = "RESULTADO DO QUIZ\n\n";

        // Processa a Pergunta 1
        resultado += "Pergunta 1: Qual é a capital de Portugal?\n";
        resultado += "Sua resposta: " + (respostaPergunta1 == null || respostaPergunta1.isEmpty() ? "Nenhuma" : respostaPergunta1) + "\n";
        if ("Lisboa".equals(respostaPergunta1)) {
            resultado += "✓ CORRETA\n\n";
        } else {
            resultado += "✗ INCORRETA (Resposta correta: Lisboa)\n\n";
        }

        // Processa a Pergunta 2 (só se foi respondida)
        if (pergunta2Respondida) {
            resultado += "Pergunta 2: Qual é a capital de França?\n";
            resultado += "Sua resposta: " + (respostaPergunta2 == null || respostaPergunta2.isEmpty() ? "Nenhuma" : respostaPergunta2) + "\n";
            if ("Paris".equals(respostaPergunta2)) {
                resultado += "✓ CORRETA\n\n";
            } else {
                resultado += "✗ INCORRETA (Resposta correta: Paris)\n\n";
            }
        } else {
            resultado += "Pergunta 2: NÃO RESPONDIDA\n\n";
        }

        // Processa a Pergunta 3 (só se foi respondida)
        if (pergunta3Respondida) {
            resultado += "Pergunta 3: Qual é a capital de Espanha?\n";
            resultado += "Sua resposta: " + (respostaPergunta3 == null || respostaPergunta3.isEmpty() ? "Nenhuma" : respostaPergunta3) + "\n";
            if ("Madrid".equals(respostaPergunta3)) {
                resultado += "✓ CORRETA\n\n";
            } else {
                resultado += "✗ INCORRETA (Resposta correta: Madrid)\n\n";
            }
        } else {
            resultado += "Pergunta 3: NÃO RESPONDIDA\n\n";
        }

        // Calcula a pontuação final
        int acertos = 0;
        int totalPerguntas = 1 + (pergunta2Respondida ? 1 : 0) + (pergunta3Respondida ? 1 : 0);

        if ("Lisboa".equals(respostaPergunta1)) acertos++;
        if ("Paris".equals(respostaPergunta2) && pergunta2Respondida) acertos++;
        if ("Madrid".equals(respostaPergunta3) && pergunta3Respondida) acertos++;

        resultado += "PONTUAÇÃO FINAL: " + acertos + "/" + totalPerguntas;

        // Mostra o resultado na tela
        textViewResultado.setText(resultado);

        // Botão para reiniciar o quiz
        buttonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volta para a MainActivity (Pergunta 1)
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                // Limpa a pilha de activities para não acumular
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Fecha esta activity
            }
        });
    }
}