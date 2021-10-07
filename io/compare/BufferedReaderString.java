package compare;
import java.io.IOException;

import compare.Utils;

public class BufferedReaderString {

  public static void main(String[] args) throws IOException, InterruptedException {
    while (true) {
      Thread.sleep(5000);
      /*
       * Read HUGE file & print time taken. 5 iterations will be done of each to get
       * fair results.
       */
      // Utils.measurePerformance(() -> Utils.readWithFileReader(Utils.HUGE_FILE_TO_READ));

      Utils.measurePerformance(() -> Utils.readWithBufferedReader(Utils.HUGE_FILE_TO_READ));

      // Utils.measurePerformance(() -> Utils.readWithNioFiles(Utils.HUGE_FILE_TO_READ));

      // Utils.measurePerformance(() -> Utils.readWithApacheCommons(Utils.HUGE_FILE_TO_READ));

      // Utils.measurePerformance(() -> Utils.readWithGoogleGuava(Utils.HUGE_FILE_TO_READ));
    }

  }

}