package model;

public class Usuari {
    private String nif;
    private String nom;
    private String cognom;

    public Usuari() {
    }

    public Usuari(String nif, String nom, String cognom) {
        this.nif = nif;
        this.nom = nom;
        this.cognom = cognom;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    @Override
    public String toString() {
        return "Usuari{" + "nif=" + nif + ", nom=" + nom + ", cognom=" + cognom + '}';
    }
    
    
}
