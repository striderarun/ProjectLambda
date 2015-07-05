/**
 * @author arun_mohan
 *
 */
package com.arun.lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class LambdasTwo {
	
	// a static function that returns a predicate
	public static Predicate<String> checkIfStartsWith(final String letter) { 
		return name -> name.startsWith(letter);
	}
	
	//a Function that takes String as input and returns a predicate as output
	final static Function<String, Predicate<String>> startsWithLetter = letter -> name -> name.startsWith(letter);
	
	//Using Optional to avoid null checks
	public static void pickAnElementFromList(final List<String> names, final String startingLetter) {
		final Optional<String> foundName = names.stream().filter(name ->name.startsWith(startingLetter)).findFirst();
		System.out.println(String.format("A name starting with %s: %s", startingLetter, foundName.orElse("No name found")));
	}

	public static void main(String[] args) {

		final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
		final List<String> comrades = Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");
		final List<String> editors = Arrays.asList("Brian", "Jackie", "John", "Mike");
		
		/*friends.stream().filter(checkIfStartsWith("N")).collect(Collectors.toList()).forEach(System.out::println);
		comrades.stream().filter(checkIfStartsWith("K")).collect(Collectors.toList()).forEach(System.out::println);
		editors.stream().filter(checkIfStartsWith("J")).collect(Collectors.toList()).forEach(System.out::println);*/
		
		friends.stream().filter(startsWithLetter.apply("N")).collect(Collectors.toList()).forEach(System.out::println);
		comrades.stream().filter(startsWithLetter.apply("K")).collect(Collectors.toList()).forEach(System.out::println);
		editors.stream().filter(startsWithLetter.apply("J")).collect(Collectors.toList()).forEach(System.out::println);

		//pick a name starting with M
		pickAnElementFromList(editors, "M");

		/*Reduce operations on collections*/
		
		//Find total no of characters in friends
		Long sumOfCharacters = friends.stream().mapToLong(String::length).sum();
		System.out.println("Sum of characters in Friends " + sumOfCharacters);
		
		//Find longest name in Editors
		final Optional<String> longestName = editors.stream().reduce((name1,name2) -> name1.length() >= name2.length() ? name1:name2);
		longestName.ifPresent(System.out::println);
		
		//Pick up any name longer than default, if not found return default value
		final String longestNamethanDefault = editors.stream().reduce("ArunMohan",(name1,name2) -> name1.length() >= name2.length() ? name1:name2);
		System.out.println("Longest name is " + longestNamethanDefault);
		
		//Find sum of prices greater than 50 after applying 50% discount
		List<Integer> prices = Arrays.asList(10,20,20,30,40,50,60,70,80);
		Double discountedPriceTotal = prices.stream().filter(s -> s >=50).mapToDouble(s -> s*0.5).sum();		
		System.out.println("Discounted total= " + discountedPriceTotal);
	}

}
