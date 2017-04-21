import java.util.Scanner;

public class ArrayDirectory implements Directory {

	/**
	 * @author Daniel Ingram
	 * 
	 */
	
	private final int ARRAY_SIZE = 1000;
	private int noOfEntries = 0;
	private Entry[] ent = new Entry[ARRAY_SIZE];
	private Scanner input = new Scanner(System.in);

	public void insert(String name, String initial, String ext) {
		//	This method adds entries to the array.

		if(noOfEntries == ent.length){
			Entry[] temp = new Entry[ent.length*2];
			
			for(int x = 0; x < ent.length; x++){
				temp[x] = ent[x];
			}
			
			ent = temp;
		}
		
		// Default location will be the highest entry
		int location = noOfEntries;

		// While the location variable is not zero
		while (location != 0) {

			// Compare the new entry's name to the one at the current location.
			// If NAME is lower than the current entry SURNAME then test the
			// next location.
			if (name.compareTo(ent[location - 1].getSurname()) < 0) {

				ent[location] = ent[location - 1];

				location--;
			}
			// If the surnames are the same then they are listed alphabetically
			// by initial.
			else if (name.compareTo(ent[location - 1].getSurname()) == 0) {

				if (initial.compareTo(ent[location - 1].getInitial()) < 0) {

					ent[location] = ent[location - 1];

					location--;
				} else {
					break;
				}
			} else {
				break;
			}
		}

		ent[location] = new Entry(name, initial, ext);

		// Increases the number of entries variable to keep track of how many
		// there are.
		noOfEntries++;
	}

	public void delete(String name) {
		// This method is the first part of the deletion by surname function.
		
		//	Uses the search method to obtain the array location.
		int sel = search(name);
		
		//	Catches if there are no entries by that name.
		if(sel < 0){
			System.out.println("There is no entry by that name.");
		}
		else{
			//	User confirmation to avoid accidental deletion.
			System.out.println(ent[sel]);
			System.out.println("Do you wish to delete this record? (Y to confirm)");
	
			//	Analyses user input to confirm. Calls the removeEntry method
			if (input.next().toLowerCase().compareTo("y") == 0) {
				removeEntry(sel);
				System.out.println("Entry Deleted.");
			} else {
				System.out.println("Entry Retained.");
			}
		}
	}

	public void deleteExt(String ext) {
		//	This method is the first part of the delete by extension function.
		int location = -1;
		int x = 0;
		
		while(location < 0){
			if(ent[x].getExt().compareTo(ext) == 0){
				location = x;
			}
			x++;
		}
		
		if(location < 0){
			System.out.println("There is no entry by that name.");
		}
		else{
			//	User confirmation to avoid accidental deletion.
			System.out.println(ent[location]);
			System.out.println("Do you wish to delete this record? (Y to confirm)");
	
			//	Analyses user input to confirm. Calls the removeEntry method
			if (input.next().toLowerCase().compareTo("y") == 0) {
				removeEntry(location);
				System.out.println("Entry Deleted.");
			} else {
				System.out.println("Entry Retained.");
			}
		}
		
				
	}

	private void removeEntry(int location) {
		//	This method is for removing the entry from the array.
		
		while (location != noOfEntries) {

			ent[location] = ent[location + 1];

			location++;
		}
		noOfEntries--;
			
	}
	
	public int search(String name){
		return search(name, 0, noOfEntries);
	}

	private int search(String name, int left, int right) {
		//	This method searches the array for entries, returning the array location if
		//	found and a negative number if no entries exist.

		if (left <= right) {
			int mid = (left + right) / 2;

			int result = ent[mid].getSurname().compareTo(name);

			if (result > 0) {
				return search(name, left, mid - 1);
			} else if (result < 0) {
				return search(name, mid + 1, right);
			} else {
				return (mid);
			}
		} else {
			return -1;
		}

	}

	public void edit(String name) {
		//	This method calls a current entry and allows the user to alter the contents.
		int location = search(name);
		String alter;
		
		if(location < 0){
			System.out.println("There are no entries.");
		}
		else{
			System.out.println("Change extension from \"" + ent[location].getExt() + "\" to:");
			alter = input.next();
			
			System.out.println("Confirm change:");
			System.out.println("Change extension from \"" + ent[location].getExt() + "\" to \"" + alter + "\"? (y or n)");
			
			if(input.next().toLowerCase().compareTo("y") == 0){
				ent[location].setExt(alter);
				System.out.println("Value Changed.");
			}
		}
		
		
	}

	public void print() {
		//	This method prints all entries in the array.
		
		int x = 0;

		do {
			System.out.println(ent[x]);
			x++;

		} while (ent[x] != null);
	}
	
	public void print(int sel){
		System.out.println(ent[sel]);
	}
}