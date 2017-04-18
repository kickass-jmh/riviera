package fr.rivieradev.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import sun.jvm.hotspot.runtime.Thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * TBD
 */
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.SampleTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(1)
public class MapConcurrency {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MapConcurrency.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    @Threads(1)
    public void hashtable(BenchmarkState bench, ThreadState thread) {
        for (String s : thread.ids) {
            bench.hashtable.put(s, s);
        }
    }

    @Benchmark
    @Threads(1)
    public void concurrentMap(BenchmarkState bench, ThreadState thread) {
        for (String s : thread.ids) {
            bench.map.put(s, s);
        }
    }

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        Hashtable<String, String> hashtable = new Hashtable<>();
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    }

    @State(Scope.Thread)
    public static class ThreadState {
        private static final int COUNT = 10;
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
}
