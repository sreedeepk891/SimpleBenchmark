public class SimpleBenchmark {

    public interface CodeSnippet {
        void runCode();
    }

    public static void main(String[] args) {
        CodeSnippet snippet1 = () -> {
            // First code snippet.
            /*
            String string1 = "hello";
            String string2 = "hello";
            boolean isEqual = string1.equals(string2); 
            */
        };

        CodeSnippet snippet2 = () -> {
            // Second code snippet.
            /*
            String string1 = "hello";
            String string2 = "hello";
            boolean isEqual = string1.toUpperCase().equals(string2.toUpperCase());
            */
        };

        int iterations = 100; // Set number of iterations.
        benchmark("Snippet 1", snippet1, iterations);
        benchmark("Snippet 2", snippet2, iterations);
    }

    public static void benchmark(String name, CodeSnippet snippet, int iterations) {
        long totalTime = 0;
        long startMemory = 0;
        long endMemory = 0;

        for (int i = 0; i < 20; i++) {
            snippet.runCode();
        }

        for (int i = 0; i < iterations; i++) {
            System.gc();

            startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

            long startTime = System.nanoTime();

            snippet.runCode();

            long endTime = System.nanoTime();

            endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

            totalTime += (endTime - startTime);
        }

        long averageTime = totalTime / iterations;
        long averageMemoryUsage = (endMemory - startMemory) / iterations;

        System.out.println(name + " - Average Time: " + averageTime + " ns");
        System.out.println(name + " - Average Memory Usage: " + averageMemoryUsage + " bytes");
    }
}