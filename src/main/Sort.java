// Class for sorting the map of events 
// Takes an input selector from the user, perhaps just use generic 
// Then sorts the list with that, maybe just use TreeMap 

package main;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Sort {
	public static <K, V extends Comparable<V>> Map<K, V> sortMapByValue(Map<K, V> inputMap) {

		System.out.println("Sort.quickSort Method Called");

		/*
		 * Since all of the events are comparable they just need to be sorted By
		 * default, the MAP is stored by time created, as that is their key So now, we
		 * can change their order depending on the value Event instead
		 * 
		 * In the future this could be manipulated with a different option for
		 * displaying, perhaps length of description or something
		 * 
		 * @param Map<Integer, Event> normally, it could work with others though
		 * 
		 * Implement Selection sort or something similar
		 */

		Map<K, V> finalMap = inputMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> {
					throw new AssertionError();
				}, LinkedHashMap::new));

		return finalMap;
	
	}
}
