import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

/**
 * author: Megan Pretorius
 * Date: 03/05/2020
 */
public class MyMiniSearchEngine {
  private Map<String, List<List<Integer>>> indexes;

    // disable default constructor
    private MyMiniSearchEngine() {
    }

    public MyMiniSearchEngine(List<String> documents) {
        index(documents);
    }

    /**
     * index 
     */
    private void index(List<String> texts) {
        //initializes scanner object to read each text file
        Scanner collect = null;
        
        //itterates over list of documents
        for(int i = 0; i < texts.size(); i++) {  
          //holds the integer values of each word in the document
          List<List<Integer>> documents = new ArrayList<List<Integer>>();
          
          try {
              //INITIALIZING VARIABLES
            //creates a new scanner object to read each text file
            collect = new Scanner(new File(texts.get(i)));
            List<Integer> docs = new ArrayList<>();
            //an array of words in the document
            ArrayList<String> words = new ArrayList<String>(); 
            List<Integer> wordsInDoc = new ArrayList<>();
             
              //PARSING EACH LINE IN DOCUMENT
            //itterates while there is input in the text file
            while (collect.hasNext() ) {  
              words.add(collect.next());
            }
            
              //FINDING THE VALUE OF EACH WORD IN DOCUMENT
            //itterates over the string of words in the line
            for (int j = 0; j < words.size(); j++) {
              //variable to store the total ascii value of each word 
              int wordVal = 0;
              
              //itterates over the charcters in each word 
              for (int k = 0; k < words.get(j).length(); k++) {
                //stores each character in the character variable 
                //by accessing the word in the words array and the 
                //individual character in the word
                char character = words.get(j).charAt(k);  
                //casts each character to an ascii value 
                int ascii = (int) character;
                wordVal += ascii;
              }
              //adds the value of word in the document to the wordInDoc list
              wordsInDoc.add(wordVal); 
              wordVal = 0; //resets value of wordVal
             }
            //adds the integer values of every word in the document
            documents.add(wordsInDoc);
        } 
        catch (FileNotFoundException e) {  //handles file errors 
            e.printStackTrace();
        }
        //adds each document and its list of word values to the map
        indexes.put(Integer.toString(i), documents);
      } 
    } 
    
    // search(key) return all the document ids where the given key phrase appears.
    // key phrase can have one or two words in English alphabetic characters.
    // return an empty list if search() finds no match in all documents.
    public List<Integer> search(String keyPhrase) {  
      //stores all the words to search for in the keyPhrase string in an array
      String[] keys = keyPhrase.split("");
      //an array of the int vals of keys
      int[] wordVals = new int [keys.length];
      ArrayList results = new ArrayList<>(); //an arraylist of documents containg keys
      
       
      //itterates over each key in the keys array
      for (int i = 0; i < keys.length; i++) { 
        int wordVal = 0; //variable to save the int val of each key
        //itterates over the charcters in the key 
        for (int j = 0; j < keys[i].length(); j++) {
          //stores each character in the character variable 
          //by accessing the word in the keys array and the 
          //individual character in the word
          char character = keys[i].charAt(j);  
          //casts each character to an ascii value 
          int ascii = (int) character;
          wordVal += ascii;
        }
        wordVals[i] = wordVal;  //adds the new words int val to an array
      }
      
      for (int k = 0; k < indexes.size(); k++) {
        for (List<Integer> vals : indexes.get(Integer.toString(k)) ) {
          if (vals.contains(wordVals.get(0))) {
            results.add(k);
          }
        } 
      }
      return new ArrayList<>(); // place holder
    }
}