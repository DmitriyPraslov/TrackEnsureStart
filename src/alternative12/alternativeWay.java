package alternative12;

import java.util.*;

public class alternativeWay {
    public static void main(String[] args) {
        String s = "foo bar baz bazz";
        getMap(s);
    }

    public static Map<String, Map<Integer,List<String>>> getMap (String s){
        Map<String, Map<Integer,List<String>>> result = new TreeMap<>();
        String[] sArr = s.split("\\W");
        Set<String> uniqueFirstLetter = getUniqueFirstLetter(sArr);
        for (String temp : uniqueFirstLetter){
            result.put(temp.substring(0,1),findTheSameWord(temp.substring(0,1), sArr));
        }
        System.out.println(result);
        return result;
    }

    public static Set<String> getUniqueFirstLetter(String[] s){
        Set<String> result = new TreeSet<>();
        for (int i=0; i<s.length; i++){
            result.add(s[i].substring(0,1));
        }
        return result;
    }

    public static Map<Integer,List<String>> findTheSameWord (String firstLetter, String[] wordArray){
        Map<Integer,List<String>> result = new TreeMap<>();
        List<String> wordList;
        int minWordLength = findMinWordLength(firstLetter, wordArray);
        int maxWordLength = findMaxWordLength(firstLetter, wordArray);

        for (int i = minWordLength; i <= maxWordLength; i++) {
            wordList = new ArrayList<>();
            for (int j=0; j<wordArray.length; j++) {
                if (wordArray[j].substring(0, 1).equals(firstLetter) && wordArray[j].length() == i) {
                    wordList.add(wordArray[j]);
                }
            }
            if (wordList.size()>0) {
                result.put(i, wordList);
            }
        }
        return result;
    }

    public static int findMinWordLength (String firstLetter, String[] arr){
        int result = findMaxWordLength(firstLetter,arr);
        for (int i=0; i<arr.length; i++){
            if (arr[i].length()<result && firstLetter.equals(arr[i].substring(0,1))){
                result = arr[i].length();
            }
        }
        return result;
    }

    public static int findMaxWordLength (String firstLetter, String[] arr){
        int result = 0;
        for (int i=0; i<arr.length; i++){
            if (arr[i].length()>result && firstLetter.equals(arr[i].substring(0,1))){
                result = arr[i].length();
            }
        }
        return result;
    }
}
