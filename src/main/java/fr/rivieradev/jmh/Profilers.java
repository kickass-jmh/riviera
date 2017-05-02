package fr.rivieradev.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
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

import profilers.FlightRecordingProfiler;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value=1, jvmArgs = { "-XX:+UnlockCommercialFeatures" })
public class Profilers {

	@Benchmark
	public void measure() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(100);
	}

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(Profilers.class.getSimpleName())
				.addProfiler(FlightRecordingProfiler.class).build();
		new Runner(opt).run();
	}
}
