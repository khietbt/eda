CREATE DATABASE `keycloak-service-db`;

CREATE USER 'keycloak-service-db-username'@'%' IDENTIFIED BY 'keycloak-service-db-password';

GRANT ALL PRIVILEGES ON `keycloak-service-db`.* TO 'keycloak-service-db-username'@'%';