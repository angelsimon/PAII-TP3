package com.grupo06.tp03.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.grupo06.tp03.AppActivity;
import com.grupo06.tp03.R;

import models.UsuarioModel;

public class GalleryFragment extends Fragment {
    private TextView txtNombreUsuario, txtMail;
    private GalleryViewModel galleryViewModel;

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
    }
    private void bindData(){
        AppActivity app = (AppActivity) getActivity();
        UsuarioModel user = app.getUser();
        txtNombreUsuario.setText(user.getNombreusuario());
        txtMail.setText(user.getMail());
    }

}