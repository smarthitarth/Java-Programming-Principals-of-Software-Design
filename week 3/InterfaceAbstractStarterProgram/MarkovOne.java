
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MarkovOne extends AbstractMarkovModel{
    //private String myText; 
    //private Random myRandom;
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
   /* public ArrayList<String> getFollows(String key){
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
    }*/
    
    public String getRandomText(int numChars){
       
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index, index+1);
        sb.append(key);
        for(int k=0; k < numChars-1; k++){
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
        return "Markov Model of order 1" ;
    }
}
