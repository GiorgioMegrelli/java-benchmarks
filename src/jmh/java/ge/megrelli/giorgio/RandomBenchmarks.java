package ge.megrelli.giorgio;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class RandomBenchmarks {
    @State(Scope.Thread)
    public static abstract class BenchmarksContext<T extends Random> {
        protected static final int BYTES_ARRAY_LEN = 1_000_000;

        public T random;
        public byte[] array;

        @Setup(Level.Trial)
        public void setup() {
            random = createInstance();
            array = new byte[BYTES_ARRAY_LEN];
            random.nextBytes(array);
        }

        protected abstract T createInstance();
    }

    public static class RandomBenchmarksContext extends BenchmarksContext<Random> {
        private static final Random RANDOM = new Random();

        @Override
        protected Random createInstance() {
            return RANDOM;
        }
    }

    public static class SecureRandomBenchmarksContext extends BenchmarksContext<SecureRandom> {
        private static final SecureRandom SECURE_RANDOM = new SecureRandom();

        @Override
        protected SecureRandom createInstance() {
            return SECURE_RANDOM;
        }
    }

    @Benchmark
    public void random_nextBytes(RandomBenchmarksContext context) {
        context.random.nextBytes(context.array);
    }

    @Benchmark
    public void secureRandom_nextBytes(SecureRandomBenchmarksContext context) {
        context.random.nextBytes(context.array);
    }
}
