package fr.esgi.java2i.exam.equipement.detecteur;

import fr.esgi.java2i.exam.Piece;
import java.util.Random;

public class DetecteurMouvementAvecCamera extends DetecteurMouvement {

    private static final String PHOTO_SYMBOLS = "0123456789ABCDEF";
    private static final int TAILLE_PHOTO = 10;

    private String dernierePhoto;

    public DetecteurMouvementAvecCamera(String id, Piece piece) {
        super(id, piece);
        this.dernierePhoto = "";
    }

    public void prendrePhoto() {
        Random random = new Random();
        StringBuilder photo = new StringBuilder();
        for (int i = 0; i < TAILLE_PHOTO; i++) {
            int index = random.nextInt(PHOTO_SYMBOLS.length());
            photo.append(PHOTO_SYMBOLS.charAt(index));
        }
        this.dernierePhoto = photo.toString();
    }

    public String getDernierePhoto() {
        return dernierePhoto;
    }

    @Override
    public void afficherEtat() {
        super.afficherEtat();
        System.out.println("- La dernière photo: " + (dernierePhoto.isEmpty() ? "Aucune photo prise" : dernierePhoto));
    }
}