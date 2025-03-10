package avrel.trywithresources;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Resource resource = new Resource("Aurel", true);

        try(resource) {
            resource.doSome(true);
        } catch (Exception exception) {
            Throwable[] suppressed = exception.getSuppressed();

            Arrays.stream(suppressed).forEach((suppressedException) -> System.out.println(suppressedException.getMessage()));
        }
    }
}
