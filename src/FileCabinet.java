import interfaces.Cabinet;
import interfaces.Folder;

import java.util.List;
import java.util.Optional;

public class FileCabinet implements Cabinet {
    private List<Folder> folders;

    // zwraca dowolny element o podanej nazwie
    @Override
    public Optional<Folder> findFolderByName(String name) {
        if (name == null) throw new IllegalArgumentException("Invalid name.");

        // 1. findFolderByName wskazuje na unikalność nazw folderów
        // 2. jeśli nazwa folderu może być nullem, należałoby użyć bezpieczniejszego porównania,
        // które nie rzuci NullPointerException
        return folders.stream()
                .filter(f -> f.getName().equals(name))
                .findFirst();
    }

    // zwraca wszystkie foldery podanego rozmiaru SMALL/MEDIUM/LARGE
    // * sygnatura funkcji wskazuje na String, jendak rozważyłbym użycie Enum
    @Override
    public List<Folder> findFoldersBySize(String size) {
        if (size == null || !size.equals(Size.SIZE_SMALL)
            && !size.equals(Size.SIZE_MEDIUM)
            && !size.equals(Size.SIZE_LARGE)) throw new IllegalArgumentException("Invalid size.");

        return folders.stream()
                .filter(f -> f.getSize().equals(size))
                .toList();
    }

    //zwraca liczbę wszystkich obiektów tworzących strukturę
    @Override
    public int count() {
        return (folders != null) ? folders.size() : 0;
    }
}
