SkillExchange - Microservice Gamification
Ce microservice fait partie du projet SkillExchange, une plateforme d’échange de compétences. Il est dédié à la gamification : attribution de badges, suivi de points, progression et classement des utilisateurs.

🚀 Fonctionnalités
✅ Attribution automatique de points suite à des actions

🏅 Attribution manuelle ou automatique de badges

📈 Suivi de la progression utilisateur (points + niveaux)

📜 Historique des points obtenus

🧾 Liste des badges obtenus

🧑‍🤝‍🧑 Gestion des utilisateurs (ajout, listing)

🏆 Leaderboard (classement des utilisateurs)

📬 Envoi d’e-mails lors de l’attribution d’un badge

🧱 Technologies utilisées
Java 17

Spring Boot

Spring Data JPA

H2 / MySQL (selon le profil)

Eureka Client (discovery)

API Gateway

Lombok

Swagger (optionnel pour tester les endpoints)

Postman (pour les tests API)

📁 Structure du projet
bash
Copy
Edit
📦 skillexchange-gamification
┣ 📂 controller
┃ ┗ GamificationController.java
┣ 📂 service
┃ ┗ GamificationService.java
┣ 📂 entity
┃ ┣ Badge.java
┃ ┣ PointHistory.java
┃ ┣ UserProgress.java
┃ ┗ User.java
┣ 📂 repository
┃ ┣ BadgeRepository.java
┃ ┣ PointHistoryRepository.java
┃ ┣ UserProgressRepository.java
┃ ┗ UserRepository.java
┗ application.yml
🔗 Endpoints principaux
Méthode	URL	Description
POST	/api/gamification/points	Ajouter des points à un utilisateur
POST	/api/gamification/assign-badge	Attribuer un badge manuellement
GET	/api/gamification/badges/{userId}	Lister les badges d’un utilisateur
GET	/api/gamification/progress/{userId}	Voir la progression d’un utilisateur
GET	/api/gamification/history/{userId}	Voir l’historique des points
POST	/api/gamification	Ajouter un nouvel utilisateur
GET	/api/gamification/leaderboard	Voir le classement global
🧪 Exemple de requête Postman (ajout d’un utilisateur)
URL : POST /api/gamification

Body (JSON) :

json
Copy
Edit
{
"firstName": "khalil",
"lastName": "ayari",
"email": "ayari2014khalil@example.com"
}
📦 Lancer l'application
Cloner le projet :

bash
Copy
Edit
git clone https://github.com/ton-utilisateur/skillexchange-gamification.git
Ouvrir dans IntelliJ ou VS Code

Lancer le microservice avec GamificationApplication.java

⚠️ Assure-toi que :

Le serveur Eureka est lancé

Le Gateway est opérationnel

