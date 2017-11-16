package fr.rivieradev.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * <p>
 * In this benchmark we compare the throughput of a get operation on a
 * ConcurrentHashMap and a Hashtable. <br>
 * The ConcurrentHashMap retrievals reflect the results of the most recently
 * completed update operations holding upon their onset. ConcurrentHashMap get
 * operation is lock-free. Conversely, Hashtable's get operation uses intrinsic
 * lock (synchronized).
 * </p>
 * 
 * <p>
 * The main goal of this benchmark is to show how one can run a multi-threaded
 * benchmark and explicitly define state scope, i.e. per thread, per benchmark.k
 * </p>
 */
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(1)
public class MapConcurrency {
	
	

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(MapConcurrency.class.getSimpleName())
				.build();
		new Runner(opt).run();
	}
}
