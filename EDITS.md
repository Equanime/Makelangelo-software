Premier test unitaire :
POur tester si j'arrivais à faire un test unitaire, j'ai vérifié une fonction de tan :
src/main/java/com/marginallyclever/makelangelo/makeart/io/LoadGCode.java/atan3(-) (/root/UdeM/IFT3913/Makelangelo-software/src/test/java/com/marginallyclever/makelangelo/makeart/io/LoadGCodeTest.java)
Cette méthode étant privée, il fallait utiliser java.lang.reflect.Method pour autoriser un accès hors classe
Pour détourner les protections de Java, il a fallu ajouter un argument /root/UdeM/IFT3913/Makelangelo-software/pom.xml:239 : --add-opens java.base/java.util=ALL-UNNAMED
Dans le target/site/jacoco/com.marginallyclever.makelangelo.makeart.io/LoadGCode.html, passage de 54% à 57% du coverage global du fichier

