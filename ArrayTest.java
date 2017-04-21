import java.io.*;


public class ArrayTest {

	/**
	 * @author Daniel Ingram
	 */
	public static void main(String[] args) {
		ArrayDirectory ad = new ArrayDirectory();
		File data = new File("src/data.txt");

		try {
			// Reader objects created to allow the text file to be read
			FileReader read = new FileReader(data);
			BufferedReader in = new BufferedReader(read);

			// String variables required for the read process.
			String temp = in.readLine();
			String[] split;

			/*
			 This Do-While loop takes the contents of the text-file stream and
			 splits it down into the three parts of the object: Surname,
			 Initial and Extension.
			 
			 From that point the insert method is called to create an object
			 using that data.
			 
			 Loop exits when there is no longer any more text in the stream.
			 */
			do {
				split = temp.split("\t");

				ad.insert(split[0], split[1], split[2]);

			} while ((temp = in.readLine()) != null);

			// Closes the FileReader stream.
			// DO NOT REMOVE!
			read.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (IOException e) {
			System.out.println(e);
		}

		ad.insert("Reznor", "T", "0919");

		ad.print();

		// Search algorithm returns a negative value if there is no entry in the
		// array.
		// This is to allow for the search method to be used within other
		// methods.
		int result = ad.search("Ingram");

		if (result < 0) {
			System.out.println("There are no entries.");
		} else {
			ad.print(result);
		}

		ad.delete("Ingram");
		
		ad.print();
		
		ad.edit("Norris");
		
		System.out.print("\n");
		
		ad.print();
		
		long before = System.currentTimeMillis();
		for(int x = 0; x < 10000 ; x++){
			ad.search("Waterfield");
		}
		long after = System.currentTimeMillis();
		long timeTaken = after - before;
		System.out.println("Time taken to perform 10,000 searches: " + timeTaken);

		before = System.currentTimeMillis();
		for(int x = 0; x < 10000; x++){
			ad.insert("Hogarth", "S", "1408");
		}
		after = System.currentTimeMillis();
		timeTaken = after - before;		
		System.out.print("Time taken to perform 10,000 inserts: " + timeTaken);
		
	}

}
