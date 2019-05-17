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
RestClient      | Klasy do pobierania informacji z OMDB
Test            | Klasy testów JUnit
Views           | Ułożenia przycisków na stronie
Banner          | Klasa wyświetlająca Banner strony
MovieEntry      | Klasa wyświetlająca informacje o wyszukanym filmie
WatchlistBar    | Klasa wyświetlająca listę filmów do obejrzenia
ListButton      | Przycisk do dodawania filmu do listy

# Status
 - [X] Szukanie filmów
 - [X] Wyświetlanie informacji o filmach
 - [X] Wyświetlanie zdjecie filmu
 - [ ] Dodawanie do listy obejrzanych/do obejrzenia
 - [ ] Zapisywanie stanu listy

# Start
Aby otworzyć projekt, wystarczy otworzyć wybranym środowisku progrmaistycznym (**_Najlepiej IntelliJ IDEA_**)
plik `pom.xml`

# Uruchomienie Aplikacji
Po zbudowaniu aplikacji, wystarczy uruchomić `MovTrackApp`

Następnie otworzyć przeglądarkę na http://localhost:8080
