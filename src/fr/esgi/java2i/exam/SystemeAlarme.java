package fr.esgi.java2i.exam;

import fr.esgi.java2i.exam.equipement.Clavier;
import fr.esgi.java2i.exam.equipement.Equipement;
import fr.esgi.java2i.exam.equipement.Sirene;
import fr.esgi.java2i.exam.equipement.Telecommande;
import fr.esgi.java2i.exam.equipement.detecteur.DetecteurMouvement;
import fr.esgi.java2i.exam.equipement.detecteur.DetecteurMouvementAvecCamera;
import fr.esgi.java2i.exam.equipement.detecteur.DetecteurOuverture;
import java.util.Random;
import java.util.Scanner;

public class SystemeAlarme {

    private static final int NOMBRE_EQUIPEMENTS = 13;

    private final Equipement[] equipements = new Equipement[NOMBRE_EQUIPEMENTS];
    private boolean alarmeActive = false;
    private Sirene sirene;

    public void init() {
        int idx = 0;
        equipements[idx++] = new DetecteurOuverture("DO-1", Piece.ENTREE);
        equipements[idx++] = new DetecteurOuverture("DO-2", Piece.ENTREE);
        equipements[idx++] = new DetecteurOuverture("DO-3", Piece.CUISINE);
        equipements[idx++] = new DetecteurOuverture("DO-4", Piece.SALLE_A_MANGER);
        equipements[idx++] = new DetecteurOuverture("DO-5", Piece.CHAMBRE);
        equipements[idx++] = new DetecteurOuverture("DO-6", Piece.GARAGE);
        equipements[idx++] = new DetecteurMouvement("DM-1", Piece.SALLE_A_MANGER);
        equipements[idx++] = new DetecteurMouvement("DM-2", Piece.CHAMBRE);
        equipements[idx++] = new DetecteurMouvementAvecCamera("DMC-1", Piece.ENTREE);
        this.sirene = new Sirene("SIR-1");
        equipements[idx++] = this.sirene;
        equipements[idx++] = new Telecommande("TEL-1");
        equipements[idx++] = new Telecommande("TEL-2");
        equipements[idx++] = new Clavier("CLA-1");
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choix = -1;
        while (choix != 0) {
            System.out.println();
            System.out.println("Menu:");
            System.out.println("1. Afficher l'état de tous les équipements");
            System.out.println("2. Afficher l'état de tous les équipements d'une pièce");
            System.out.println("3. Activer ou Désactiver le système d'alarme");
            System.out.println("4. Activer ou Désactiver les équipements d'une pièce");
            System.out.println("5. Tester le détecteur de mouvement avec caméra");
            System.out.println("6. Simuler l'utilisation du clavier");
            System.out.println("7. Simuler l'ouverture d'une fenêtre ou d'une porte");
            System.out.println("8. Simuler une intrusion avec les capteurs de mouvement");
            System.out.println("0. Quitter");
            System.out.print("Votre choix: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Veuillez saisir un nombre valide !");
                scanner.next();
                continue;
            }
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 0:
                    System.out.println("Au revoir !");
                    break;
                case 1:
                    afficherEtatEquipements();
                    break;
                case 2:
                    afficherEtatEquipementsPiece(scanner);
                    break;
                case 3:
                    activerDesactiverSystemeAlarme();
                    break;
                case 4:
                    activerDesactiverEquipementsPiece(scanner);
                    break;
                case 5:
                    testerDetecteurMouvementAvecCamera();
                    break;
                case 6:
                    simulerUtilisationClavier(scanner);
                    break;
                case 7:
                    simulerOuverturePorteExtOuFenetre(scanner);
                    break;
                case 8:
                    simulerIntrusion();
                    break;
                default:
                    System.out.println("Choix invalide, recommencez !");
            }
        }
        scanner.close();
    }

    private Sirene getSirene() {
        return this.sirene;
    }

    private Clavier getClavier() {
        for (int i = 0; i < equipements.length; i++) {
            if (equipements[i] instanceof Clavier) {
                return (Clavier) equipements[i];
            }
        }
        return null;
    }

    private Telecommande getTelecommande() {
        for (Equipement equipement : equipements) {
            if (equipement instanceof Telecommande) {
                return (Telecommande) equipement;
            }
        }
        return null;
    }

    private void afficherEtatEquipements() {
        for (Equipement equipement : equipements) {
            equipement.afficherEtat();
        }
    }

    private Piece saisirPiece(Scanner scanner) {
        Piece[] pieces = Piece.values();
        StringBuilder liste = new StringBuilder();
        for (int i = 0; i < pieces.length; i++) {
            liste.append(pieces[i]);
            if (i < pieces.length - 1) {
                liste.append(", ");
            }
        }
        System.out.print("Entrez le nom de la pièce (" + liste + ") : ");
        String nomPiece = scanner.nextLine().toUpperCase();
        try {
            return Piece.valueOf(nomPiece);
        } catch (IllegalArgumentException e) {
            System.out.println("Tu n'as pas saisi correctement le nom de la pièce !");
            return null;
        }
    }

    private void afficherEtatEquipementsPiece(Scanner scanner) {
        Piece piece = saisirPiece(scanner);
        if (piece == null) {
            return;
        }
        for (Equipement equipement : equipements) {
            if (equipement instanceof InstalleDansUnePiece) {
                InstalleDansUnePiece installe = (InstalleDansUnePiece) equipement;
                if (installe.getPiece() == piece) {
                    equipement.afficherEtat();
                }
            }
        }
    }

    public void activerDesactiverSystemeAlarme() {
        alarmeActive = !alarmeActive;
        Telecommande telecommande = getTelecommande();
        for (Equipement equipement : equipements) {
            telecommande.appuyer(equipement, alarmeActive);
        }
        System.out.println("Système d'alarme " + (alarmeActive ? "activé" : "désactivé"));
    }

    public void desactiverAlarme() {
        if (alarmeActive) {
            alarmeActive = false;
            Telecommande telecommande = getTelecommande();
            for (Equipement equipement : equipements) {
                telecommande.appuyer(equipement, false);
            }
            System.out.println("Système d'alarme désactivé");
        }
    }

    private void activerDesactiverEquipementsPiece(Scanner scanner) {
        Piece piece = saisirPiece(scanner);
        if (piece == null) {
            return;
        }
        int i = 0;
        while (i < equipements.length) {
            Equipement equipement = equipements[i];
            if (equipement instanceof InstalleDansUnePiece) {
                InstalleDansUnePiece installe = (InstalleDansUnePiece) equipement;
                if (installe.getPiece() == piece) {
                    equipement.setActif(!equipement.isActif());
                }
            }
            i++;
        }
        System.out.println("Équipements de la pièce " + piece + " ont été basculés.");
    }

    private void testerDetecteurMouvementAvecCamera() {
        for (Equipement equipement : equipements) {
            if (equipement instanceof DetecteurMouvementAvecCamera) {
                DetecteurMouvementAvecCamera detecteur = (DetecteurMouvementAvecCamera) equipement;
                detecteur.prendrePhoto();
                detecteur.afficherEtat();
            }
        }
    }

    private void simulerUtilisationClavier(Scanner scanner) {
        Clavier clavier = getClavier();
        if (clavier != null) {
            clavier.utiliserClavier(this, scanner);
        }
    }

    private void simulerOuverturePorteExtOuFenetre(Scanner scanner) {
        System.out.print("Entrez l'ID du détecteur d'ouverture à tester: ");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("ID invalide !");
            return;
        }
        int idDetecteur = scanner.nextInt();
        scanner.nextLine();
        for (Equipement equipement : equipements) {
            if (equipement instanceof DetecteurOuverture && equipement.getId() == idDetecteur) {
                DetecteurOuverture detecteur = (DetecteurOuverture) equipement;
                detecteur.setOuvert(!detecteur.isOuvert());
                if (detecteur.isOuvert() && alarmeActive) {
                    activerSirene();
                }
                detecteur.afficherEtat();
            }
        }
    }

    private void simulerIntrusion() {
        Random random = new Random();
        for (Equipement equipement : equipements) {
            if (equipement instanceof DetecteurMouvement) {
                DetecteurMouvement detecteur = (DetecteurMouvement) equipement;
                detecteur.setMouvementDetecte(random.nextBoolean());
                if (detecteur.isMouvementDetecte() && alarmeActive) {
                    activerSirene();
                }
                detecteur.afficherEtat();
            }
        }
    }

    public void activerSirene() {
        Sirene s = getSirene();
        if (s != null) {
            s.declencher();
        }
    }
}