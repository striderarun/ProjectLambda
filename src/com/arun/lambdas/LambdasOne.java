package com.arun.lambdas;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class LambdasOne {

	public static void main(String[] args) {

		/*Basic Lambda Operations*/
		 
		/*Create a list of String*/
		List<String> animals = Arrays.asList("tiger","lion","leopard","TIGER");
		
		/**Filtering Collections
		 * 
		 **/
		
		/*Create a new list containing only tigers*/
		List<String> tigers = animals.stream().filter((s -> s.equalsIgnoreCase("tiger"))).collect(Collectors.toList());
		
		/*Create a new list containing tigers or leopards*/
		Predicate<String> tigerFilter = s -> s.equalsIgnoreCase("tiger");
		Predicate<String> tigerOrLeopardFilter = tigerFilter.or(s -> s.equalsIgnoreCase("leopard"));
		List<String> tigerOrLeopard = animals.stream().filter(tigerOrLeopardFilter).collect(Collectors.toList());
		
		/*Create a new list containing animals starting with l*/
		List<String> animalsStartingWithL = animals.stream().filter(s -> s.startsWith("l")).collect(Collectors.toList());
		
		/*Create a string with filtered elements separated by a delimiter*/
		String filteredString = animals.stream().filter(s -> s.equalsIgnoreCase("tiger")).collect(Collectors.joining("*"));
		
		/*Iterating through the list;print the elements of the collection*/
		System.out.println("List of Tigers");
		tigers.stream().forEach(s -> System.out.println(s)); //using lambdas and Stream API
		
		System.out.println("List of Animals");
		animals.forEach(s -> System.out.println(s)); //using lambdas without Stream API
		
		System.out.println("List of Tigers or Leopards");
		tigerOrLeopard.forEach(System.out::println); //using Method References

		/**Transforming a collection
		 * Create an integer list with length of each String element in animals
		 * namesLength1 -> lambdas
		 * namesLength2 -> method references
		 * upperCaseList -> converting all elements to upper case
		 **/
		List<Integer> namesLength1 = animals.stream().map(s -> s.length()).collect(Collectors.toList()); 
		List<Integer> namesLength2 = animals.stream().map(String::length).collect(Collectors.toList());
		List<String> upperCaseList = animals.stream().map(String::toUpperCase).collect(Collectors.toList());

													
	}

}
