package fr.esgi.java2i.exam.equipement.detecteur;

import fr.esgi.java2i.exam.InstalleDansUnePiece;
import fr.esgi.java2i.exam.Piece;
import fr.esgi.java2i.exam.equipement.Equipement;

public class DetecteurMouvement extends Equipement implements InstalleDansUnePiece {

    private boolean mouvementDetecte;
    private final Piece piece;

    public DetecteurMouvement(String id, Piece piece) {
        super(id);
        this.mouvementDetecte = false;
        this.piece = piece;
    }

    public boolean isMouvementDetecte() {
        return mouvementDetecte;
    }

    public void setMouvementDetecte(boolean mouvementDetecte) {
        this.mouvementDetecte = mouvementDetecte;
    }

    @Override
    public void afficherEtat() {
        System.out.println("- Le détecteur de mouvement [" + getId() + "] dans la pièce " + piece
                + " est " + (isActif() ? "Actif" : "Désactivé") + " et "
                + (isMouvementDetecte() ? "Mouvement détecté" : "Pas de mouvement"));
    }

    @Override
    public Piece getPiece() {
        return piece;
    }
}