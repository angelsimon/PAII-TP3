package com.grupo06.tp03.ui.dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.grupo06.tp03.AppActivity;
import com.grupo06.tp03.R;
import com.grupo06.tp03.ui.home.HomeFragment;
import com.grupo06.tp03.ui.home.HomeViewModel;

import classes.Validaciones;
import controllers.EstacionamientoController;
import models.EstacionamientoModel;
import models.UsuarioModel;

public class NewParkingDialogFragment extends DialogFragment {

    private EditText txtPatente, txtTiempo;
    private NewParkingDialogViewModel mViewModel;
    private EstacionamientoModel estacionamiento;
    private EstacionamientoController controlador;
    private AppActivity app;
    private NewParkingDialogListener listener;

    public static NewParkingDialogFragment newInstance() {
        return new NewParkingDialogFragment();
    }

    public interface NewParkingDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    public void setListener(NewParkingDialogListener listener){
        this.listener = listener;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            listener = (NewParkingDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            //Toast.makeText(this.getContext(), "No implementa", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(NewParkingDialogViewModel.class);
        View root = inflater.inflate(R.layout.new_parking_dialog_fragment, container, false);
        app = (AppActivity) getActivity();
        controlador = new EstacionamientoController(root.getContext());
        //bindData(root.getContext());
        return root;
    }
    /*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewParkingDialogViewModel.class);
        // TODO: Use the ViewModel
    }
    */

    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.new_parking_dialog_fragment, null));
        //builder.setTitle("Registrar estacionamiento");
        //builder.setPositiveButton("REGISTRAR", new DialogInterface.OnClickListener() {
        /*builder.setPositiveButton("REGISTRAR", null);
        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        NewParkingDialogFragment.this.getDialog().cancel();
                    }
                });*/
        // Add action buttons
           builder.setPositiveButton("Registrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    /*if (validate()){
                        if(controlador.guardar(bindData())){
                            app.showToast("El estacionamiento fue guardado con éxito");
                            limpiarCampos();
                            listener.onDialogPositiveClick(NewParkingDialogFragment.this);
                            NewParkingDialogFragment.this.getDialog().dismiss();
                            //NewParkingDialogFragment.this.getDialog().cancel();
                        }
                        else{
                            //app.showToast("No se ha podido guardar el estacionamiento");
                            limpiarCampos();
                        }
                    }*/
                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    listener.onDialogNegativeClick(NewParkingDialogFragment.this);
                    NewParkingDialogFragment.this.getDialog().cancel();
                }
            });

        final Dialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(final DialogInterface dialogShow) {
                Dialog d = (Dialog) dialogShow;
                txtPatente = (EditText) d.findViewById(R.id.txtPatente);
                txtTiempo = (EditText) d.findViewById(R.id.txtTiempo);
                Button b = ((AlertDialog)dialogShow).getButton(AlertDialog.BUTTON_POSITIVE);
                b.setText("Registrar");
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (validate()){
                            if(controlador.guardar(bindData())){
                                app.showToast("El estacionamiento fue guardado con éxito");
                                limpiarCampos();
                                listener.onDialogPositiveClick(NewParkingDialogFragment.this);
                                NewParkingDialogFragment.this.getDialog().dismiss();
                                //NewParkingDialogFragment.this.getDialog().cancel();
                            }
                            else{
                                app.showToast("No se ha podido guardar el estacionamiento");
                                limpiarCampos();
                            }
                        }
                    }
                });
            }
        });
        //setFocus(txtPatente);
        return dialog;
    }

    private EstacionamientoModel bindData (){
        try{
            UsuarioModel user = app.getUser();
            EstacionamientoModel estacionamiento = new EstacionamientoModel();
            estacionamiento.setIdUsuario(user.getId());
            estacionamiento.setPatente(txtPatente.getText().toString());
            estacionamiento.setDuracion(Long.parseLong(txtTiempo.getText().toString()));
            estacionamiento.setEstado(true);
            return estacionamiento;
        }
        catch(Exception e){
            throw e;
        }
    }

    private boolean validate(){
        if(Validaciones.esVacio(txtPatente.getText().toString())){
            app.showToast("El número de patente no puede ser vacío");
            setFocus(txtPatente);
            return false;
        }
        if(Validaciones.esVacio(txtTiempo.getText().toString())){
            app.showToast("La cantidad de minutos no puede ser 0");
            setFocus(txtTiempo);
            return false;
        }
        return true;
    }

    private void limpiarCampos(){
        txtPatente.setText("");
        txtTiempo.setText("");
    }

    private void setFocus(EditText control){
        control.requestFocus();
        InputMethodManager imm = (InputMethodManager) app.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(control, InputMethodManager.SHOW_IMPLICIT);
    }

}