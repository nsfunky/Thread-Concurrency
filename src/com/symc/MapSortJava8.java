package com.symc;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapSortJava8 {

	public static void main(String[] args) 
	{
		/*
		amazon    1
		facebook  2
		linkedin  1
		gmail     3
		symantec  1 
		*/
		
		Map<String, Integer> map = new HashMap<>();
		
		map.put("amazon", 1);
		map.put("facebook", 2);
		map.put("linkedin", 1);
		map.put("gmail", 3);
		map.put("symantec", 1);
		

		map.entrySet()
		  .stream()
		  .sorted(Map.Entry.<String, Integer>comparingByKey())
		  .forEach(System.out::println);
		
		System.out.println("------------");
		
		map.entrySet()
		  .stream()
		  .sorted(Map.Entry.comparingByValue())
		  .forEach(System.out::println);
		
		System.out.println("------------");
		
		Map<String, Integer> result = map.entrySet()
				  .stream()
				  .sorted(Map.Entry.comparingByValue())
				  .collect(Collectors.toMap(
				    Map.Entry::getKey, 
				    Map.Entry::getValue, 
				    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		
		System.out.println("Result >>>>\n"+result);
		/*
		
		SortedSet<String> keySet = new TreeSet<>(map.keySet());
		
		System.out.println(keySet);
		
		
		SortedSet<Integer> values = new TreeSet<>(map.values());
		
		System.out.println(values);
		
		*/
	}

}
