package fr.esgi.java2i.exam.equipement;

public class Telecommande extends Equipement {

    public Telecommande(String nom) {
        super(nom);
        this.setActif(true);
    }

    @Override
    public void afficherEtat() {
        System.out.println("- La télécommande [" + getId() + "] est " + (isActif() ? "Active" : "Désactivée"));
    }

    public void appuyer(Equipement equipement, boolean actif) {
        equipement.setActif(actif);
    }
}