import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HashTest {

	/**
	 * @author Daniel Ingram
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashDirectory hd = new HashDirectory();
		File data = new File("src/data.txt");

		try {
			// Reader objects created to allow the text file to be read
			FileReader read = new FileReader(data);
			BufferedReader in = new BufferedReader(read);

			// String variables required for the read process.
			String temp = in.readLine();
			String[] split;

			/*
			 * This Do-While loop takes the contents of the text-file stream and
			 * splits it down into the three parts of the object: Surname,
			 * Initial and Extension.
			 * 
			 * From that point the insert method is called to create an object
			 * using that data.
			 * 
			 * Loop exits when there is no longer any more text in the stream.
			 */
			do {
				split = temp.split("\t");

				hd.insert(split[0], split[1], split[2]);

			} while ((temp = in.readLine()) != null);

			// Closes the FileReader stream.
			/* DO NOT REMOVE! */
			read.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (IOException e) {
			System.out.println(e);
		}

		hd.print();

		hd.delete("Ingram");

		hd.print();

		hd.deleteExt("0806");

		hd.edit("Carmody");
		
		hd.print(hd.search("Norris"));

		long before = System.currentTimeMillis();
		for (int x = 0; x < 10000; x++) {
			hd.insert("Hogarth", "S", "1408");
		}
		long after = System.currentTimeMillis();
		long timeTaken = after - before;
		System.out.println("Time taken to perform 10,000 inserts: " + timeTaken);

		before = System.currentTimeMillis();
		for (int x = 0; x < 10000; x++) {
			hd.search("Waterfield");
		}
		after = System.currentTimeMillis();
		timeTaken = after - before;

		System.out.println("Time taken to perform 10,000 searches: " + timeTaken);
	}

}
