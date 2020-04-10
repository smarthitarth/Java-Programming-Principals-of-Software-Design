
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current,
    int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        QuakeEntry q = copy.get(0);
        Location loc = q.getLocation();
        int m = 0;
        double shortestDist = loc.distanceTo(current);
        for (int i = 0; i < howMany; i++){
            for (int k = 1; k < copy.size(); k++){
                QuakeEntry quake = copy.get(k);
                Location loca = quake.getLocation();
                double dis = loca.distanceTo(current);
                if ( dis < shortestDist){
                    shortestDist = dis;
                    m = k;
                }
            }
            ret.add(copy.get(m));
            copy.remove(m);
        }
        return ret;
    }
    
    
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        // ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (int k = 0; k < quakeData.size(); k++){
            QuakeEntry quake = quakeData.get(k);
            Location loc = quake.getLocation();
            //System.out.println(loc.distanceTo(from));
            if (loc.distanceTo(from) < distMax){
                answer.add(quake);
            }
        }
        return answer;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,10);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
