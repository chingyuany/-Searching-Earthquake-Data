
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class LargestQuakes {
public void findLargestQuakes()
{
  EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> quakeData  = parser.read(source);
        
    System.out.println("# quakes read: " + quakeData.size());
    /*
     for (QuakeEntry qe : quakeData) {
            System.out.println(qe);
        }
     */
    int largest = indexOfLargest(quakeData);
    System.out.println("max magnitude index = " +largest+" magnitude ="+quakeData.get(largest).getMagnitude());
    ArrayList<QuakeEntry> answer = getLargest(quakeData,50);
     
     for (QuakeEntry qe : answer) {
            System.out.println(qe);
        }
    
   System.out.println("# quakes read: " + answer.size());
}
public int indexOfLargest(ArrayList<QuakeEntry> Data )
{
    int index =0;
  for(int k=1; k < Data.size(); k++){
                QuakeEntry quake = Data.get(k);
                double mag = quake.getMagnitude();
                if ( mag > Data.get(index).getMagnitude()){
                    index = k;   
                }
            }
return index;
}
public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData , int howmany)
{
    ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
     ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
    for(int j=0; j < howmany; j++) {
        int max = indexOfLargest(copy);
          answer.add(copy.get(max));
            copy.remove(max);
    }
    return answer;
   
        
}
}