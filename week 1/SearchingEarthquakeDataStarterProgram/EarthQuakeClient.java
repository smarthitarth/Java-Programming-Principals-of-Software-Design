import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            if (qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }

        return answer;
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
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth,double maxDepth) {
        // ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (int k = 0; k < quakeData.size(); k++){
            QuakeEntry quake = quakeData.get(k);
            double depth = quake.getDepth();
            if (depth > minDepth && depth < maxDepth){
                answer.add(quake);
            }
        }
        return answer;
    }
    
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> qe = filterByDepth(list, -10000.0, -5000.0);
        for(QuakeEntry q : qe){
            System.out.println(q);
        }    
        System.out.println("Found "+ qe.size()+" quakes that meet the criteria.");
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> qe = filterByMagnitude(list, 5.0);
        for(QuakeEntry q : qe){
            System.out.println(q.getInfo());
            System.out.println(q);
          
        }
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        Location city =  new Location(38.17, -118.82);
        //System.out.println(list);
        //System.out.println(city);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000000, city);
        //System.out.println(close);
        for(QuakeEntry qe : close){
            System.out.println(qe);
        }
        System.out.println("Total quakes found: " + close.size());
        // This location is Bridgeport, CA
        
        // TODO
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String where, String phrase) {
        // ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (int k = 0; k < quakeData.size(); k++){
            QuakeEntry quake = quakeData.get(k);
            String info = quake.getInfo();
            if (where.equals("any") && info.contains(phrase)){
                answer.add(quake);
                System.out.println("Str: " + info);
            }else if (where.equals("start")){
               if(info.indexOf(phrase) == 0){
                   answer.add(quake);
                   System.out.println("Str: " + info);
                }
            }else{
                   if(info.contains(phrase)){
                       answer.add(quake);
                       System.out.println("Str: " + info);
                    }
                }
        }           
        return answer;
    }
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> qe = filterByPhrase(list, "any", "Creek");
        for(QuakeEntry q : qe){
            System.out.println(q);
        }    
        System.out.println("Found "+ qe.size()+" quakes that match criteria");
    }
    
}
