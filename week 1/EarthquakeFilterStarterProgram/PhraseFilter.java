
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    public PhraseFilter(String w, String p){
        where = w;
        phrase = p;
    }
     public String getName (){
        return "PhraseFilter";
    }
    public boolean satisfies(QuakeEntry quake){
        String info = quake.getInfo();
            if (where.equals("any") && info.contains(phrase)){
                //answer.add(quake);
                //System.out.println("Str: " + info);
                return true;
            }else if (where.equals("start")){
               if(info.indexOf(phrase) == 0){
                   //answer.add(quake);
                   //System.out.println("Str: " + info);
                   return true;
                }
            }else{
                   if(info.contains(phrase)){
                      // answer.add(quake);
                      // System.out.println("Str: " + info);
                      return true;
                    }
            } 
        return false;
    }
}
