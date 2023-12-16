package HOTELRES;
import java.util.Comparator;

public class CostComparator implements Comparator<Services>{
	@Override
	public int compare(Services s1, Services s2) {
	     double cost1 = s1.getCost();
	        double cost2 = s2.getCost();
	        if (cost1 > cost2) {
	            return -1;
	        } else if (cost1 < cost2) {
	            return 1;
	        } else {
	            return 0;
	        }
	}

}
