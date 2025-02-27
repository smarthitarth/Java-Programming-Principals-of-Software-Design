
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 
    
    public void testRunMarkov() {
        String st = "This is the best thing. He is the best, she is amaing. They love each other.  What is love? Love is sex.";
        MarkovWordOne markovWord = new MarkovWordOne(); 
        runModel(markovWord, st, 278);                 
        
    }
    
    public void runMarkovTwo() {
        //String st = "ghjk";
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        //String st = "This is the best thing. He is the best, she is amaing. They love each other.  What is love? Love is sex.";
        MarkovWordTwo markovWord = new MarkovWordTwo(); 
        runModel(markovWord, st, 120, 832);  
    }

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        //st = "This method should create a simple String instead of using a file for testing and call runModel.";
        MarkovWordOne markovWord = new MarkovWordOne(); 
        runModel(markovWord, st, 120, 139); 
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}
