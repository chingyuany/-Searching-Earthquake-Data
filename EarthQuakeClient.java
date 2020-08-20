import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
          for(int k=0; k < quakeData.size(); k++){
                QuakeEntry quake = quakeData.get(k);
                double Mag = quake.getMagnitude();
                if (Mag >  magMin)
                   {
                   answer.add(quake);  
                }
            }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
     
        for(int k=0 ; k < quakeData.size(); k++){
                QuakeEntry quake = quakeData.get(k);
                Location loc = quake.getLocation();

                if (loc.distanceTo(from) /1000 < distMax){
                    answer.add(quake);  
                }
            }

        return answer;
    }
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,double minDepth, double maxDepth)
    {

ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
for(int k=0 ; k < quakeData.size(); k++){
                QuakeEntry quake = quakeData.get(k);
                double depth = quake.getDepth();

                if (depth >minDepth && depth < maxDepth){
                    answer.add(quake);  
                }
            }

     return answer;
    }
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,String where,String phrase)
{

ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
for(int k=0 ; k < quakeData.size(); k++){
                QuakeEntry quake = quakeData.get(k);
                String title = quake.getInfo();

                if (where =="start" ){
                    if(title.startsWith(phrase))
                    answer.add(quake);  
                }
                else if (where == "end")
                {
                    if(title.endsWith(phrase))
                    answer.add(quake); 
                }
                else if (where == "any")
                {if(title.indexOf(phrase) != -1)
                    answer.add(quake); 
                }
            }

return answer;

}
    public void quakesByPhrase()
    {
     EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> titlelist = filterByPhrase(list,"any","Can");
      for (QuakeEntry qe : titlelist) {
            System.out.println(qe);
        }
        System.out.println("Found "+titlelist.size()+" quakes that match that criteria");

    }
    public void quakesOfDepth()
    {
         EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
         ArrayList<QuakeEntry> depthlist = filterByDepth(list,-4000.0, -2000.0);
      for (QuakeEntry qe : depthlist) {
            System.out.println(qe);
        }
        System.out.println("Found "+depthlist.size()+" quakes that match that criteria");

    
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
         ArrayList<QuakeEntry> maglist = filterByMagnitude(list,5.0);
      for (QuakeEntry qe : maglist) {
            System.out.println(qe);
        }
        System.out.println("Found "+maglist.size()+" quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> closelist =filterByDistanceFrom(list,1000.0,city);
        for (QuakeEntry qe : closelist) {
            System.out.println(qe.getLocation().distanceTo(city)/1000+"\t"+qe.getInfo());
        }
        System.out.println("Found "+closelist.size()+" quakes that match that criteria");
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
    
}
