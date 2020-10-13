import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileDivider{
    List<Integer> nums = new ArrayList<>();
    private int teiler;
    private int chunk;



    public FileDivider(int teiler, int chunck) {
        try {
            nums = Files.lines(new File("numbers.csv").toPath())
                    .flatMap(s -> Arrays.stream(s.split(":")))
                    .map(string -> {
                        try{
                            return Integer.parseInt(string);
                        }catch (NumberFormatException n){
                            return null;
                        }
                    }).filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.chunk = chunck;
        this.teiler = teiler;
    }

    public void calc(){
        int anzThreads = ((int) nums.size()/chunk)+1;

    }
    class Divider implements Runnable{
        private int startIndex;
        private int endIndex;
        private int result;

        public Divider(int startIndex, int endIndex, int result) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.result = result;
        }

        @Override
        public void run() {
            for (int i = startIndex; i <= endIndex; i++) {
                if (i%chunk == 0)
                    System.out.println(i);
            }
        }
    }
}
