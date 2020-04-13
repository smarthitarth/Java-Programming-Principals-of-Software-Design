
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2){
        String s1 = q1.getInfo();
        String s2 = q2.getInfo();
        String lastWord1 = s1.substring(s1.lastIndexOf(" ")+1);
        String lastWord2 = s2.substring(s2.lastIndexOf(" ")+1);
        if(lastWord1.compareTo(lastWord2) == 0){
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return lastWord1.compareTo(lastWord2);
    }
}
