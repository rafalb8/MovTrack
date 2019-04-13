# MovTrack
Aplikacja webowa do tworzenia listy filmów do obejrzenia.
 
# Wykorzystane Narzędzia
 - [OMDB Api](http://www.omdbapi.com/) do pobierania informacji,szukania filmów.
 - [Vaadin Flow](https://vaadin.com/flow) do utworzenia aplikacji web
 - [JUnit 5](https://junit.org/junit5/) do testowania
 - [Maven](https://maven.apache.org/) do zarządzania projektem
 - [IntelliJ IDEA](https://www.jetbrains.com/idea/) środowisko programistyczne

# Struktura projektu
Folder / Klasa  |   Użycie
--------------- |------------
RestClient      | Klasy do pobierania informacji z OMDB
Test            | Klasy testów JUnit
Views           | Ułożenia przycisków na stronie
Banner          | Klasa wyświetlająca Banner strony
MovieEntry      | Klasa wyświetlająca informacje o wyszukanym filmie
WatchlistBar    | Klasa wyświetlająca listę filmów do obejrzenia
WarchListButton | Przycisk do dodawania filmu do listy

#Status
 - [X] Szukanie filmów
 - [X] Wyświetlanie informacji o filmach
 - [X] Wyświetlanie zdjecie o filmu
 - [ ] Dodawanie do listy do obejrzenia
 - [ ] Zapisywanie listy

# Start
Aby otworzyć projekt, wystarczy otworzyć wybranym środowisku progrmaistycznym (**_Najlepiej IDEA_**)
plik *pom.xml*

# Uruchomienie Aplikacji
Po zbudowaniu aplikacji, wystarczy uruchomić web serwer Jetty przez plugin Maven'a:

    jetty:run

W środowisku **IDEA** wystarczy:

    2x Nacisnąć Shift
    > maven
    Enter
    
    Wybrać MovTrack -> Plugins -> jetty -> jetty:run

Następnie otworzyć przeglądarkę na http://localhost:8080
