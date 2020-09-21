package com.grupo06.tp03.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.grupo06.tp03.AppActivity;
import com.grupo06.tp03.R;
import com.grupo06.tp03.ui.dialog.NewParkingDialogFragment;

import java.util.ArrayList;

import adapters.EstacionamientoAdapter;
import controllers.EstacionamientoController;
import models.EstacionamientoModel;
import models.UsuarioModel;

public class HomeFragment extends Fragment implements NewParkingDialogFragment.NewParkingDialogListener  {
    private ArrayList<EstacionamientoModel> lista;
    private GridView grilla;
    private EstacionamientoAdapter adaptador;
    private HomeViewModel homeViewModel;
    FragmentManager fm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        try
        {
            fm = getParentFragmentManager();
        }
        catch(Exception e){
            e.getMessage();
        }

        FloatingActionButton btn = root.findViewById(R.id.btnNuevoEstacionamiento);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Miau", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                NewParkingDialogFragment nuevoEst = new NewParkingDialogFragment();
                nuevoEst.setListener(HomeFragment.this);
                nuevoEst.show(fm, "prueba");
            }
        });
        grilla = (GridView) root.findViewById(R.id.grdEstacionamientos);
        updateGridView(root);
        grilla.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return removeEstacionamiento(i, view);
            }
        });
        return root;
    }



    public void updateGridView(View root){
        bindData(root.getContext());
        adaptador = new EstacionamientoAdapter(lista, root.getContext());
        grilla.setAdapter(adaptador);
    }

    private Boolean removeEstacionamiento(int i, View view){
        EstacionamientoController controller = new EstacionamientoController(view.getContext());
        if (controller.delete(lista.get(i).getId())) {
            lista.remove(i);
            adaptador.notifyDataSetChanged();
            Toast.makeText(view.getContext(), "Registro eliminado", Toast.LENGTH_SHORT).show();
        }
        return true;
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


    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        //Toast.makeText(this.getContext(), "Angel OK", Toast.LENGTH_SHORT).show();
        updateGridView(this.getView());
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        //Toast.makeText(this.getContext(), "El dialog se cancelo", Toast.LENGTH_SHORT).show();
    }
}