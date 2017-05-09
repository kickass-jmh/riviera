package fr.rivieradev.jmh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
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
//@Fork(value = 1, jvmArgs= {"-XX:+UnlockCommercialFeatures"})
@Fork(1)
public class MapConcurrency {

	private static final int COUNT = 10;

	@Benchmark
	@Threads(8)
	public void hashtable(Blackhole bh, BenchmarkState bench, ThreadState thread) {
		for (String s : thread.ids) {
			bh.consume(bench.hashtable.get(s));
		}
	}

	@Benchmark
	@Threads(8)
	public void concurrentMap(Blackhole bh, BenchmarkState bench, ThreadState thread) {
		for (String s : thread.ids) {
			bh.consume(bench.map.get(s));
		}
	}

	@State(Scope.Benchmark)
	public static class BenchmarkState {

		Hashtable<String, String> hashtable = new Hashtable<>();
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

		@Setup
		public void setup() {
			for (int i = 0; i < COUNT; i++) {
				String e = "ID" + i;
				hashtable.put(e, e);
				map.put(e, e);
			}
		}
	}

	@State(Scope.Thread)
	public static class ThreadState {
		private List<String> ids;

		@Setup
		public void setup() {
			ids = new ArrayList<>();
			for (int i = 0; i < COUNT; i++) {
				String e = "ID" + i;
				ids.add(e);
			}
			Collections.shuffle(ids);
		}
	}

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(MapConcurrency.class.getSimpleName())
				//.addProfiler(StackProfiler.class)
				.build();
		new Runner(opt).run();
	}
}
