package com.grupo06.tp03.ui.gallery;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.grupo06.tp03.AppActivity;
import com.grupo06.tp03.R;

import java.util.ArrayList;

import controllers.LoginController;
import models.LoginModel;
import models.UsuarioModel;

public class GalleryFragment extends Fragment {
    private TextView txtNombreUsuario, txtMail;
    private ListView lista;
    private GalleryViewModel galleryViewModel;
    private ArrayList<LoginModel> logins;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        /*final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        bindControls(root);
        bindData();
        return root;
    }

    private void bindControls(View root){
        this.txtNombreUsuario = (TextView) root.findViewById(R.id.txtNombreUsuario_mc);
        this.txtMail = (TextView) root.findViewById(R.id.txtMail_mc);
        this.lista = (ListView) root.findViewById(R.id.lstLogins);
    }
    private void bindData(){
        AppActivity app = (AppActivity) getActivity();
        UsuarioModel user = app.getUser();
        txtNombreUsuario.setText(user.getNombreusuario());
        txtMail.setText(user.getMail());
        LoginController loginController = new LoginController(this.getContext());
        logins = loginController.getLast5ByUsuario(user.getId());
        updateLista(logins);
    }
    private void updateLista(ArrayList<LoginModel> listado){
        if (listado.size() == 0){
            return;
        }
        String[] items = new String[listado.size()];
        int i=0;
        for (LoginModel elemento:listado) {
            items[i] = elemento.getFechaYHora("dd/MM/yyyy HH:mm");
            i++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, items);
        lista.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}