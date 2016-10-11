package com.exemple.profedam.calculadoradpi.model;


import com.exemple.profedam.calculadoradpi.utils.MyUtils;

/**
 * Created by Lluis_2 on 29/09/2016.
 */

public class Pantalla {


    private int resHoritzontal;
    private int resVertical;
    private int dpi;
    private double diagonal;


    public Pantalla(int resHoritzontal, int resVertical,  double diagonal) {
        this.resHoritzontal = resHoritzontal;
        this.resVertical = resVertical;
        this.diagonal = diagonal;
        calcularDpi();
    }
    public Pantalla (int resHoritzontal, int resVertical, int dpi)
    {
        this.resHoritzontal = resHoritzontal;
        this.resVertical = resVertical;
        this.dpi = dpi;
        calcularDiagonal();
    }

    public int getResHoritzontal() {
        return resHoritzontal;
    }

    public void setResHoritzontal(int resHoritzontal) {
        this.resHoritzontal = resHoritzontal;
    }

    public int getResVertical() {
        return resVertical;
    }

    public void setResVertical(int resVertical) {
        this.resVertical = resVertical;
    }

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(double diagonal) {
        this.diagonal = diagonal;
    }

    private void calcularDpi(){

        this.dpi = (int)(MyUtils.calcularHipotenusa(resHoritzontal, resVertical)/diagonal);

    }
    private void calcularDiagonal()
    {
      this.diagonal = MyUtils.round(MyUtils.calcularHipotenusa(resHoritzontal, resVertical)/dpi, 1);
    }
}


