import edu.westminstercollege.cmpt328.memory.*;

public class Experiment1 
{
	
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
	            .accessTime(20)     // access time in cycles
	            .directMapping()    // use direct mapping
	            .build();           // build the cache as configured
	    
	    Cache cacheB = Cache.builder()
	            .name("Cache B")      // name of cache
	            .drawingFrom(ram)   // what memory it pulls from
	            .lineCount(256)       // how many lines (of 64 bytes)
	            .accessTime(25)     // access time in cycles
	            .setAssociative(2, ReplacementAlgorithm.LRU)
	            .build();           // build the cache as configured
		
	    MemorySystem systemA = new MemorySystem(cacheA); // provide the highest-level memory (closest to CPU)
	    MemorySystem systemB = new MemorySystem(cacheB); // provide the highest-level memory (closest to CPU)
	    
	    IntArrayValue intArray1 = systemB.allocateIntArray(6144);
	    IntArrayValue intArray2 = systemB.allocateIntArray(6144);
	    IntArrayValue intArray3 = systemB.allocateIntArray(6144);
	    
        IntValue i = systemB.allocateInt();
	    for (i.set(0); i.get() < intArray1.getLength(); i.increment())  // prints 10 20 30 40
	        intArray2.set(i, intArray1.get(i));
	    
	    
	    /*
	    systemB.resetMemories();

	    
	    for (i.set(0); i.get() < intArray1.getLength(); i.increment())  // prints 10 20 30 40
	        intArray3.set(i, intArray1.get(i));
	        
	    */
	    
        System.out.println();

        // How many CPU cycles did we use on our fictional computer with these memory accesses?
        System.out.printf("\n\nCycles used: %d\n", systemB.getTotalAccessTime());

        // Print out the cache again
        System.out.println("\nCache after these accesses:");
        cacheB.print();

        // The MemorySystem can also print out a summary of accesses, hit ratios, and so on
        System.out.println("\n");
        System.out.println("Memory system summary");
        systemB.printStatistics();
        
        System.out.println(intArray1.getAddress());
        System.out.println(intArray2.getAddress());
        System.out.println(intArray3.getAddress());
	}
	
}
