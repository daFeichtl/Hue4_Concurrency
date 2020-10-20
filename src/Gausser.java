import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Gausser {
    private int countTo;
    private int result;
    private final int maxThreadCount = 100;

    public Gausser(int countTo) {
        this.countTo = countTo;
    }

    public void calc(){
        int threads = (countTo/maxThreadCount)+1;
        int startVal=0;
        int endVal=0;
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(threads);
        List<? extends Callable<Integer>> callables=new ArrayList<>();
        GausserThread[] gausserThreads = new GausserThread[threads];
        for (int i = 1; i <= threads; i++) {
            if (startVal+maxThreadCount > countTo){
                endVal = countTo;
            }
            else
                endVal = startVal+maxThreadCount;
            gausserThreads[i-1] = new GausserThread(startVal,endVal);
            startVal = endVal+1;
        }
        callables = Arrays.asList(gausserThreads);
        try{
            for (final Future<Integer> future:pool.invokeAll(callables)){
                result+=future.get();
            }
        } catch (ExecutionException | InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Result: "+result);
        System.out.println("Gauss' Result: "+(countTo*countTo+countTo)/2);
    }

    class GausserThread implements Callable<Integer> {
        private int start;
        private int end;

        public GausserThread(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public Integer call() {
            int res = 0;
            for (int i = start; i <= end; i++) {
                res+=i;
            }
            return res;
        }
    }
}
