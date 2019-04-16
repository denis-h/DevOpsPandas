# DevOpsPandas


## Bibliothèque d'analyse de données en Java

[![CircleCI](https://circleci.com/gh/denis-h/DevOpsPandas.svg?style=svg&circle-token=a3cd0e39448c0872c08302ef0f31b070e7d62e69)](https://circleci.com/gh/denis-h/DevOpsPandas)
[![Coverage Status](https://coveralls.io/repos/github/denis-h/DevOpsPandas/badge.svg)](https://coveralls.io/github/denis-h/DevOpsPandas)


## Fonctionnalités fournies

* Création d'un _dataframe_
    * `DataFrame(ArrayList<Column> data)` où `data` est un ensemble de colonnes
    * `DataFrame(String csv)` où `csv` est un nom de fichier qui est parsé

* Affichage d'un _dataframe_
    * `String print()` affiche tout le dataframe
    * `String printBegin(int nb_lines)` affiche `nb_lines` premières lignes
    * `String printLast(int nb_lines)` affiche `nb_lines` dernières lignes

* Sélection dans un _dataframe_
    * `iloc(int pos)` sélectionne une ligne à l'index `pos`
    * `iloc(ArrayList<Integer> pos)` sélectionne un ensemble de lignes
    * `loc(String label)` sélectionne une colonne avec l'étiquette `label`
    * `loc(ArrayList<String> label)` sélectionne un ensemble de colonnes

* Statistiques sur un _dataframe_
    * `max()` calcule la valeur maximale d'une colonne
    * `min()` calcule la valeur minimale d'une colonne
    * `average()` calcule la moyenne d'une colonne
    * `sum()` calcule la somme d'une colonne

## Outils utilisés

- [GitHub][github_link]
Nous avons utilisé _GitHub_ pour stocker,versionner et partager le source code
- [Maven][maven_link]
Maven nous a permis de construire notre projet et de gérer facilement les
dépendances. En ajoutant différents `plugins` nous pouvons :
    - compiler notre projet `mvn compile`
    - executer le programme `mvn exec:java`
    - lancer les tests `mvn test`        
- [Junit][junit_link]
Nous avons écris des tests unitaires afin de vérifier le bon fonctionnement de
notre bibliothèque
- [CircleCI][circleci_link]
Pour avoir un service d'intégration continue nous avons choisi _CircleCI_ car
ce système est recommandé pour les petits projets, où l'objectif principal est
de démarrer l'intégration le plus rapidement possible. Nous avons liés
_CirleCI_ avec notre depot de _GitHub_ et notre image sur _Docker Hub_.
- [JaCoCo (EclEmma)][jacoco_link]
La couverture de code est mésurée par _JaCoCo_. Le rapport est généré par
_maven_ en lancant la commande `mvn test` et il se trouve dans
`target/site/jacoco/index.html`.
- [Docker Hub][dockerhub_link]
Nous avons dockerisé notre projet en construisant une image de _docker_. Cette
image est disponible sur [Docker Hub][image_link]. Nous l'avons aussi liée avec
_CirleCI_
- [Badges][badges_link]
Le résultat de build par _CirleCI_ est lié avec _GitHub_

## Feedback

Le projet nous a permis de comprendre mieux les bonnes pratiques de _DevOps_.



[github_link]: https://github.com/
[maven_link]: https://maven.apache.org/
[junit_link]: https://junit.org/junit5/
[circleci_link]: https://circleci.com/
[jacoco_link]: https://www.eclemma.org/jacoco/
[dockerhub_link]: https://hub.docker.com/
[badges_link]: https://github.com/badges/
[image_link]: https://hub.docker.com/r/denishod/devopspandas
