import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class JavaStreamsTester {
    public static void test(){
        List<String> stringList = Arrays.asList("", "asjdlfaj", "abc", "asdf");
        List<Integer> integerList = Arrays.asList(3,6,4,2,7);
        System.out.println(getCountEmptyString(stringList));
        System.out.println(getCountLength3(stringList));
        for (String s:deleteEmptyStrings(stringList)){
            System.out.println(s);
        }
        System.out.println(getMergedString(stringList));
        for (Integer i:getSquares(integerList)){
            System.out.println(i);
        }
        System.out.println(getMax(integerList));
        System.out.println(getMin(integerList));
        System.out.println(getSum(integerList));
        System.out.println(getAverage(integerList));
    }
    private static int getCountEmptyString(List<String> strings) {
        return strings.stream().filter(String::isEmpty).collect(Collectors.toList()).size();
    }

    private static int getCountLength3(List<String> strings) {
        return strings.size() - strings.stream().filter(s -> s.length() != 3).collect(Collectors.toList()).size();
    }

    private static List<String> deleteEmptyStrings(List<String> strings) {
        return strings.stream().filter(String::isEmpty).collect(Collectors.toList());
    }

    private static String getMergedString(List<String> strings) {
        return strings.stream().collect(Collectors.joining());
    }

    private static List<Integer> getSquares(List<Integer> numbers) {
        return numbers.stream().mapToInt(i -> (int) Math.pow(i,2)).boxed().collect(Collectors.toList());
    }

    private static int getMax(List<Integer> numbers) {
        return numbers.stream().max(Comparator.comparing(Integer::valueOf)).get();
    }

    private static int getMin(List<Integer> numbers) {
        return numbers.stream().min(Comparator.comparing(Integer::valueOf)).get();
    }

    private static int getSum(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }
    private static int getAverage(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).sum()/numbers.size();
    }
}
