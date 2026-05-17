package fr.esgi.java2i.exam.equipement;

public class Sirene extends Equipement {

    public Sirene(String id) {
        super(id);
        this.setActif(true);
    }

    public void declencher() {
        System.out.println("La sirène se déclenche à 100dB !!!");
    }

    @Override
    public void afficherEtat() {
        System.out.println("- La sirène [" + getId() + "] est " + (isActif() ? "Active" : "Désactivée"));
    }
}