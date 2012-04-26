package sampler;

public class Frequency implements Comparable {

	String key;
	MutableInt value;
	
	public Frequency(String key, MutableInt value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public int compareTo(Object o) {
		
		Frequency f = (Frequency) o;
		
		return this.value.compareTo(f.value);
		
	}
	
	public String toString() { 
		return String.format("%s: %s", key, value);
	}

}
