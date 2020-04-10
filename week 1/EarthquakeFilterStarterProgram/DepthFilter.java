
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
    private double minDep;
    private double maxDep;
    public DepthFilter(double min,double max){
        minDep = min;
        maxDep = max;
    }
     public String getName (){
        return "DepthFilter";
    }
    public boolean satisfies(QuakeEntry qe){
        return qe.getDepth() >= minDep && qe.getDepth() <= maxDep; 
    }
}
