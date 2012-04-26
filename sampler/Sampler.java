package sampler;

import java.util.Set;

/* Resource: http://www.antlr.org/wiki/display/CS345/Sampling+threads */

public class Sampler implements Runnable {
	
	@Override
	public void run() {
		Set<Thread> s = Thread.getAllStackTraces().keySet();
		Thread main = null;
		for (Thread a : s) {
			// find main thread (running main())
			if (a.getName().equals("main")) {
				main = a;
				break;
			}
		}

		while (main.isAlive()) {
			// get its stack trace
			StackTraceElement[] a = main.getStackTrace();

			for (StackTraceElement ste : a) {
				String className = ste.getClassName();

				// ignore anything in trace from java.* or sun.* or com.* or javax.*
				if (!className.startsWith("java.")
						&& !className.startsWith("sun.")
						&& !className.startsWith("com.")
						&& !className.startsWith("javax.")
						&& !className.startsWith("$")) {
					
					String name = className + "." + ste.getMethodName();
					
					// find the topmost trace element
					SamplerAgent.counts.add(name);
					break;
				}
			}

			try {
				Thread.sleep(1); // wake up about every 1ms
			} catch (Exception e) {
				System.out.println("This can never be printed.");
			}
		}
	}

}
