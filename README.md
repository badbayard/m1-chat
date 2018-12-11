## Equipe
Yannis Hutt
Gabriel Vinado

## Remarques et Pré-requis
Voici le tp chat réaliser dans le cadre de l'UE de programmation web.
Ce projet a été développé et testé sous Windows 10.

Pour que ce logiciel fonctionne de manière relativement sûr, il faut au préalable avoir installé : 
- Java  (version 1.8 ou ultérieur)
- Maven (version 4.0 ou ultérieur)
- Tomcat

Le programme se lance avec le serveur Tomcat qui ouvre un navigateur qui permet d'accéder à la page internet du chat sur le localhost avec le port 8080.

## Compilation
À la racine du projet et dans un invite de commande :  
`mvn compile`


## Constitution
le tp se compose en 2 partis avec une premiére page qui proposent:
- Un client Ajax
- Un client classic

## Client Classique
Le client classique se compose d'un formulaire d'inscription, avec un formulaire de connexion avec un login et un nom de salon, le salon accepte plusieurs utilisateurs s'ils sont bien inscrits avant.
Dans le salon on peut se déconnecter et écrire des messages.

## Client Ajax
On peut entrer le pseudo de quelqu'un et voir sur une autre page dans quel salon la personne est allé en créant un salon avec un nom , ainsi que les messages et la "User List".


