
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class Tester {
    public void testGetFollows(){
        MarkovOne m = new MarkovOne();
        m.setTraining("this is a test yes this is a test.");
        //for(int i = 0; i < 3; i++){
            System.out.println("===========================\n");
            ArrayList<String> fol = m.getFollows("t");
            System.out.println("Size: " + fol.size()+"\n");
            for(int k = 0; k < fol.size(); k++)
                System.out.print(" " + fol.get(k));
            System.out.println("\n===========================\n");
            
            ArrayList<String> fol1 = m.getFollows("es");
            System.out.println("Size: " + fol1.size()+"\n");
            for(int k = 0; k < fol1.size(); k++)
                System.out.print (" " + fol1.get(k));
            System.out.println("\n===========================\n");   
            
            ArrayList<String> fol2 = m.getFollows(".");
            System.out.println("Size: " + fol2.size()+"\n");
            for(int k = 0; k < fol2.size(); k++)
                System.out.print (" " + fol2.get(k));
        //}
    }
    
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        //markov.setRandom(43);
        markov.setTraining(st);
        ArrayList<String> fol2 = markov.getFollows("th");
        System.out.println("Size: " + fol2.size());
       // for(int k = 0; k < fol2.size(); k++)
           // System.out.print(" " + fol2.get(k));        
    }
}
