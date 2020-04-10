
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location from;
    private double maxDis;
    public DistanceFilter(Location l, double dis){
        from = l;
        maxDis = dis;
    }
     public String getName (){
        return "DistanceFilter";
    }
    public boolean satisfies(QuakeEntry qe){
        return qe.getLocation().distanceTo(from) < maxDis;
    }
}
