package com.example.meuprojeto.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.meuprojeto.R;
import com.example.meuprojeto.dao.TarefaDAO;
import com.example.meuprojeto.model.Tarefa;

public class FormularioActivity extends AppCompatActivity
{
    private Button botaoSalvar;
    private EditText campoDescricao;
    private DatePicker campoData;
    private TarefaDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        inicializarDao();
        inicializarReferencias();
        inicializarNovaAcoes();
    }



    private void inicializarReferencias(){
        botaoSalvar = findViewById(R.id.botao_salvar);
        campoDescricao = findViewById(R.id.campo_descricao);
        campoData = findViewById(R.id.campo_data);
    }

    private void inicializarNovaAcoes(){
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tarefa tarefa = new Tarefa();

                int dia = campoData.getDayOfMonth();
                int mes = campoData.getMonth()+1;
                int ano = campoData.getYear();

                tarefa.setDescricao(campoDescricao.getText().toString());
                tarefa.setData(dia+"/"+mes+"/"+ano);
                dao.addTarefa(tarefa);
                Toast toast = Toast.makeText(FormularioActivity.this,"Salvo!",Toast.LENGTH_LONG);
                toast.show();
                finish();
            }
        });
    }

    private void inicializarDao(){
        dao = new TarefaDAO(getBaseContext());
    }
}

