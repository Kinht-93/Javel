package fr.esgi.java2i.exam.equipement;

import fr.esgi.java2i.exam.SystemeAlarme;
import java.util.Scanner;

public class Clavier extends Equipement {

    private static final String CODE_ALARME = "1234";
    private static final int NB_TENTATIVES = 3;

    public Clavier(String id) {
        super(id);
        this.setActif(true);
    }

    @Override
    public void afficherEtat() {
        System.out.println("- Le clavier [" + getId() + "] est " + (isActif() ? "Actif" : "Désactivé"));
    }

    public void utiliserClavier(SystemeAlarme systemeAlarme, Scanner scanner) {
        boolean bonCode = false;
        int tentative = 0;
        while (tentative < NB_TENTATIVES && !bonCode) {
            System.out.print("Entrez le code pour désactiver l'alarme: ");
            String code = scanner.nextLine();
            if (code.equals(CODE_ALARME)) {
                bonCode = true;
            } else {
                tentative++;
                System.out.println("Code incorrect ! (" + tentative + "/" + NB_TENTATIVES + ")");
            }
        }
        if (bonCode) {
            System.out.println("Code correct !");
            systemeAlarme.desactiverAlarme();
        } else {
            System.out.println("Trop de tentatives échouées !");
            systemeAlarme.activerSirene();
        }
    }
}