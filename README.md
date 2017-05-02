# riviera

## Report explained

```
# JMH 1.18 (released 34 days ago)
# VM version: JDK 1.8.0_102, VM 25.102-b14
# VM invoker: D:\Java\jdk1.8.0_102\jre\bin\java.exe
# VM options: -Dfile.encoding=UTF-8
# Warmup: 5 iterations, 1 s each
# Measurement: 5 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op
# Benchmark: fr.rivieradev.jmh.JmhSimpleBenchmark.search

# Run progress: 66.67% complete, ETA 00:00:10
# Fork: 1 of 1
# Warmup Iteration   1: 65.497 ns/op
# Warmup Iteration   2: 66.729 ns/op
# Warmup Iteration   3: 66.624 ns/op
# Warmup Iteration   4: 67.983 ns/op
# Warmup Iteration   5: 65.981 ns/op
Iteration   1: 66.315 ns/op
Iteration   2: 66.091 ns/op
Iteration   3: 67.184 ns/op
Iteration   4: 66.837 ns/op
Iteration   5: 66.261 ns/op


Result "fr.rivieradev.jmh.JmhSimpleBenchmark.search":
  66.538 ±(99.9%) 1.757 ns/op [Average]
  (min, avg, max) = (66.091, 66.538, 67.184), stdev = 0.456
  CI (99.9%): [64.781, 68.294] (assumes normal distribution)


# Run complete. Total time: 00:00:31

Benchmark                           Mode  Cnt   Score   Error  Units
JmhSimpleBenchmark.search           avgt    5  66.538 ± 1.757  ns/op
```