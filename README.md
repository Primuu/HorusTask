# HorusTask

Zadanie polega na zaimplementowaniu metod findFolderByName, findFolderBySize, count w klasie FileCabinet - 
najchętniej unikając powielania kodu i umieszczając całą logikę w klasie FileCabinet. 
Z uwzględnieniem w analizie i implementacji interfejsu MultiFolder!

```java
public class FileCabinet implements Cabinet {
    private List<Folder> folders;
}
```

```java
interface Cabinet {

    // zwraca dowolny element o podanej nazwie
    Optional<Folder> findFolderByName(String name);
    
    // zwraca wszystkie foldery podanego rozmiaru SMALL/MEDIUM/LARGE
    List<Folder> findFoldersBySize(String size);
    
    //zwraca liczbę wszystkich obiektów tworzących strukturę
    int count();
}
```

```java
interface Folder {
    String getName();
    String getSize();
}
```


```java
interface MultiFolder extends Folder {
    List<Folder> getFolders();
}
```