package eu.ase.streams_lambda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Java8TestProcessingStreams {

	public static void main(String args[]) {
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl", "abc", "", "bc", "efg", "abcd",
				"", "jkl", "abc", "", "bc", "efg", "abcd", "", "jkl");
		System.out.println("List: " + strings);

		long start = 0, stop = 0;
		long count = 0;
		start = System.currentTimeMillis();
		for (String s : strings) {
			if (s != null && s.length() == 0) {
				count++;
			}
		}
		stop = System.currentTimeMillis();
		System.out.println("count: " + count + ", ms = " + (stop - start));

		start = System.currentTimeMillis();
		Predicate<String> predicate = string -> string.isEmpty();
		count = strings.stream().filter(predicate).count();
		stop = System.currentTimeMillis();

		System.out.println("Empty Strings: " + count + ", ms = " + (stop - start));

		count = strings.stream().filter(string -> string.length() == 3).count();
		System.out.println("Strings of length 3: " + count);

		List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
		System.out.println("Filtered List: " + filtered);

		String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
		System.out.println("Merged String: " + mergedString);

		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
		System.out.println("Squares List: " + squaresList);

		List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);
		System.out.println("List: " + integers);

		List<String> strs = Arrays.asList("1", "2");

		IntSummaryStatistics stats = strs.stream().mapToInt((x) -> Integer.parseInt(x)).summaryStatistics();

		System.out.println("Highest number in List : " + stats.getMax());
		System.out.println("Lowest number in List : " + stats.getMin());
		System.out.println("Sum of all numbers : " + stats.getSum());
		System.out.println("Average of all numbers : " + stats.getAverage());
		System.out.println("Random Numbers: ");

		Random random = new Random();

		for (int i = 0; i < 10; i++) {
			System.out.println(random.nextInt(10 - 1) + 1);
		}

		System.out.println("-----------------");
		random.ints(1, 10).limit(10).sorted().forEach(System.out::println);
		//////////////////////////////// .forEach( x -> System.out.println(x))

		start = System.currentTimeMillis();
		count = strings.parallelStream().filter(string -> string.isEmpty()).count();
		stop = System.currentTimeMillis();
		System.out.println("Empty Strings - Parallel: " + count + ", ms = " + (stop - start));

	}
}