import edu.westminstercollege.cmpt328.memory.*;

public class Experiment4 
{
	private static Image image;
	private static String location = "C:\\Users\\Ratheria\\Desktop\\final-api-code.png";
	private static String location2 = "C:\\Users\\Ratheria\\Desktop\\final-api-code2.png";
	
	public static void main(String[] args)
	{
		MemorySystem memory = new MemorySystem.CoreI7();
		MemorySystem.setDefault(memory);
		try
		{
			image = Image.load(location);
			for (int y = 0; y < image.getHeight(); ++y) {
	            for (int x = 0; x < image.getWidth(); ++x) {
	            	Pixel pixel = image.getPixelAt(x, y);
	            	int average = (pixel.getRed() + pixel.getBlue() + pixel.getGreen())/3;
	                pixel.setRed(average);
	                pixel.setBlue(average);
	                pixel.setGreen(average);
	            }
	        }
			image.save(location2);
		}
		catch(Exception e)
		{
			e.printStackTrace(System.out);
		}
        System.out.println();

        // How many CPU cycles did we use on our fictional computer with these memory accesses?
        System.out.printf("\n\nCycles used: %d\n", memory.getTotalAccessTime());
	}
}
