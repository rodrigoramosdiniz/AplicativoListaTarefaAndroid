package com.example.meuprojeto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.meuprojeto.R;
import com.example.meuprojeto.adapters.TarefaAdapter;
import com.example.meuprojeto.dao.TarefaDAO;
import com.example.meuprojeto.model.Tarefa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaActivity extends AppCompatActivity {
    private FloatingActionButton botaoNovaTarefa;
    private Button botaoFeito;
    private ListView listView;
    private TarefaAdapter adapter;
    private TarefaDAO dao;
    private AppCompatActivity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Lista De Afazeres");
        setContentView(R.layout.activity_lista);
        inicializarRefencias();
        inicializarAcoes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setTarefas(dao.getTarefas());
        listView.setAdapter(adapter);
    }

    private void inicializarRefencias() {
        botaoNovaTarefa = findViewById(R.id.botao_nova_tarefa);
        listView = findViewById(R.id.activity_list_list_view);
        adapter = new TarefaAdapter(this);
        dao = new TarefaDAO(getBaseContext());
        listView.setAdapter(adapter);
    }

    private void inicializarAcoes() {
        botaoNovaTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int posicao = position;
                botaoFeito = view.findViewById(R.id.activity_list_item_botao_feito);
                botaoFeito.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ListaActivity.this, "Feito", Toast.LENGTH_SHORT).show();
                        List<Tarefa> tarefas = dao.getTarefas();
                        Tarefa tarefa = tarefas.get(posicao);
                        tarefa.setFeito(1);
                        dao.atualizarTarefa(tarefa);
                        tarefas = dao.getTarefas();
                        adapter.setTarefas(tarefas);
                        listView.setAdapter(adapter);
                    }
                });
            }
        });
    }

}

