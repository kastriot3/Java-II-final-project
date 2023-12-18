import java.util.Comparator;

public class Compare implements Comparator<KeyFinder>{

	@Override
	public int compare(KeyFinder o1, KeyFinder o2) {
		return o1.getKey()-o2.getKey();
	}
	

}
