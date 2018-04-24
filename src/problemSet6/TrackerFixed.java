package problemSet6;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Test extends Thread {
	private TrackerFixed tracker;
	
	public Test (TrackerFixed tra) {
		this.tracker = tra;
	}
	
	public void run () {
		TrackerFixed.MutablePoint loc = tracker.getLocation("somestring");
		loc.x = -1212000;
	}
}

//is this class thread-safe?
public class TrackerFixed {
	//@guarded by this
	private final Map<String, MutablePoint> locations;
	
	//the reference locations, is it going to be an escape?
	public TrackerFixed(Map<String, MutablePoint> locations) {
		this.locations = locations;
	}
	
	//is this an escape? yes is an escape. 
	// The locations reference cannot be changed but the items inside can be changed.
	// we can go around this by making the return unmodifiable
	public synchronized Map<String, MutablePoint> getLocations () {
		return Collections.unmodifiableMap(locations);
	}
	
	//is this an escape? yes it is an escape. The location should be modifiable because the taxi moves.
	public synchronized MutablePoint getLocation (String id) {
		MutablePoint loc = locations.get(id);
		return loc;
	}
	
	// this method has to be synchronized to allow one thread to modify it.
	public synchronized void setLocation (String id, int x, int y) {
		MutablePoint loc = locations.get(id);
		
		if (loc == null) {
			throw new IllegalArgumentException ("No such ID: " + id);			
		}
		
		loc.x = x;
		loc.y = y;
	}
	
	//this class is not thread-safe (why?) and keep it unmodified.
	class MutablePoint {
		public int x, y;

		public MutablePoint (int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public MutablePoint (MutablePoint p) {
			this.x = p.x;
			this.y = p.y;
		}
	}
}
