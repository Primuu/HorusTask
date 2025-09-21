import java.util.Arrays;

public enum FileSize {
    SMALL,
    MEDIUM,
    LARGE;

    public static boolean isValid(String value) {
        return Arrays.stream(FileSize.values())
                .anyMatch(fileSize -> fileSize.name().equals(value));
    }
}
