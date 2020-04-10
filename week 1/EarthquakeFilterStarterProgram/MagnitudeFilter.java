
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter{
    private double min;
    private double max;
    public MagnitudeFilter(double minimum, double maximum){
        min = minimum;
        max = maximum;
    }
     public String getName (){
        return "MagnitudeFilter";
    }
    public  boolean satisfies(QuakeEntry qe){
        return qe.getMagnitude() >= min && qe.getMagnitude() <= max; 
    }
}
