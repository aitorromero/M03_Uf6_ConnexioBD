package com.exemple.profedam.calculadoradpi.utils;

import android.widget.EditText;

/**
 * Created by Lluis_2 on 29/09/2016.
 */

public class MyUtils {

    public static double calcularHipotenusa (double catetoA, double catetoB)
    {
        //TODO suponemos valores correctos

        return Math.sqrt(Math.pow(catetoA,2)+Math.pow(catetoB,2));
    }


    public static boolean stringIsNullOrEmpty (String cadena)

    {
        return (cadena == null ||cadena.length()==0);

    }

    public static boolean editTextIsNullOrEmpty (EditText editText)
    {
        return stringIsNullOrEmpty (editText.getText().toString());
    }

    public static double round (double numero,int digitos)
    {
        int cifras =(int) Math.pow(10,digitos);

         //Math.rint redondea al número entero con 0 decimales
        return Math.rint(numero*cifras)/cifras;
    }

}
