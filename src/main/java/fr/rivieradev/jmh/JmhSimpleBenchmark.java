package fr.rivieradev.jmh;

import java.math.BigInteger;
import java.util.Random;
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
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@Warmup(iterations=5, time=1, timeUnit=TimeUnit.SECONDS)
@Measurement(iterations=5, time=1, timeUnit=TimeUnit.SECONDS)
public class JmhSimpleBenchmark {

  String a;

  @Setup
  public void prepare() {
    a = new BigInteger(8000, new Random()).toString(32);
  }
  
  @Benchmark
  public int search() {
    return a.indexOf("lg");
  }  
  
  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
    		.include(JmhSimpleBenchmark.class.getSimpleName())
    		.build();
    new Runner(opt).run();
  }

}
