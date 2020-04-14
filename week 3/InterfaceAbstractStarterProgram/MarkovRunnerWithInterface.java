
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size) {
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 29;
        MarkovZero mz = new MarkovZero();
        mz.setRandom(seed);
        runModel(mz, st, size);
    
        MarkovOne mOne = new MarkovOne();
        mOne.setRandom(seed);
        runModel(mOne, st, size);
        
        MarkovModel mThree = new MarkovModel(3);
        mThree.setRandom(seed);
        runModel(mThree, st, size);
        
        MarkovFour mFour = new MarkovFour();
        mFour.setRandom(seed);
        runModel(mFour, st, size);

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
