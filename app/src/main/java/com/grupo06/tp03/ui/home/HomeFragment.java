package com.grupo06.tp03.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.grupo06.tp03.AppActivity;
import com.grupo06.tp03.R;

import java.util.ArrayList;

import adapters.EstacionamientoAdapter;
import controllers.EstacionamientoController;
import models.EstacionamientoModel;
import models.UsuarioModel;

public class HomeFragment extends Fragment {
    private ArrayList<EstacionamientoModel> lista;
    private GridView grilla;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        FloatingActionButton btn = root.findViewById(R.id.btnNuevoEstacionamiento);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Miau", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        bindData(root.getContext());
        grilla = (GridView) root.findViewById(R.id.grdEstacionamientos);
        EstacionamientoAdapter adaptador = new EstacionamientoAdapter(lista, root.getContext());
        grilla.setAdapter(adaptador);
        return root;
    }

    private void bindData(Context ctx){
        try {
            AppActivity app = (AppActivity) getActivity();
            UsuarioModel user = app.getUser();
            EstacionamientoController controlador = new EstacionamientoController(ctx);
            lista = controlador.getByUsuario(user.getId());
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

}