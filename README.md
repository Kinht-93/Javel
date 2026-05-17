# Javel

Partiel de programmation orientée objet en Java (ESGI — Java 2I).

Modélisation d'un **système d'alarme domestique** : une habitation composée de pièces, chaque pièce pouvant accueillir différents équipements (capteurs, clavier de contrôle, sirène, télécommande). L'exercice met en pratique l'héritage, le polymorphisme et la composition.

## Sommaire

- [Prérequis](#prérequis)
- [Structure du projet](#structure-du-projet)
- [Modèle objet](#modèle-objet)
- [Compilation et exécution](#compilation-et-exécution)
- [Licence](#licence)

## Prérequis

- **JDK 17** ou supérieur (testé sur JDK 21).
- Un IDE Java au choix : IntelliJ IDEA, Eclipse, ou VS Code avec l'extension *Extension Pack for Java*.
- Git.

Vérification :

~~~bash
javac -version
java -version
~~~

## Structure du projet

~~~
Javel/
├── bin/                                 # classes compilées (généré par javac)
├── lib/                                 # bibliothèques externes
├── src/
│   └── fr/esgi/java2i/exam/
│       ├── Main.java                    # point d'entrée
│       ├── Piece.java                   # une pièce de l'habitation
│       ├── SystemeAlarme.java           # système d'alarme central
│       ├── InstalleDansUnePiece.java    # contrat pour les objets installables
│       └── equipement/
│           ├── Equipement.java          # classe mère des équipements
│           ├── Clavier.java
│           ├── Sirene.java
│           ├── Telecommande.java
│           └── detecteur/
│               ├── DetecteurMouvement.java
│               ├── DetecteurMouvementAvecCamera.java
│               └── DetecteurOuverture.java
└── README.md
~~~

## Modèle objet

| Classe | Rôle |
|---|---|
| `SystemeAlarme` | Pilote central. Connaît l'ensemble des pièces et orchestre l'armement / déclenchement / désactivation. |
| `Piece` | Représente une pièce de l'habitation. Conteneur d'équipements. |
| `InstalleDansUnePiece` | Contrat implémenté par tout objet pouvant être installé dans une pièce. |
| `Equipement` | Classe mère abstraite de tous les équipements. |
| `Clavier`, `Sirene`, `Telecommande` | Équipements concrets non-capteurs. |
| `DetecteurMouvement`, `DetecteurOuverture` | Capteurs concrets. |
| `DetecteurMouvementAvecCamera` | Spécialisation de `DetecteurMouvement` enrichie d'une caméra. |

La hiérarchie illustre :

- **Héritage** — `DetecteurMouvementAvecCamera` → `DetecteurMouvement` → `Equipement`.
- **Polymorphisme** — une `Piece` manipule ses équipements via le type abstrait `Equipement`.
- **Composition** — `SystemeAlarme` contient des `Piece`, qui contiennent des `Equipement`.
- **Contrat** — `InstalleDansUnePiece` imposé à tout objet placé dans une pièce.

## Compilation et exécution

Sans build tool externe, depuis la racine du projet :

~~~bash
# Compilation : tous les .java vers bin/
javac -d bin -sourcepath src $(find src -name "*.java")

# Exécution
java -cp bin fr.esgi.java2i.exam.Main
~~~

Sous Windows PowerShell :

~~~powershell
javac -d bin -sourcepath src (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object FullName)
java -cp bin fr.esgi.java2i.exam.Main
~~~

Avec un IDE : ouvre le dossier `Javel/` et lance la classe `Main`.

## Licence

MIT — voir le fichier [`LICENSE`](LICENSE).