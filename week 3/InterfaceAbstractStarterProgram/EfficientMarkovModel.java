
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class EfficientMarkovModel extends AbstractMarkovModel {
    //private String myText; 
    //private Random myRandom;
    private int N;
    private HashMap<String, Integer> hm; 
    public EfficientMarkovModel(int n) {
        myRandom = new Random();
        HashMap<Integer, String> hm = new HashMap<Integer, String>(); 
        N = n;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
     protected ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        //int index = 0;
        while(pos < myText.length()){
            int index = myText.indexOf(key, pos);
            if(index == -1){
                break;   
            }
            if(index + key.length() >= myText.length()-1) {break;}
            follows.add(myText.substring(index+key.length(), index+key.length()+1));
          //  System.out.println("Position: " + pos + " index: " + index + " Follows size: " + follows.size());
            pos = index+key.length();
        }
      //  for(int i = 0; i < follows.size(); i++)
      //     {System.out.println(follows.get(i));}
        return follows;
    }
    
    public void buildMap(){
    
    }
    
    public String getRandomText(int numChars){
        
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index, index+N);
        sb.append(key);
        for(int k=0; k < numChars-N; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    public String toString(){
        return "Markov Model of order" + N;
    }
}

