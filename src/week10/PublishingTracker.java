package week10;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap; //this is thread-safe!
import java.util.concurrent.ConcurrentMap; //this is thread-safe!

//is this class thread-safe?
public class PublishingTracker {
	private final ConcurrentMap<String, Point> locations;
	
	public PublishingTracker(Map<String, Point> locations) {
		this.locations = new ConcurrentHashMap<String, Point>(locations);
	}
	
	public synchronized Map<String, Point> getLocations () {
		return Collections.unmodifiableMap(new HashMap<String, Point>(locations));
	}
	
	public synchronized Point getLocation (String id) {
		return locations.get(id);
	}
	
	public synchronized void setLocation (String id, int x, int y) {		
		if (locations.replace(id, new Point(x, y)) == null) {
			throw new IllegalArgumentException ("No such ID: " + id);			
		}
	}
	
	//is this class not thread-safe?
	class Point {
		public final int x, y;
		
		public Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
