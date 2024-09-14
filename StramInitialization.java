package com.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class StramInitialization {

	public static void main(String[] args) {
		
		//from collection
		List<String> values = List.of("One", "Two", "Three", "Four");
		Stream<String> stream1 = values.stream();
		
		//Arrays of values
		String[] names = {"One","Two","Three","Four"};
		Stream<String> stream2 = Arrays.stream(names);
		
		//Stream methods
		Stream<String> stream3 = Stream.of("One","Two","Three","Four");
		
		//generate() method
		Stream<String> stream4 = Stream.generate(() -> "One");
		
		//Stream Builder
		Stream.Builder<String> streamBuilder = Stream.builder();
		Stream<String> stream5 = streamBuilder.add("One").add("Two").add("Three").add("Four").build();
		
		//Empty Stream
		Stream<String> stream6 = Stream.empty();
		
		//
		//
		
		//reduce()
		Optional<String> longestString     =   stream1.reduce((word1, word2) 
				-> word1.length() > word2.length() ? word1 : word2);
		
		longestString.ifPresent(System.out::println);
		
		
		

	}

}
