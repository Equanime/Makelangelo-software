##  Coverage
Origine : 24.46 %
Maintenant : 27.33 %
[![Github Action](https://github.com/Equanime/Makelangelo-software/actions/workflows/test.yml/badge.svg)](https://github.com/Equanime/Makelangelo-software/actions/workflows/test.yml)

## Résumé du parcours
Premier test unitaire :
POur tester si j'arrivais à faire un test unitaire, j'ai vérifié une fonction de tan :
> [Makelangelo > com.marginallyclever.makelangelo.makeart.io > LoadGCode.java](https://3913.udem.col1n.fr/com.marginallyclever.makelangelo.makeart.io/LoadGCode.java.html#L39) **[>class<](src/test/java/com/marginallyclever/makelangelo/makeart/io/LoadGCodeTest.java)**

Cette méthode étant privée, il fallait utiliser java.lang.reflect.Method pour autoriser un accès hors classe

Pour détourner les protections de Java, il a fallu ajouter un argument [pom.xml:239](pom.xml#L239) : `--add-opens java.base/java.util=ALL-UNNAMED`

Dans [Makelangelo > com.marginallyclever.makelangelo.makeart.io > LoadGCode.java](https://3913.udem.col1n.fr/com.marginallyclever.makelangelo.makeart.io/LoadGCode.java.html#L39), passage de 54% à 57% du coverage global du fichier, on peut y aller

Pour s'habituer à faire des tests unitaires, nous avons fait les tests pour

> [Makelangelo > com.marginallyclever.convenience > NameThatColor.java](https://3913.udem.col1n.fr/com.marginallyclever.convenience/NameThatColor.java.html) **[>class<](src/test/java/com/marginallyclever/convenience/NameThatColorTest.java)**

> [Makelangelo > com.marginallyclever.convenience > ColorRGB.java](https://3913.udem.col1n.fr/com.marginallyclever.convenience/ColorRGB.java.html) **[>class<](src/test/java/com/marginallyclever/convenience/TestColorRGB.java)**

> [Makelangelo > com.marginallyclever.makelangelo.makeart.imageconverter > Converter_FlowField.java](https://3913.udem.col1n.fr/com.marginallyclever.makelangelo.makeart.imageconverter/Converter_FlowField.html) **[>class<](src/test/java/com/marginallyclever/makelangelo/makeart/imageconverter/Converter_FlowFieldTest.java)**

Pour apprendre à faire des tests avec JavaFaker (on veut le bonus)
> [Makelangelo > com.marginallyclever.convenience > Point2D.java](https://3913.udem.col1n.fr/com.marginallyclever.convenience/Point2D.java.html) **[>class<](src/test/java/com/marginallyclever/convenience/TestPoint2D.java)**

#### Ensuite on va continuer à compléter [Makelangelo > com.marginallyclever.convenience](https://3913.udem.col1n.fr/com.marginallyclever.convenience/index.source.html)
(On a décidé de ne pas revenir en arrière : on pourrait javafaker les couleurs)

> [Makelangelo > com.marginallyclever.convenience > LineInterpolator.java
](https://3913.udem.col1n.fr/com.marginallyclever.convenience/LineInterpolator.java.html) **[>class<](src/test/java/com/marginallyclever/convenience/TestLineInterpolator.java)**

> [Makelangelo > com.marginallyclever.convenience > QuadGraph.java](https://3913.udem.col1n.fr/com.marginallyclever.convenience/QuadGraph.java.html) **[>class<](src/test/java/com/marginallyclever/convenience/TestQuadGraph.java)**

## Etapes intéressantes du projet 
- Pas d'écran
Pour se faciliter la vie, on a travaillé sur un serveur partagé (un ordi chez moi en France), donc on avait pas d'écran, j'ai demandé à notre copain ChatGPT et on a installé xvfb

- Travailler ensemble (vive github)
Nous avons chacun travaillé sur une copie github du projet en se répartissant les classes, sauf les deux classes mises en évidence pour découvrir qu'on a travaillé ensemble

- Trouver mutuellement les bugs
Pour être sûrs de run les mêmes commandes, nous avons ajouté à la fin de nos .bashrc

```bash
alias mvntest='xvfb-run mvn test'
alias mvncleaninstall='mvn clean install -DskipTests'
alias mvnt='mvntest -e'
alias mvnci='mvncleaninstall'
alias xvfb='pkill Xvfb'
```


## Résumé des tests ajoutés
1. [Makelangelo > com.marginallyclever.makelangelo.makeart.io > LoadGCode > canLoad](src/test/java/com/marginallyclever/makelangelo/makeart/io/LoadGCodeTest.java#L66)
1. [Makelangelo > com.marginallyclever.convenience > NameThatColor > colorFinderTest](src/test/java/com/marginallyclever/convenience/NameThatColorTest.java#L26)
1. [Makelangelo > com.marginallyclever.convenience > NameThatColor > colorFinderClass](src/test/java/com/marginallyclever/convenience/NameThatColorTest.java#L46)
1. [Makelangelo > com.marginallyclever.convenience > Point2D > testNormalize](src/test/java/com/marginallyclever/convenience/TestPoint2D.java#L42)
1. [Makelangelo > com.marginallyclever.convenience > Point2D > testDistance](src/test/java/com/marginallyclever/convenience/TestPoint2D.java#L76)
1. [Makelangelo > com.marginallyclever.convenience > Point2D > testDistanceSquared](src/test/java/com/marginallyclever/convenience/TestPoint2D.java#L89)
1. [Makelangelo > com.marginallyclever.convenience > Point2D > testEqualsEpsilon](src/test/java/com/marginallyclever/convenience/TestPoint2D.java#L102)
1. [Makelangelo > com.marginallyclever.convenience > LineInterpolator > testGetPoint](src/test/java/com/marginallyclever/convenience/TestPoint2D.java#L30)
1. [Makelangelo > com.marginallyclever.convenience > LineInterpolator > testGetTangent](src/test/java/com/marginallyclever/convenience/TestPoint2D.java#L44)
1. [Makelangelo > com.marginallyclever.convenience > LineInterpolator > testGetNormal](src/test/java/com/marginallyclever/convenience/TestPoint2D.java#L104)
1. [Makelangelo > com.marginallyclever.convenience > QuadGraph > testInsert](src/test/java/com/marginallyclever/convenience/TestPoint2D.java#L40)
1. [Makelangelo > com.marginallyclever.convenience > QuadGraph > testSearch](src/test/java/com/marginallyclever/convenience/TestPoint2D.java#L63)
1. [Makelangelo > com.marginallyclever.convenience > QuadGraph > testCountPoints](src/test/java/com/marginallyclever/convenience/TestPoint2D.java#L136)
