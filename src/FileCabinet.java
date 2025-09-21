import interfaces.Cabinet;
import interfaces.Folder;
import interfaces.MultiFolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileCabinet implements Cabinet {
    private List<Folder> folders;
    // 1. Jeśli getSize()/getName() może zwrócić null, należałoby użyć bezpieczniejszego porównania,
    // które nie rzuci NullPointerException

    @Override
    public Optional<Folder> findFolderByName(String name) {
        // findFolderByName wskazuje na unikalność nazw folderów
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Invalid name.");

        return flattenFolders().stream()
                .filter(f -> f.getName().equals(name))
                .findFirst();
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        if (size == null || !FileSize.isValid(size)) throw new IllegalArgumentException("Invalid size.");
        return flattenFolders().stream()
                .filter(f -> f.getSize().equals(size))
                .toList();
    }

    @Override
    public int count() {
        return flattenFolders().size();
    }

    private List<Folder> flattenFolders() {
        // w zależności od rozmairu listy folders, można rozważyć cacheowanie spłaszczonej listy,
        // żeby za każdym razem nie budować jej od nowa
        List<Folder> flatten = new ArrayList<>();
        if (folders == null) return flatten;

        for (Folder folder: folders) {
            depthFirstSearch(folder, flatten);
        }
        return flatten;
    }

    private void depthFirstSearch(Folder folder, List<Folder> flatten) {
        if (folder == null) return;
        flatten.add(folder);

        // zakłada się że folder może być subfolderem tylko jednego folderu,
        // w przeciwnym razie należałoby śledzić odwiedzone foldery
        if (folder instanceof MultiFolder multiFolder && multiFolder.getFolders() != null) {
            for (Folder subFolder: multiFolder.getFolders()) {
                depthFirstSearch(subFolder, flatten);
            }
        }
    }
}
