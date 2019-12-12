package com.example.meuprojeto.adapters;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.meuprojeto.R;
import com.example.meuprojeto.model.Tarefa;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class TarefaAdapter extends BaseAdapter {

    private List<Tarefa> tarefas;
    private AppCompatActivity activity;
    private TextView descricao;
    private TextView data;
    private Button nv_botao_feito_;

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public TarefaAdapter(AppCompatActivity activity) {
        this.activity = activity;
        this.tarefas = new ArrayList<Tarefa>();
    }

    @Override
    public int getCount() {
        return tarefas.size();
    }

    @Override
    public Object getItem(int position) {
        return tarefas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tarefas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = activity.getLayoutInflater().inflate(R.layout.activity_list_item, parent, false);
        descricao = view.findViewById(R.id.activity_list_item_campo_descricao);
        data = view.findViewById(R.id.activity_list_item_campo_data);
        nv_botao_feito_ = view.findViewById(R.id.activity_list_item_botao_feito);

        nv_botao_feito_.setFocusable(false);
        nv_botao_feito_.setClickable(false);

        Tarefa tarefa = tarefas.get(position);

        descricao.setText("Tarefa "+tarefa.getId()+": "+tarefa.getDescricao());
        data.setText("Data: ("+tarefa.getData()+")");

        Date atual = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");

        Date tarefaData = new Date();

        try {
            tarefaData = s.parse(tarefa.getData());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(atual.after(tarefaData))
            view.setBackgroundColor(Color.parseColor("red"));


        if (tarefa.getFeito() == 1) {
            view.setBackgroundColor(Color.parseColor("green"));
            nv_botao_feito_.setVisibility(View.INVISIBLE);
        }

        return view;

    }


}



