### Uruchomienie aplikacji
1. Wymagania:
    - Java 21+
    - Internet

2. Build:
   `./gradlew build`

3. Run:
   `./gradlew bootRun`

Aplikacja wystartuje pod adresem:
`http://localhost:8080`

Endpointy zabezpieczony Bearer tokenem (ABC123):

Ranking:
```
curl --location --request GET "http://localhost:8080/api/spread/ranking" --header "Authorization: Bearer ABC123"
```

Kalkulacja:
```
curl --location --request POST "http://localhost:8080/api/spread/calculate" --header "Authorization: Bearer ABC123"
```