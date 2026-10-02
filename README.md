# Game Backlog API — esercizio MVC in memoria

API didattica per un elenco di videogiochi: titolo, piattaforma, stato, voto e data di uscita. Allena DTO, validazione, aggiornamenti parziali, regole applicative e risposte HTTP.

## Stato e ruolo

**Esercizio di consultazione e ripasso, senza espansioni attive.** Le funzionalità sotto sono osservate nel codice; non è una release verificata per produzione.

## API presente

| Metodo | Endpoint | Risposta dichiarata |
| --- | --- | --- |
| GET | `/games` | 200, elenco |
| GET | `/games/{id}` | 200, dettaglio |
| POST | `/games` | 201, corpo e Location |
| PATCH | `/games/{id}` | 204 |
| DELETE | `/games/{id}` | 200 senza corpo |

Non è presente un endpoint PUT.

## Implementazione

- `GameService` conserva una lista in memoria con dieci giochi iniziali e ID progressivi.
- DTO `CreateGameRequest` e `PatchGameRequest`.
- Controllo dei titoli duplicati senza distinzione maiuscole/minuscole.
- In creazione: BACKLOG non ammette un voto; COMPLETED lo richiede.
- In PATCH: un gioco già COMPLETED non può passare a un altro stato.
- Error handler per input non valido, gioco inesistente e duplicato.

Esempio di creazione:

```json
{
  "title": "Gioco demo",
  "platform": "PC",
  "status": "BACKLOG",
  "rating": null,
  "releaseDate": "2025-01-01"
}
```

## Avvio

Java 21, Spring Boot 4.1.0, Maven Wrapper, Web MVC e Jakarta Validation. Non serve un database per il modello attuale.

```bash
./mvnw spring-boot:run
```

Su Windows usare `mvnw.cmd`. Porta predefinita: `8080`.

## Verifiche e limiti

```bash
./mvnw test
```

È presente il test di avvio del contesto; la copertura delle regole applicative resta da costruire.

- I dati vengono persi al riavvio.
- Non ci sono autenticazione, proprietari o persistenza JPA.
- Lista e contatore non hanno un controllo concorrente dedicato.
- La copia della lista non rende immutabili gli oggetti `Game`.
- PATCH modifica i campi in sequenza, senza rollback; un errore successivo può lasciare modifiche precedenti.
- La coerenza completa stato/voto non viene rivalidata in tutte le modifiche.

## Eventuale ripresa

Scegliere un solo caso di validazione o atomicità del PATCH e verificarlo con un test. Non introdurre automaticamente database o Security: il ruolo del repository resta quello di esercizio MVC.

Le note storiche in `docs/CONTEXT.md` precedono questo README; il codice è la fonte sul contratto attuale.
