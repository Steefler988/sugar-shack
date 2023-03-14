# Sugar-Shack

Projet Spring Boot avec base de données H2 intégrée et couverture de tests unitaires
Ce projet est une démonstration de Spring Boot avec une base de données H2 intégrée et une couverture de tests unitaires. 
Il a pour but de répondre à un test de compétence exigé par Maplr

Prérequis
Java 17
Maven

- [Installation](#installation)
- [Configuration](#configuration)
- [Execution](#execution)
- [Tests](#tests)


## Installation
Pour installer le projet, vous pouvez le cloner depuis le dépôt Git et l'importer dans votre environnement de développement préféré. Vous aurez également besoin de Java 17 pour exécuter l'application.

## Configuration
Le fichier application.properties contient la configuration pour la base de données H2. La base de données H2 sera générée automatiquement et les données seront insérées grâce aux scripts d'insertion dans src/main/resources/data.sql.

## Execution
Pour lancer l'application, vous pouvez utiliser la commande mvn spring-boot:run à la racine du projet. L'application sera accessible à l'adresse http://localhost:8080.

## Tests
Le projet contient des tests unitaires pour les différentes couches de l'application. Vous pouvez lancer les tests en utilisant la commande mvn test à la racine du projet. 
Les tests unitaires peuvent être exécutés avec la commande suivante : mvn test


Auteur
Philippe Raquin