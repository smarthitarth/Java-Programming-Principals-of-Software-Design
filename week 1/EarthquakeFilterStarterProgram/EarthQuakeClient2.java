import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        /*Filter f = new MinMagFilter(4.0); 
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        for (QuakeEntry qe: m7) { 
            //System.out.println(qe);
        } */
        /*Filter f1 = new MagnitudeFilter(0.0, 2.0);
        ArrayList<QuakeEntry> mf = filter(list, f1);
        Filter f2 = new DepthFilter(-100000.0, -10000.0);
        ArrayList<QuakeEntry> df = filter(mf, f2);
        Filter f4 = new PhraseFilter("any", "a");
        ArrayList<QuakeEntry> pf = filter(df, f4);
        for (QuakeEntry qe: pf) { 
            System.out.println(qe);
        } 
        System.out.println(pf.size());*/
        
        Location city = new Location(35.42, 139.43);
        Filter f3 = new DistanceFilter(city, 10000000.0);
        ArrayList<QuakeEntry> df = filter(list, f3);
        Filter f4 = new PhraseFilter("end", "Japan");
        ArrayList<QuakeEntry> pf = filter(df, f4);
        for (QuakeEntry qe: pf) { 
            System.out.println(qe);
        } 
        System.out.println(pf.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 3.0));
        //maf.addFilter(new DepthFilter(-100000.0, -10000.0));
        Location l = new Location(36.1314, -95.9372);
        maf.addFilter(new DistanceFilter(l,10000000));
        maf.addFilter(new PhraseFilter("any","Ca"));
        ArrayList<QuakeEntry> qe = filter(list, maf);
        for(QuakeEntry q: qe){
            System.out.println(q);
        }
        System.out.println(qe.size());
        System.out.println(maf.getName());
    }
    
    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        //maf.addFilter(new MagnitudeFilter(0.0, 3.0));
        Location city = new Location(35.42, 139.43);
        maf.addFilter(new DistanceFilter(city, 10000000));
        maf.addFilter(new PhraseFilter("end","Japan"));
        ArrayList<QuakeEntry> qe = filter(list, maf);
        for(QuakeEntry q: qe){
            System.out.println(q);
        }
        System.out.println(qe.size());
    }

}
