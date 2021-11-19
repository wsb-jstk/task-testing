# Zadanie 1

Dopasuj projekt, dodając do niego odpowiednią konfigurację maven/gradle, a w niej JUnit 5 (bez Vintage) oraz Hamcrest.
Stwórz odpowiednią strukturę katalogów do testów jednostkowych.

# Zadanie 2

Napisz testy jednostkowe dla klas CustomLinkedList oraz CustomArrayList, które pokryją kod tych klas w minimum 90%.
Przetestuj również wewnętrzne klasy iteratorów. Uwzględnij „happy path”, przypadki brzegowe oraz możliwość pojawienia
się błędnych danych wejściowych. Do testów CustomLinkedList użyj asercji z JUnit, natomiast do CustomArrayList –
hamcresta.

Wskazówka: na ile to możliwe, staraj się korzystać z dokumentacji JDoc, a nie kodu źródłowego klas, aby pisać testy nie
opierając się o już gotową implementację.

CustomArrayList zawiera wbudowany mechanizm realokacji, który zwiększy długość tablicy, gdyby miała ona zapełnić się w
90% lub zmniejszy w przypadku spadnięcia zapełnienia poniżej 60%. Nowy rozmiar tablicy po realokacji będzie taki, żeby
była ona zapełniona w 75%. Napisz testy, które sprawdzą, czy tablica faktycznie jest zwiększana („czy”, a nie „o ile”).

Dodatkowe informacje na temat tego, co robia dane metody, mozecie znalezc podgladajac
interfejs [java.util.List](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html).

# Zadanie 3

Stosując podejście TDD, napisz testy jednostkowe dla metod znajdujących się w klasie NumberHelper.

# Zadanie 4

Zaimplementuj metodę NumberHelper.average(List<Integer> list) tak, aby wszystkie Twoje testy jednostkowe były zielone.

# Zadanie 5

Przy pomocy refleksji przetestuj mechanizm realokacji rozmiaru tablicy w CustomArrayList.

# Zadanie 6

Stosując podejście TDD, napisz implementację klasy [Factorial](https://en.wikipedia.org/wiki/Factorial) (PL: silnia),
która posiada metodę `calculateFactorial()`. Podpowiedzi:

- fake it till You make it
- [Green - Red - Refactor](https://www.codecademy.com/articles/tdd-red-green-refactor)
