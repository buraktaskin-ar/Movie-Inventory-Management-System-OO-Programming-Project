package enums;

public enum TimeDirection {
    BEFORE, AFTER
}


/*
Bu TimeDirection adlı enum (sabitler) sınıfı, bir tarih veya zaman referansına göre zaman yönünü belirtmek için kullanılır. İki sabit içerir:

BEFORE: Belirli bir tarihten veya zamandan önce olanları temsil eder.
AFTER: Belirli bir tarihten veya zamandan sonra olanları temsil eder.

Bu sabitler, MovieList sınıfındaki whereYear metodu gibi zaman tabanlı işlemlerde kullanılabilir.
whereYear metodu çağrıldığında, 
TimeDirection.BEFORE parametresiyle çağrıldığında belirli bir yıldan önceki filmler listelenirken,
TimeDirection.AFTER parametresiyle çağrıldığında belirli bir yıldan sonraki filmler listelenir.

*/