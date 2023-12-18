
public interface KeyFinder {
	
	//interface method getKey() returns int.
	//returns key ((banner id for students and faculty, crn for
	//courses)
	int getKey();
	
	//interface method sameKey() returns boolean
	// returns true if the object has k as its key
	boolean sameKey(int k);

	
	
}
