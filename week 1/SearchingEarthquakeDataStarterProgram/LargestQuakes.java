
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class LargestQuakes {
    void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("Printing for " + list.size() + " earth-quakes: ");
        /*for(QuakeEntry qe : list){
            System.out.println(qe);
        }*/
        //int i = indexOfLargest(list);
        //System.out.println("Largest magnitude loc: " + i + "\nEarth-quake: " + list.get(i));
        
        ArrayList<QuakeEntry> largestList = getLargest(list, 5);
        for (QuakeEntry qe : largestList){
            System.out.println(qe);
        }
    }
    
    int indexOfLargest(ArrayList<QuakeEntry> data){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        double magMin = 0;
        int i = 0;
        int k = 0;
        for(QuakeEntry qe : data){
            if (qe.getMagnitude() > magMin){
                magMin = qe.getMagnitude();
                k = i;
            }
            //System.out.println("i, k: " + i+" " + k);
            i++;            
        }
        return k;
    }
    ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
       ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry> (quakeData);
       ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry> ();
       for (int i = 0; i < howMany; i++){
           int m = indexOfLargest(copy);
           ret.add(copy.get(m));
           copy.remove(m);
       }
       return ret;
    }
}
