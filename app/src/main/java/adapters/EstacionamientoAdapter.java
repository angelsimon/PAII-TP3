package adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.grupo06.tp03.R;

import java.util.ArrayList;

import models.EstacionamientoModel;

public class EstacionamientoAdapter extends BaseAdapter {
    private ArrayList<EstacionamientoModel> elementos;
    private Context ctx;

    public EstacionamientoAdapter(ArrayList<EstacionamientoModel> elementos, Context ctx) {
        this.elementos = elementos;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return elementos.size();
    }

    @Override
    public EstacionamientoModel getItem(int i) {
        return elementos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View vista = view;
        if (vista == null){
            vista = inflater.inflate(R.layout.estacionamiento_grid_template,null);
        }

        TextView patente = (TextView) vista.findViewById(R.id.txtPatente);
        TextView tiempo = (TextView) vista.findViewById(R.id.txtTiempo);
        patente.setText(getItem(i).getPatente());
        tiempo.setText(getItem(i).getDuracion().toString() + " minutos");

        /// Código para que aparezcan los estacionamientos eliminados como baja lógica
        /*if (getItem(i).getEstado() == false){
            vista.findViewById(R.id.card).setBackgroundColor(Color.argb(25, 255, 0,0));
        }*/

        return vista;
    }

}
