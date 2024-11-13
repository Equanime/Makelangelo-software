##  Coverage

Référence : 27.33 %
[![Github Action](https://github.com/Equanime/Makelangelo-software/actions/workflows/test.yml/badge.svg)](https://github.com/Equanime/Makelangelo-software/actions/workflows/test.yml)

## Résumé du parcours
On a d'abord fait la [liste des flags](https://github.com/Equanime/Makelangelo-software/actions/runs/11802673804/job/32878853196)

On va ensuite modifier le [fichier](/.github/workflows/test.yml) de la [github action](https://github.com/Equanime/Makelangelo-software/actions/workflows/test.yml)

### Ajout de matrice de flags :
[Ajout de la matrice](/.github/workflows/test.yml#L21-L27)
```yml
        maven_opts:
          - "-XX:-UseG1GC -XX:+UseZGC" # Change GC (Garbage Collector)
          # - "-XX:MaxGCPauseMillis=2000" # GC with more pause time
          - "-Xmx256m -Xms256m" # Compilation with less memory (fail with 128)
          - "-XX:MaxMetaspaceSize=128m" # Compilation with less memory allocated to class and metadata
          - "-XX:+TieredCompilation" # Run faster tests with optimized compilation
          - "-Djava.security.egd=file:/dev/./urandom" # Changing random source
```
[Accès à la matrice](/.github/workflows/test.yml#L39)
```yml
        echo "MAVEN_OPTS=${{ matrix.maven_opts }}" >> $GITHUB_ENV
```

### Log et vérification
[Modification du code de vérification](/.github/workflows/test.yml#L50-L59)
```yml
        coverage=$COVERAGE
        threshold=27.33
        echo "Flags : '$MAVEN_OPTS'"
        echo "Coverage attendu : $threshold%"
        if (( $(echo "$coverage < $threshold" | bc -l) )); then
          echo "Coverage has decreased ($coverage%) !!!"
          exit 1
        else
          echo "Coverage is still!"
        fi
```
Principe : affichage des flags utilisés, Puis du coverage attendu, puis après calcul, exit ou continuité de la Github Action.

### Ajout de différentes JVM
[Ajout de la matrice](/.github/workflows/test.yml#L12-L20)
```yml
        jvm_version:
          - '17'
          # - '18'
          # - '19'
          - '20'
          # - '21'
          # - '22'
          - '23'
          # - '24' - don't exist yet
```
[Accès à la matrice](/.github/workflows/test.yml#L35)
```yml
        java-version: ${{ matrix.jvm_version }}
```
Il existe de nombreuses versions de la JVM qui vérifient la [condition >= 17 prévue dans le projet](/pom.xml#L562) et qui existent actuellement [(Java 24 prévue pour 2025-03-18)](https://www.java.com/releases/#24)

Nous essayons donc 3 versions dont la dernière en date (choix arbitraire)

## Résumé des flags ajoutés
### "-XX:-UseG1GC -XX:+UseZGC"
Le moteur ZGC pourrait être plus performant dans le cadre de l'utiisation de moteurs en réduisant les latences. Tester donc ce GC est intéressant

### "-XX:MaxGCPauseMillis=2000"
Pourrait être intéressant également, mais dans le cadre de la tache 3, ça fait doublon avec le premier flag

### "-Xmx256m -Xms256m"
Non seulement ces flags permettent de retirer le potentiel ralentissement dû au redimentionnement du tas

De plus, une petite valeur comme 256 Mo permet de tester le programme avec une petite mémoire (pratique avec un petit apareil embarqué)

### "-XX:MaxMetaspaceSize=128m"
Le MetaSpace est l'espace alloué pour les classes chargées, notamment avec les imports. Limiter cette valeur permet de limiter les ressources prises par le chargement de bibliothèques.

### "-XX:+TieredCompilation"
Ce mode de compilation est entre la compilation complète et la compilation JIT (Just-In-Time), en optimisant en priorité les méthodes les plus utilisées.

### "-Djava.security.egd=file:/dev/./urandom"
Ce flag permet de modifier la génération des nombres aléatoires en sacrifiant l'imprévisibilité des nombres aléatoires (moins sécurisé).
Etant donné que Makelangelo ne fait rien de sensible (comparé à cryptomator ou autres), ce flag est pertinent.
