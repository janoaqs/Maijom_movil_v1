package com.example.maijomv100.recursos;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class TablaIntegrantes {

    private TableLayout tablaFicha;
    private Context contexto;
    private String[]header;
    private ArrayList<String[]>datos;
    private TableRow rowTR;
    private TextView celdaTV;
    private int indexC;
    private int indexR;

    public TablaIntegrantes(TableLayout tablaFicha, Context contexto) {
        this.tablaFicha = tablaFicha;
        this.contexto = contexto;
    }

    public void AgregarHeader(String[]header) {
        this.header=header;
        crearHeader();
    }

    public void AgregarDatos(ArrayList<String[]>datos,int size) {
        this.datos=datos;
        createDataTable(size);
    }

    private void nuevaRow(){
        rowTR=new TableRow(contexto);
    }

    private void nuevaCell(){
        celdaTV=new TextView(contexto);
        celdaTV.setGravity(Gravity.CENTER);
        celdaTV.setTextSize(18);
        celdaTV.setTextColor(Color.WHITE);
    }

    private void crearHeader(){
        indexC=0;
        nuevaRow();
        while(indexC<header.length){
            nuevaCell();
            celdaTV.setText(header[indexC++]);
            rowTR.addView(celdaTV,newTableRowParams());
        }
        tablaFicha.addView(rowTR);
    }

    private void createDataTable(int size){
        String info;

        for(indexR=1;indexR<=size;indexR++){
            nuevaRow();
            for(indexC=0;indexC<header.length;indexC++){
                nuevaCell();
                String[] columnas=datos.get(indexR-1);
                info=(indexC<columnas.length)?columnas[indexC]:"";
                celdaTV.setText(info);
                rowTR.addView(celdaTV,newTableRowParams());
            }
            tablaFicha.addView(rowTR);
        }

    }




    private TableRow GetRow(int index){
        return (TableRow)tablaFicha.getChildAt(index);
    }

    private TextView GetCell(int rowIndex, int columIndex){
        rowTR=GetRow(rowIndex);
        return (TextView) tablaFicha.getChildAt(columIndex);
    }

    private TableRow.LayoutParams newTableRowParams(){
        TableRow.LayoutParams parametros=new TableRow.LayoutParams();
        parametros.setMargins(3,3,3,3);
        parametros.weight=1;
        return parametros;
    }




}
