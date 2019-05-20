# MovTrack
Aplikacja webowa do tworzenia listy filmów do obejrzenia.
 
# Wykorzystane Narzędzia
 - [TVDB Api](https://developers.themoviedb.org/3/) do pobierania informacji,szukania filmów.
 - [Vaadin Flow](https://vaadin.com/flow) do utworzenia aplikacji web
 - [JUnit 5](https://junit.org/junit5/) do testowania
 - [Maven](https://maven.apache.org/) do zarządzania projektem
 - [IntelliJ IDEA](https://www.jetbrains.com/idea/) środowisko programistyczne
 - [jsonschema2pojo](http://www.jsonschema2pojo.org/) do generawania klas Javy z JSON'ów

# Struktura projektu
Folder / Klasa  |   Użycie
--------------- |------------
RestClient      | Klasy do pobierania informacji z TMDB
Test            | Klasy testów JUnit
Views           | Ułożenia przycisków na stronie
Banner          | Klasa wyświetlająca Banner strony
SearchResults   | Klasa wyświetlająca informacje o wyszukanym filmie
MediaBar        | Klasa wyświetlająca listę filmów w poziomie
List            | Klasy do obsługi bazy danych

# Status
 - [X] Szukanie filmów
 - [X] Wyświetlanie informacji o filmach
 - [X] Wyświetlanie zdjecie filmu
 - [X] Dodawanie do listy obejrzanych/do obejrzenia
 - [X] Zapisywanie stanu listy
 - [X] Wyświetlanie rekomendacji do filmów
 - [ ] Design

# Start
Aby otworzyć projekt, wystarczy otworzyć wybranym środowisku progrmaistycznym (**_Najlepiej IntelliJ IDEA_**)
plik `pom.xml`

# Uruchomienie Aplikacji
Po zbudowaniu aplikacji, wystarczy uruchomić klasę `MovTrackApp` w której znajduje się Main()

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
    