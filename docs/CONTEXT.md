# Contesto tecnico — Game Backlog API

Aggiornato: 2026-09-08

## Stato verificato
Il repository non contiene attualmente un README. Dal `pom.xml` risultano:
- Java 21;
- Spring Boot 4.1.0;
- Spring Web / Web MVC;
- Spring Data REST;
- Jakarta Validation.

## Incertezza esplicita
Lo scope funzionale, gli endpoint effettivi e il livello di completamento non sono documentati in modo sufficiente. Prima di riprendere il progetto bisogna leggere `src` e verificare il comportamento reale.

## Regola di ripresa
Non assumere automaticamente un CRUD completo o un particolare modello Game. Prima di qualsiasi evoluzione ricostruire lo stato dal codice e, se il progetto viene ripreso seriamente, aggiungere o aggiornare il README.