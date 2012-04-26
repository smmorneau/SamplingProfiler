package sampler;

import java.lang.instrument.Instrumentation;

public class SamplerAgent {
	
	// jar -cvfm <destination/jarname> <path/manifestfilename> *.class

	static FrequencySet<String> counts = new FrequencySet<String>();
	
	/**
	 * JVM hook to statically load the javaagent at startup.
	 * 
	 * After the Java Virtual Machine (JVM) has initialized, the premain method
	 * will be called. Then the real application main method will be called.
	 * 
	 * @param args
	 * @param inst
	 * @throws Exception
	 */
	public static void premain(String args, Instrumentation i)
			throws Exception {
		
		Sampler s = new Sampler();
		Thread t = new Thread(s);
		t.start();
		Thread shutdown = new Thread(new Shutdown());
		Runtime.getRuntime().addShutdownHook(shutdown);
	}

}