import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

public class FileDivider{
    List<Integer> nums = new ArrayList<>();
    ThreadPoolExecutor threadPoolExecutor;
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
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(anzThreads);
        for (int i = 0; i < anzThreads; i++) {
            threadPoolExecutor.execute(new Divider(i*chunk,(i+1)*chunk-1));
        }
    }
    public void stop(){
        threadPoolExecutor.shutdown();
    }
    class Divider implements Runnable{
        private int startIndex;
        private int endIndex;

        public Divider(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
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
