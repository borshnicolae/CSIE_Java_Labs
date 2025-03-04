package com.ase.csie;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.ase.csie.java.Amenity;
import com.ase.csie.java.HotelRoom;

public class MainStreams {

	public static void main(String[] args) {
		HotelRoom room1 = new HotelRoom(101, "Sea View", new Amenity[]{Amenity.WIFI, Amenity.TV});
		room1.setAvailable(true);

		HotelRoom room2 = new HotelRoom(102, "Mountain View", new Amenity[]{Amenity.MINI_BAR});
		room2.setAvailable(false);

		// Same location and amenities as room1
		HotelRoom room3 = new HotelRoom(103, "Sea View", new Amenity[]{Amenity.WIFI, Amenity.TV});
		room3.setAvailable(false);

		// Same room number and location as room2 but different availability
		HotelRoom room4 = new HotelRoom(104, "Mountain View", new Amenity[]{Amenity.MINI_BAR});
		room4.setAvailable(true);

		HotelRoom room5 = new HotelRoom(105, "Rooftop", new Amenity[]{Amenity.TV});
		room5.setAvailable(false);

		// Same amenities as room5 but different location
		HotelRoom room6 = new HotelRoom(106, "Garden Side", new Amenity[]{Amenity.TV});
		room6.setAvailable(true);

		HotelRoom room7 = new HotelRoom(107, "Poolside", new Amenity[]{Amenity.TV, Amenity.WIFI});
		room7.setAvailable(false);

		// Same as room7
		HotelRoom room8 = new HotelRoom(108, "Poolside", new Amenity[]{Amenity.TV, Amenity.WIFI});
		room8.setAvailable(false);

		HotelRoom room9 = new HotelRoom(109, "Desert Oasis", new Amenity[]{Amenity.WIFI, Amenity.MINI_BAR, Amenity.TV});
		room9.setAvailable(true);

		// Same as room9 but different availability
		HotelRoom room10 = new HotelRoom(110, "Desert Oasis", new Amenity[]{Amenity.WIFI, Amenity.MINI_BAR, Amenity.TV});
		room10.setAvailable(false);
		
		List<HotelRoom> hotel = Arrays.asList(room1, room2, room3, room4, room5, room6, room7, room8, room9, room10);
		System.out.println("List: " + hotel);

		long start = 0, stop = 0;
		long count = 0;
		start = System.currentTimeMillis();
		for (HotelRoom r : hotel) {
			if (r.isAvailable()) {
				count++;
			}
		}
		stop = System.currentTimeMillis();
		System.out.println("Available rooms count: " + count + ", ms = " + (stop - start));

		// pag. 46, 57, 58, 62, 64
		start = System.currentTimeMillis();
		Predicate<HotelRoom> predicate = r -> r.isAvailable();
		count = hotel.stream().filter(predicate).count();
		stop = System.currentTimeMillis();

		System.out.println("Available rooms: " + count + ", ms = " + (stop - start));
		
		start = System.nanoTime();
		count = hotel.stream().filter(r -> r.isAvailable()).count();
		// System.out.println("Empty Strings: " + count);
		stop = System.nanoTime();
		System.out.printf("\n co4 = %d, ns = %d \n", count, (stop - start));

		count = hotel.stream().filter(r -> r.getLocation().equals("Poolside")).count();
		System.out.println("Rooms with Poolside view: " + count);

		List<HotelRoom> filtered = hotel.stream().filter(r -> r.getLocation().equals("Sea View")).collect(Collectors.toList());
		System.out.println("Filtered List with Sea View rooms: " + filtered);

		String mergedString = hotel.stream().filter(r -> {
			for (Amenity a : r.getAmenities()) {
				if (Amenity.TV.equals(a)) {
					return true;
				}
			}
			return false;
		}).map(HotelRoom::toString).collect(Collectors.joining(", "));
		System.out.println("Merged String of rooms with TV: " + mergedString);

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

		// pag.54 compare collection vs stream

		// print ten random numbers
		Random random = new Random();

		for (int i = 0; i < 10; i++) {
			System.out.println(random.nextInt(10 - 1) + 1);
		}

		System.out.println("-----------------");
		random.ints(1, 10).limit(10).sorted().forEach(System.out::println);
		//////////////////////////////// .forEach( x -> System.out.println(x))

		// parallel processing
		start = System.currentTimeMillis();
		count = hotel.parallelStream().filter(r -> r.isAvailable()).count();
		stop = System.currentTimeMillis();
		System.out.println("Empty Strings - Parallel: " + count + ", ms = " + (stop - start));

	}

}
