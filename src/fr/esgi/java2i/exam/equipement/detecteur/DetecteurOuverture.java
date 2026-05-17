package fr.esgi.java2i.exam.equipement.detecteur;

import fr.esgi.java2i.exam.InstalleDansUnePiece;
import fr.esgi.java2i.exam.Piece;
import fr.esgi.java2i.exam.equipement.Equipement;

public class DetecteurOuverture extends Equipement implements InstalleDansUnePiece {

    private boolean ouvert;
    private final Piece piece;

    public DetecteurOuverture(String id, Piece piece) {
        super(id);
        this.ouvert = false;
        this.piece = piece;
    }

    public boolean isOuvert() {
        return ouvert;
    }

    public void setOuvert(boolean ouvert) {
        this.ouvert = ouvert;
    }

    @Override
    public void afficherEtat() {
        System.out.println("- Le détecteur d'ouverture [" + getId() + "] dans la pièce " + piece
                + " est " + (isActif() ? "Actif" : "Désactivé") + " et "
                + (isOuvert() ? "Ouvert" : "Fermé"));
    }

    @Override
    public Piece getPiece() {
        return piece;
    }
}