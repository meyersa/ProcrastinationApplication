/*
 * Procrastination Application
 * August Meyers
 * Sort.java
 * Sort utility
 */

package main;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

	/**
	 * Sort Class
	 * Sort Utility Wrapper
	 */

public class Sort {
	
	/**
	 * sortMapByValue Method
	 * Sorts the Map using compareTo
	 * 
	 * @param Map<K, V> inputMap Generic sorting map ;)
	 * @return Map<K, V> sortedMap
	 */
	
	public static <K, V extends Comparable<V>> Map<K, V> sortMapByValue(Map<K, V> inputMap) {
		Log.writeLog("Sort.quickSort Method Called");

		/*
		 * Since all of the events are comparable they just need to be sorted By
		 * default, the MAP is stored by time created, as that is their key So now, we
		 * can change their order depending on the value Event instead
		 * 
		 * In the future this could be manipulated with a different option for
		 * displaying, perhaps length of description or something
		 * 
		 * Implement Selection sort or something similar
		 */

		Map<K, V> finalMap = inputMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> {
					throw new AssertionError();
				}, LinkedHashMap::new));
		
		/*
		 * Strange logic I found by assembling multiple StackExchange posts that lets me sort maps
		 * using their stream instead of their actual memory
		 * This is only virtual though, so we have to assemble everything by listening and collecting
		 * and then outputting that as its own map
		 * 
		 * Source: 
		 * https://www.baeldung.com/java-hashmap-sort
		 * https://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values
		 * https://mkyong.com/java/how-to-sort-a-map-in-java/
		 */
		
		return finalMap;
	
	}
}
