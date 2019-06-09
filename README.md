# MovTrack
Aplikacja webowa do tworzenia listy filmów do obejrzenia.
 
# Wykorzystane Narzędzia
 - [TMDB Api](https://developers.themoviedb.org/3/) do pobierania informacji,szukania filmów.
 - [Vaadin Flow](https://vaadin.com/flow) do utworzenia aplikacji web
 - [JUnit 5](https://junit.org/junit5/) do testowania
 - [Maven](https://maven.apache.org/) do zarządzania projektem
 - [IntelliJ IDEA](https://www.jetbrains.com/idea/) środowisko programistyczne
 - [jsonschema2pojo](http://www.jsonschema2pojo.org/) do generawania klas Javy z JSON'ów
 - [Spring Boot](https://spring.io/projects/spring-boot) do serwera web i obsługi baz danych

# Struktura projektu
Folder / Klasa  |   Użycie
--------------- |------------
RestClient      | Klasy do pobierania informacji z TMDB
Test            | Klasy testów JUnit
Views           | Klasy widoku na stronie
Banner          | Klasa wyświetlająca Banner strony
SearchResults   | Klasa wyświetlająca informacje o wyszukanym filmie
MediaBar        | Klasy wyświetlająca listę filmów w poziomie
List            | Klasy do obsługi bazy danych

# Status
 - [X] Szukanie filmów
 - [X] Wyświetlanie informacji o filmach
 - [X] Wyświetlanie zdjecie filmu
 - [X] Dodawanie do listy obejrzanych/do obejrzenia
 - [X] Zapisywanie stanu listy
 - [X] Wyświetlanie rekomendacji do filmów
 - [X] Wyświetlanie zapisanych filmów na liście

# Start
Aby otworzyć projekt, wystarczy otworzyć wybranym środowisku progrmaistycznym (**_Najlepiej IntelliJ IDEA_**)
plik `pom.xml`

# Uruchomienie Aplikacji
Po zbudowaniu aplikacji, wystarczy uruchomić klasę `MovTrackApp` w której znajduje się main()

Następnie otworzyć przeglądarkę na http://localhost:8080

# Baza danych H2
Panel sterowania bazą znajduje się na http://localhost:8080/h2

Domyślne ustawienia:

    JDBC URL: jdbc:h2:mem:database
    User Name: sa
    Password: [blank]

Ustawienia można zmienić w `src/main/resources/application.properties`

Aby baza danych została zapisana w pliku wystarczy zmienić:

    spring.datasource.url = jdbc:h2:mem:database
    na
    spring.datasource.url = jdbc:h2:file:[ścieżka do pliku]
    
# Sposób realizacji projektu
Aplikacja używa Vaadin Flow, frameworku Java do budowania aplikacji webowych i Spring Boot dla ułatwienia pisania projektu.
Informacje o filmach lub serialach są uzyskiwane z TMDB api. Aby umożliwić zapisywanie filmów do list, używana jest baza danych H2.


# Wnioski
 Pisanie aplikacji webowej przy pomocy Vaadin Flow jest bardzo przyjemne i proste. Framework ten jest bardzo intuicyjny.
 
 Spring Boot także ułatwia pisanie projektu, nie potrzebuje konfiguracji serwera web i pozwala na szybkie połączenie wybraną bazą danych.
 
 Najwięcej problemów miałem z usuwaniem błędów związanych z niekompletnymi zależnościami dla JAX-RS, które jest używane przez klienta REST do
 pobierania informacji o filmach. 
 
 Najciekawszym narzędziem był Spring Boot, który przy pisaniu interfejsu dla bazy danych nie potrzebował
 implementacji metod do pobierania informacji BD, bo robił to automatycznie.
    