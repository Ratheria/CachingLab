import java.util.Random;

import edu.westminstercollege.cmpt328.memory.*;

public class Experiment2 
{
   
    private static void benchmark(MemorySystem sys) 
    {
        Random random = new Random();
        IntArrayValue data = sys.allocateIntArray(100000);
        IntValue i = sys.allocateInt();
        IntValue tmp = sys.allocateInt();
        IntValue a = sys.allocateInt();
        IntValue b = sys.allocateInt();

        for (i.set(0); i.get() < 1000000; i.increment()) 
        {
            a.set(random.nextInt(data.getLength()));
            b.set(random.nextInt(data.getLength()));

            tmp.set(data.get(a));
            data.set(a, data.get(b));
            data.set(b, tmp);
        }
        
        System.out.printf("\nTotal access time: %,d\n", sys.getTotalAccessTime());
        sys.printStatistics();
    }
    
    public static void main(String [] args)
    {
        MainMemory ram = new MainMemory(
                "RAM",      // name of memory
                200     // access time in cycles
        );
    	
        Cache cacheA = Cache.builder()
                .name("Cache A")      // name of cache
                .drawingFrom(ram)   // what memory it pulls from
                .lineCount(256)       // how many lines (of 64 bytes)
                .accessTime(25)     // access time in cycles
                .setAssociative(2, ReplacementAlgorithm.LRU)
                .build();           // build the cache as configured
        
        Cache cacheB = Cache.builder()
                .name("Cache B")      // name of cache
                .drawingFrom(ram)   // what memory it pulls from
                .lineCount(256)       // how many lines (of 64 bytes)
                .accessTime(25)     // access time in cycles
                .setAssociative(8, ReplacementAlgorithm.LRU)
                .build();           // build the cache as configured
        
        Cache cacheC = Cache.builder()
                .name("Cache C")      // name of cache
                .drawingFrom(ram)   // what memory it pulls from
                .lineCount(256)       // how many lines (of 64 bytes)
                .accessTime(25)     // access time in cycles
                .fullyAssociative(ReplacementAlgorithm.LRU)
                .build();           // build the cache as configured
        
        MemorySystem systemA = new MemorySystem(cacheA); 
        MemorySystem systemB = new MemorySystem(cacheB); 
        MemorySystem systemC = new MemorySystem(cacheC); 
        
    	benchmark(systemA);
    	benchmark(systemB);
    	benchmark(systemC);
    }
}
