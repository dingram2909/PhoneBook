
public class Entry {

	/**
	 * @author Daniel Ingram
	 * 
	 * TODO Write Comments!
	 */
	
	private String surname;
	private String initial;
	private String ext;
	
	public Entry(){
		setSurname(null);
		setInitial(null);
		setExt(null);
	}
	
	public Entry(String surname, String initial, String ext){
		setSurname(surname);
		setInitial(initial);
		setExt(ext);
	}
	
	public void setSurname(String surname){
		this.surname = surname;
	}
	
	public String getSurname(){
		return surname;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}
	
	public String toString(){
		return(String.format("%-15s\t%1s\t%4s", getSurname(), getInitial(), getExt()));

	}
	
	
}
