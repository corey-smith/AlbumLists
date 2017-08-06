package albums.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class URLUtils {
    /**
     * Method to take in a map of Key/Value pairs of Strings to replace in another String (being a URL)
     * This exists because the standard java String.format method relies on ordering, and I don't want to do that
     * @param baseStr - string with "{key}" values to be replaced with actual values from HashMap
     * @param values - values HashMap. This should have keys that match each {key} in the baseStr value
     * @return returnStr - populated base String
     */
    public static String populate(String baseStr, HashMap<String, String> values) {
        String returnStr = baseStr;
        Iterator<Entry<String, String>> valueIter = values.entrySet().iterator();
        while (valueIter.hasNext()) {
            Entry<String, String> curEntry = valueIter.next();
            String replaceStr = "{" + curEntry.getKey() + "}";
            String replaceValue = curEntry.getValue();
            returnStr = returnStr.replace(replaceStr, replaceValue);
        }
        return returnStr;
    }
}
