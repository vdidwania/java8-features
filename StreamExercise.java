package com.stream;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExercise {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> strings = Arrays
	              .asList("apple", "banana", "cherry", "date", "grapefruit");
		//longest string
		System.out.println(strings.stream().max(Comparator.comparingInt(str -> str.length())).get());
		System.out.println(strings.stream().max((s1, s2) -> s1.length() - s2.length()).get());

		
		//Calculate the average age of a list of Person objects using Java streams:
		List<Person> persons = Arrays.asList(
			    new Person("Alice", 25),
			    new Person("Bob", 30),
			    new Person("Charlie", 35)
			);
		double averageAge = persons.stream()
                .mapToDouble(Person::getAge)
                .average()
                .orElse(0);
		System.out.println(averageAge);
		System.out.println(persons.stream().mapToInt(Person::getAge).average().orElse(0));
		
		//Check if a list of integers contains a prime number using Java streams:
		List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10, 11, 12, 13, 14, 15);
		
		System.out.println(numbers.stream().peek(System.out::println).anyMatch(count -> isPrime(count)));
		
		//Find the kth smallest element in an array using Java streams:
		int[] array = {4, 2, 7, 1, 5, 3, 6};
		int k = 3;
		int kthLowest = Arrays.stream(array)
				.boxed()
				.sorted((e1, e2) -> e1-e2)
				.limit(k)
				.sorted((e1, e2) -> e2-e1)
				.findFirst().orElse(-1);
		
		kthLowest = Arrays.stream(array)
				.sorted()
				.skip(k-1)
				.findFirst()
				.orElse(-1);

		System.out.println(kthLowest);
		
		
		//Given a list of strings, find the frequency of each word using Java streams:
		List<String> words = Arrays.asList("apple", "banana", "apple", "cherry", 
                "banana", "apple");
		
		System.out.println(words.stream().
			collect(Collectors
					.groupingBy(
							//str -> str,
						Function.identity(),
							Collectors.counting())));
		
		//Implement a method to partition a list into two groups based on a predicate using Java streams:
		numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		Map<Boolean, List<Integer>> partitioned = numbers.stream()
				.collect(Collectors
						.partitioningBy(n -> n%2 ==0));
		List<Integer> evenNumbers = partitioned.get(true);
		List<Integer> oddNumbers = partitioned.get(false);
		
		System.out.println("evenNumbers:"+evenNumbers);
		System.out.println("oddNumbers:"+oddNumbers);
		
		//Given a list of transactions, find the sum of transaction amounts for each day using Java streams:
		
		List<Transaction> transactions = Arrays.asList(
			    new Transaction("2022-01-01", 100),
			    new Transaction("2022-01-01", 200),
			    new Transaction("2022-01-02", 300),
			    new Transaction("2022-01-02", 400),
			    new Transaction("2022-01-03", 500)
			);
		
		Map<String, Integer> sumByDate = transactions.stream()
											.collect(Collectors
											.groupingBy(
													Transaction::getDate,
													Collectors.summingInt(Transaction::getAmount)));
		System.out.println(sumByDate);
		
		
		//get the numbers starting with digit 1
		//method 1- using toString
		numbers = Arrays.asList(1, 12, 34, 54, 56, 67, 17, 18, 45, 10, 9);
		List<Integer> listStartWithOne = numbers.stream()
											.filter(num -> Integer.toString(num).startsWith("1"))
											.toList();
		System.out.println(listStartWithOne);

		//method 2- using math ops
		List<Integer> listStartWithOne2 = numbers.stream()
											.filter(num -> {
												int i = num;
												while(i > 9) {
													i /= 10;
												}
												return i == 1;
											})
											.toList();
		
		System.out.println(listStartWithOne2);
		
		//find frequency of each character in a string
		String inputString = "Java Concept Of The Day";	
		Map<Character, Long> charCountMap = 
		inputString.chars()	
			.mapToObj(ch -> (char) ch)
			.collect(Collectors
					.groupingBy(ch -> ch,
							Collectors.counting()));
		
		System.out.println(charCountMap);
		
		//find frequency of each element in an array or a list
		List<String> stationeryList = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Stapler", "Note Book", "Pencil");
		Map<String, Long> freqMap = stationeryList.stream()
											.collect(Collectors
													.groupingBy(
															Function.identity(), 
															Collectors.counting()));
		System.out.println(freqMap);
		
		//sort the given list of decimals in reverse order
		List<Double> decimalList = Arrays.asList(12.45, 23.58, 17.13, 42.89, 33.78, 71.85, 56.98, 21.12);
        List<Double> sortedList = decimalList.stream().sorted(Comparator.reverseOrder()).toList();
        
		System.out.println(sortedList);

		//Given a list of strings, join the strings with ‘[‘ as prefix, ‘]’ as suffix and ‘,’ as delimiter
		List<String> listOfStrings = Arrays.asList("Facebook", "Twitter", "YouTube", "WhatsApp", "LinkedIn");
		//Method 1
		String joinedStr = listOfStrings.stream().map(str -> "["+str+"]").collect(Collectors.joining(","));
		System.out.println(joinedStr);
		//Method 2
		joinedStr = listOfStrings.stream().collect(Collectors.joining(",", "[", "]"));
		System.out.println(joinedStr);
		
		//From the given list of integers, print the numbers which are multiples of 5
		List<Integer> listOfIntegers = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
		listOfIntegers.stream().forEach(num -> {if(num%5 ==0) System.out.println(num);});
		listOfIntegers.stream()
		.filter(num -> num%5==0)
		.forEach(System.out::println);
		
		//Given a list of integers, find maximum and minimum of those numbers
		listOfIntegers = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
		System.out.println("max:"+listOfIntegers.stream().max(Comparator.comparingInt(i -> i)).get());	
		System.out.println("max:"+listOfIntegers.stream().max(Comparator.naturalOrder()).get());
		
		System.out.println("min:"+listOfIntegers.stream().min(Comparator.comparingInt(i -> i)).get());	
		System.out.println("max:"+listOfIntegers.stream().min(Comparator.naturalOrder()).get());
		
		//merge two unsorted arrays into single sorted array
		int[] a = new int[] {4, 2, 7, 1};
        
        int[] b = new int[] {8, 3, 9, 5};
        
        System.out.println(Stream.concat(Arrays.stream(a).boxed(), Arrays.stream(b).boxed()).sorted().toList());
        
        //merge two unsorted arrays into single sorted array without duplicates
        int[] x = new int[] {4, 2, 7, 1, 1, 8};
        
        int[] y = new int[] {8, 3, 9, 5, 1, 2, 9, 7};
        System.out.println(Stream.concat(Arrays.stream(a).boxed(), Arrays.stream(b).boxed()).sorted().distinct().toList());

        //get three maximum numbers and three minimum numbers from the given list of integers
		listOfIntegers = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89, 110, 15, 25, 135, 9, 215);
		System.out.println("Min 3:"+listOfIntegers.stream().sorted().limit(3).toList());
		System.out.println("Max 3:"+listOfIntegers.stream().sorted(Comparator.reverseOrder()).limit(3).toList());
		
		//check if two strings are anagrams or not
		String s1 = "RaceCar";
        String s2 = "CarRace";
        
        s1= Stream.of(s1.split("")).sorted().collect(Collectors.joining());
        s2= Stream.of(s2.split("")).sorted().collect(Collectors.joining());
        if(s1.equals(s2)) {
        	System.out.println("Two strings are anagrams");
        }else {
        	System.out.println("Two strings are not anagrams");
        }
        


	}
	public static boolean isPrime(int number) {
		  if (number <= 1) {
		    return false;
		  }
		  for (int i = 2; i <= Math.sqrt(number); i++) {
		    if (number % i == 0) {
		        return false;
		    }
		  }
		  return true;
		}

}
