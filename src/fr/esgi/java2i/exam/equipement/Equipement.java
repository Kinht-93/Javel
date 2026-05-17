package fr.esgi.java2i.exam.equipement;

public abstract class Equipement {

    private static int compteur = 0;

    private final int id;
    private String nom;
    private boolean actif;

    public Equipement(String nom) {

        this.nom = nom;
        compteur++;
        this.id = compteur;
        this.actif = false;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public abstract void afficherEtat();
}