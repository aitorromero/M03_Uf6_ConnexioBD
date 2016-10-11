package com.exemple.profedam.calculadoradpi.controllers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exemple.profedam.calculadoradpi.R;

import com.exemple.profedam.calculadoradpi.model.Pantalla;
import com.exemple.profedam.calculadoradpi.utils.MyUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Cal esbrinar, quina és l'orientació del telèfon */

          WindowManager windowManager = (WindowManager) getSystemService (Context.WINDOW_SERVICE);
          Display display = windowManager.getDefaultDisplay();
          if (display.getRotation()==Surface.ROTATION_0|| display.getRotation()==Surface.ROTATION_180)
          {
              Button btnCalcular = (Button) findViewById(R.id.btnCalcular);
              btnCalcular.setOnClickListener(this);
              Toast.makeText (this, "CargarVertical", Toast.LENGTH_SHORT).show();

          }
          else
          {
              Toast.makeText (this, "CargarHorizontal", Toast.LENGTH_SHORT).show();
              cargarHorizontal(display);

          }






    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCalcular) {
            EditText etResHoritzontal = (EditText) findViewById(R.id.etResolucioHoritzontal);
            EditText etResVertical = (EditText) findViewById(R.id.etResolucioVertical);
            EditText etDiagonal = (EditText) findViewById(R.id.etDiagonal);


            if (!MyUtils.editTextIsNullOrEmpty(etResHoritzontal)
                    && !MyUtils.editTextIsNullOrEmpty(etResVertical) &&
                    !MyUtils.editTextIsNullOrEmpty(etDiagonal))

            {
                int resHoritzontal = Integer.valueOf(etResHoritzontal.getText().toString());
                int resVertical = Integer.valueOf(etResVertical.getText().toString());
                double resDiagonal = Double.valueOf(etDiagonal.getText().toString());

                Pantalla pantalla = new Pantalla(resHoritzontal, resVertical, resDiagonal);
                TextView tvDpi = (TextView) findViewById(R.id.tvDpi);
                tvDpi.setText(String.valueOf(pantalla.getDpi()) + " dpi");
            } else {
                if (MyUtils.editTextIsNullOrEmpty(etResHoritzontal)) {
                    Toast.makeText(this, "Falta resolución horizontal", Toast.LENGTH_SHORT).show();

                  //  etResHoritzontal.setFocusable(true);
                    etResHoritzontal.requestFocus();

                }

                if (MyUtils.editTextIsNullOrEmpty(etResVertical)) {
                    Toast.makeText(this, "Falta resolución vertical", Toast.LENGTH_SHORT).show();
                    etResVertical.requestFocus();

                }

                if (MyUtils.editTextIsNullOrEmpty(etDiagonal)) {
                    Toast.makeText(this, "Falta Diagonal", Toast.LENGTH_SHORT).show();
                    etDiagonal.requestFocus();

                }
            }
        }
    }

    private void cargarHorizontal(Display display) {

        /* TODO Obtener los valores de las resoluciones y rellenar los textviews */

        DisplayMetrics displayMetrics = new DisplayMetrics();

        // Carga los valores del display en displaymetrics

        display.getMetrics(displayMetrics);


        TextView tvResVertical = (TextView) findViewById (R.id.tvResolucionVerticalED);
        tvResVertical.setText (String.valueOf(displayMetrics.heightPixels));

        TextView tvResHorizontal = (TextView) findViewById(R.id.tvResolucionHorizontalED);
        tvResHorizontal.setText (String.valueOf(displayMetrics.widthPixels));

        TextView tvPPI = (TextView) findViewById (R.id.tvPPIED);
        tvPPI.setText (String.valueOf(displayMetrics.densityDpi));

        Pantalla pantalla = new Pantalla (displayMetrics.heightPixels, displayMetrics.widthPixels, displayMetrics.densityDpi);
        TextView tvDiagonal = (TextView) findViewById (R.id.tvDiagonalED);
        tvDiagonal.setText (String.valueOf(pantalla.getDiagonal()));





        // tvResVertical.setText ("Rv:" + displayMetrics.heightPixels);








    }




}


