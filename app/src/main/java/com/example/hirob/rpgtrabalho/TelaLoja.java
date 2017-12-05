package com.example.hirob.rpgtrabalho;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hirob.rpgtrabalho.db.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class TelaLoja extends AppCompatActivity {
    ListView lv;
    List<String> initialList;
    ArrayAdapter mAdapter;
    DataBaseHelper mBancoDeDados;
    int numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_loja);

        lv = (ListView) findViewById(R.id.listViewLoja);

        initialList = new ArrayList<>();

        initialList.add("Poção - 7 Gold - 10 cura");
        initialList.add("Poção Ataque - 10 Gold");
        initialList.add("Poção Defesa - 10 Gold");
        initialList.add("Poção Bônus HP - 12 Gold");

        mAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                initialList);
        lv.setAdapter(mAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String texto = (String) adapterView.getItemAtPosition(i);
                numero = i;
                AlertDialog.Builder builder = new AlertDialog.Builder(TelaLoja.this);
                builder.setMessage("Você tem certeza que deseja comprar '"+texto+"' ?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mBancoDeDados = new DataBaseHelper(TelaLoja.this);
                        Personagem personagem = mBancoDeDados.pegarPersonagemPronto();

                        switch (numero) {
                            case 0: //Se ele explorar, vai procurar um inimigo novo
                                personagem.setHpAtual(
                                        personagem.getHpAtual()+10
                                );
                                break;
                            case 1:
                                personagem.setAtkMax(
                                        personagem.getAtkMax()+1
                                );
                                personagem.setAtkMin(
                                        personagem.getAtkMin()+1
                                );
                                break;
                            case 2:
                                personagem.setDefense(
                                        personagem.getDefense()+1
                                );
                                break;
                            case 3:
                                personagem.setHpTotal(
                                        personagem.getHpTotal()+1
                                );
                                break;
                        }

                        mBancoDeDados.salvarPersonagem(personagem);
                    }
                });

                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

//    public void clickLv (View v) {
//        LinearLayout vwParentRow = (LinearLayout)v.getParent();
//        int position=(Integer) v.getTag();
//
//        Context contexto = getApplicationContext();
//        int duracao = Toast.LENGTH_SHORT;
//        Toast toast = Toast.makeText(contexto, position+ "", duracao);
//        toast.show();
//    }


}
