
public interface Directory {

	public void insert(String name, String initial, String ext);
	
	public void delete(String name);
	
	public void deleteExt(String ext);
	
	public int search(String name);
	
	public void edit(String name);
	
	public void print();
}
