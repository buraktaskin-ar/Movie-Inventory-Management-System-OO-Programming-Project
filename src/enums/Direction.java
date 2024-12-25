package enums;

public enum Direction {
    FORWARD, BACKWARD
}



/*
Bu Direction adlı enum (sabitler) sınıfı, bir liste veya veri yapısı üzerinde ilerleme yönünü belirtmek için kullanılır.
İki sabit içerir:

FORWARD: İleri yönde ilerlemeyi temsil eder.
BACKWARD: Geriye doğru ilerlemeyi temsil eder.

Bu sabitler, özellikle MovieList sınıfındaki print metodu gibi yönlendirme
işlemlerinde kullanılabilir. print metodu çağrıldığında, Direction.
FORWARD parametresi ile çağrıldığında listenin öğeleri sıralı olarak ileri yönde yazdırılırken,
Direction.BACKWARD parametresi ile çağrıldığında liste geriye doğru sıralı olarak yazdırılır.


*/