
public class HashDirectory implements Directory{

	/**
	 * @author Daniel Ingram
	 */
	private final int ARRAY_SIZE = 26;
	private final char BASE = 'A';
	private int array;
	private ListDirectory[] ent = new ListDirectory[ARRAY_SIZE];
	
	public HashDirectory(){
		for(int i = 0; i < ARRAY_SIZE; i++){
			ent[i] = new ListDirectory();
		}
	}
	
	public void insert(String name, String initial, String ext){
		ent[listSel(name)].insert(name, initial, ext);
	}
	
	public void delete(String name){
		ent[listSel(name)].delete(name);
	}
	
	public void deleteExt(String ext){
		for(int i = 0; i < ARRAY_SIZE; i++){
			try{
				ent[i].deleteExt(ext);
			}
			catch(IndexOutOfBoundsException e){
				
			}
		}
	}
	
	public int search(String name){
		array = listSel(name);
		return search(name, array);		
	}
	
	public int search(String name, int list){
		return ent[list].search(name);
	}
	
	public void edit(String name){
		ent[listSel(name)].edit(name);
	}
	
	public void print(){
		for(int i = 0; i < ARRAY_SIZE ; i++){
			ent[i].print();
		}
	}
	
	public void print(int sel){
		ent[array].print(sel);
	}

	private int listSel(String name){
		return ((int) name.charAt(0) - BASE);
	}
	
}
