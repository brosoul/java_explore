package compare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class Utils {

  public static final String FILE_TO_READ = "/Users/xulinhui/Workspace/data_in/temp/test_file/file";
  public static final String HUGE_FILE_TO_READ = "/Users/xulinhui/Workspace/data_in/temp/test_file/file_1g";

  public static void main(String[] args) throws IOException, InterruptedException {
    while (true) {
      Thread.sleep(5000);
      /*
       * Read HUGE file & print time taken. 5 iterations will be done of each to get
       * fair results.
       */
      measurePerformance(() -> readWithFileReader(HUGE_FILE_TO_READ));

      // measurePerformance(() -> readWithBufferedReader(HUGE_FILE_TO_READ));

      // measurePerformance(() -> readWithNioFiles(HUGE_FILE_TO_READ));

      // measurePerformance(() -> readWithApacheCommons(HUGE_FILE_TO_READ));

      // measurePerformance(() -> readWithGoogleGuava(HUGE_FILE_TO_READ));
    }

  }

  /**
   * Measure method performance execution time using java.time.Duration &
   * java.time.Instant.
   * 
   * @param method
   * @return Time taken for method in seconds or millis
   */
  public static void measurePerformance(Runnable method) {

    Instant start = Instant.now();
    method.run();
    Duration duration = Duration.between(start, Instant.now());
    System.out.println(duration.toMillis());
    Runtime r = Runtime.getRuntime();
    long freeMemory = r.freeMemory();
    long totalMemory = r.totalMemory();
    long maxMemory = r.maxMemory();
    long usedMemory = (totalMemory - freeMemory);
    System.out.println(freeMemory / (1024 * 1024) + ", " + totalMemory / (1024 * 1024) + ", "
        + maxMemory / (1024 * 1024) + ", " + usedMemory / (1024 * 1024));
  }

  /**
   * Read file with Java's FileReader
   * 
   * @param fileToRead
   * @return
   */
  public static String readWithFileReader(String fileToRead) {
    String readData = "";
    try (FileReader fileReader = new FileReader(fileToRead, Charset.forName("ISO-8859-1"))) {
      while (fileReader.ready()) {
        char[] c = new char[8192];
        fileReader.read(c);
        readData = readData + new String(c);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return readData;
  }

    /**
   * Read file with Java's Scanner
   * 
   * @param fileToRead
   * @return
   */
  public static List<String> readWithScanner(String fileToRead) {
    List<String> list = new ArrayList<>();
    try (Scanner scanner = new Scanner(new File(fileToRead), Charset.forName("ISO-8859-1"))) {
			while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        list.add(line);
			}
    } catch (IOException e) {
      e.printStackTrace();
    }
    return list;
  }

  

  /**
   * Read file with Java's BufferedReader
   * 
   * @param fileToRead
   * @return
   */
  public static List<String> readWithBufferedReader(String fileToRead) {
    List<String> list = new ArrayList<>();
    try (FileReader fileReader = new FileReader(fileToRead, Charset.forName("ISO-8859-1"));
        BufferedReader bufferedReader = new BufferedReader(fileReader)) {
      for (;;) {
        String line = bufferedReader.readLine();
        if (line == null)
          break;
        list.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * Read file with Java's NIO Files
   * 
   * @param fileToRead
   * @return
   */
  public static List<String> readWithNioFiles(String fileToRead) {
    List<String> list = null;
    try {
      // System.out.println(Charset.defaultCharset().name());
      list = Files.readAllLines(Path.of(fileToRead), Charset.forName("ISO-8859-1"));
      // readData = Files.readString(Path.of(fileToRead));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * Read file with Apache commons-io FileUtils
   */
  public static List<String> readWithApacheCommons(String fileToRead) {
    List<String> list = null;
    try {
      // System.out.println(Charset.defaultCharset().name());
      list = FileUtils.readLines(new File(fileToRead), Charset.forName("ISO-8859-1"));
      // readData = FileUtils.readFileToString(new File(fileToRead), "UTF-8");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * Read file with google guava common io Files.
   * 
   * @param fileToRead
   * @return
   */
  public static List<String> readWithGoogleGuava(String fileToRead) {
    List<String> list = null;
    try {
      // System.out.println(Charset.defaultCharset().name());
      list = com.google.common.io.Files.readLines(new File(fileToRead), Charset.forName("ISO-8859-1"));
      list = com.google.common.io.Files.asCharSource(new File(fileToRead), Charset.forName("ISO-8859-1")).readLines();
      // readData = com.google.common.io.Files.asCharSource(new File(fileToRead),
      // Charset.defaultCharset()).read();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return list;
  }
}
