package sampler;

import java.util.Set;
import java.util.TreeSet;

public class Shutdown implements Runnable {

	@Override
	public void run() {

		Set<Frequency> set = new TreeSet<Frequency>();

		long total = 0;
		
		for (String method : SamplerAgent.counts.keySet()) {
			MutableInt count = SamplerAgent.counts.get(method);
			total += count.longValue();
			
			Frequency f = new Frequency(method,
					count);
			set.add(f);
		}
		
		System.out.println("***** Sampler Profile: *****");
		
		for (Frequency f : set) {
			System.out.println(f + " " + "(" + (int) ((f.value.doubleValue() / total) * 100) + "%)");
		}

	}

}