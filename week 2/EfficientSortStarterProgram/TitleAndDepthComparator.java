
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String s1 = qe1.getInfo();
        String s2 = qe2.getInfo();
        if(s1.compareTo(s2) == 0){
            return Double.compare(qe1.getDepth(), qe2.getDepth());
        }else{
            return s1.compareTo(s2);
        }
    }
}
