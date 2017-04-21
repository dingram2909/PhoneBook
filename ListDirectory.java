import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class ListDirectory implements Directory {

	/**
	 * @author Daniel Ingram
	 */

	private List<Entry> ent = new LinkedList<Entry>();
	private int noOfEntries = 0;
	private Scanner input = new Scanner(System.in);

	public void insert(String name, String initial, String ext) {
		int location = 0;

		if (noOfEntries == 0) {
			ent.add(new Entry(name, initial, ext));

		} else {
			while (location <= noOfEntries) {

				if (location == noOfEntries) {
					ent.add(location, new Entry(name, initial, ext));
					break;
				} else if (ent.get(location).getSurname().compareTo(name) < 0) {
					location++;
				} else {
					ent.add(location, new Entry(name, initial, ext));
					break;
				}

			}
		}
		noOfEntries++;
	}

	public void delete(String name) {
		// This method is the first part of the deletion by surname function.

		// Uses the search method to obtain the array location.
		int sel = search(name);

		// Catches if there are no entries by that name.
		if (sel < 0) {
			System.out.println("There is no entry by that name.");
		} else {
				System.out.println("Deleted.");
				ent.remove(sel);
				noOfEntries--;

		}
	}

	public void deleteExt(String ext) {
		int location = -1;
		int x = 0;

		while (location < 0) {
			if (ent.get(x).getExt().compareTo(ext) == 0) {
				location = x;
			}
			x++;
		}

		if (location < 0) {
			System.out.println("There is no entry by that name.");
		} else {
			
			System.out.println("Deleted.");
				ent.remove(location);
				noOfEntries--;
		}
	}

	public int search(String name) {
		return search(name, 0, noOfEntries);
	}

	private int search(String name, int left, int right) {

		if (left <= right) {
			int mid = (left + right) / 2;

			int result = ent.get(mid).getSurname().compareTo(name);

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
		// This method calls a current entry and allows the user to alter the
		// contents.
		int location = search(name);
		String alter;

		if (location < 0) {
			System.out.println("There are no entries.");
		} else {
			System.out.println("Change extension from \""
					+ ent.get(location).getExt() + "\" to:");
			alter = input.next();

			System.out.println("Confirm change:");
			System.out.println("Change extension from \""
					+ ent.get(location).getExt() + "\" to \"" + alter
					+ "\"? (y or n)");

			if (input.next().toLowerCase().compareTo("y") == 0) {
				edit(location, alter);
				System.out.println("Value Changed.");
			}
		}
	}
	
	public void edit(int location, String alter){
		ent.get(location).setExt(alter);
	}

	public void print() {
		for (int i = 0; i < noOfEntries; i++) {
			System.out.println(ent.get(i).toString());
		}
	}
	
	public void print(int sel){
		if(sel < 0){
			System.out.println("There is no entry by that name.");
		}
		else{
			System.out.println(ent.get(sel).toString());
		}
		
	}

}
