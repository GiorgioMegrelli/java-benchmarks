# java-benchmarks

Java Benchmarks

## Benchmarks

### RandomBenchmarks

Filling an array with 1 000 000 elements (bytes).

#### `random_nextBytes`

```
Benchmark                                Mode  Cnt   Score   Error  Units
RandomBenchmarks.random_nextBytes        avgt   20  26.050 ▒ 3.393  ms/op
```

#### `secureRandom_nextBytes`

```
Benchmark                                Mode  Cnt   Score   Error  Units
RandomBenchmarks.secureRandom_nextBytes  avgt   20   5.899 ▒ 2.876  ms/op
```
