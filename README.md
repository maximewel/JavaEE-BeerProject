# Beer project
Le projet bière est un mini-projet du cours de JavaEE. Il est responsable d'1/3 de la note du TE1.

# Objectifs
Les objectifs sont décrits en 3 phases.

## Phase 1:
* Création d'un projet SpringBoot, avec dépendances web et thymeleaf
* Pas de bases de données, les bières sont stockées en mémoire (List, HashMap, etc...)
* Création d'un endpoint REST /bieres
    * GET --> Liste des bières
    * GET /bieres/[id] --> Détail d'une bière
    * POST --> Création d'une bière
    * DELETE --> Suppression d'une bière

## Phase 2:

* Création d'un formulaire de saisie de bières
* Création d'un écran listant les bières
* Création de la fonctionnalité de suppression de bières avec un écran

## Phase 3:
* Création de la fonctionnalité de modification

# structure
Le projet se structure en packages :

## Structure du code
La logique se trouve dans src/main/java/beer -> ch.hearc.beer et contient :
* L'application
* Le package model comprenant
	* Le model, la "beer"
	* L'interface de database, "IBeerDatabase" qui abstrait le CRUD d'une bière
	* La MapBeerDatabase, un exemple d'implem de database utilisant une map
* Le package controller comprenant
	* Le beercontroller
* Les vues dans src/main/ressources/static
	* css/styles.css contient le CSS du projet (classique, sans bootstrap)
	* Les vues index et createOrUpdate dans templates

A noter : Le controller est un controlleur léger, càd qu'il s'occupe du routage et de la vérification des input utilisateurs, mais laisse la résponsabilité du CRUD à l'interface de DB.

## Structure de l'app
L'app tente de rester standard dans l'implémentation restfull d'une mini-app pour les bières :
| Route        | Verbe           | Fonction  | But |
| :------------- |:-------------| :-----| :-----|
| /beer, /beer/, /beer/index      | GET | indexBeers() | Renvoit la liste des bières sur la vue respective |
| /beer/create      | GET      |   create() | Renvoit la *view avec formulaire* de création d'une bière |
| /beer      | POST      |   store() | Traite le formulaire et stock la nouvelle bière en DB |
| /beer/{id}      | GET      |   editForm() | Renvoit la *view avec formulaire* d'update d'une bière |
| /beer      | PUT      |   edit() | Traite le formulaire et update la bière en DB |
| /beer/{id}      | DELETE      |   destroy() | Efface la bière présente en DB |

Remarques :
* On reconnait les verbes get, delete, put, post sur l'endpoit restfull /beer et /beer/{id} si nécéssaire.\
* Le point /beer/{id} pourrait être réservé par la vue détail (non présente ici), alors on devrait placer la view d'update sur une autre route non rest
* Deux routes GET supplémentaires permettent de servir les  vues : /beer/create, /beer/{id} qui pourrait être /beer/{id}/update si on voulait laisser /beer/{id} pour les détails
* Les vues "create" et "update" sont regroupées - le traitement des fonction qui servet la vue se chargent :
	* de créer une bière temporaire dans le create, pour la donner à la vue createOrUpdate
	* de récupérer la bière en DB dans le update, pour la donner à la vue createOrUpdate
	Ceci permet de factoriser la vue de création/update d'une bière qui sont très similaires pour notre app
# Divers
* Le projet est doté d'un système de gestion des erreurs via la session - les erreurs sont mises en session depuis les fonctions non idempotentes (formulaires traités) et récupérées depuis la vue index
* Les routes non idempotentes - post, put, delete - sont redirigées vers l'indexe
* Il faudra serieusement penser à importer bootstrap sur le vrai projet, parce que là c'est sacrèment moche.
