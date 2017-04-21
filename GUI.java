import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;


public class GUI {

	/**
	 * @author Daniel Ingram
	 */
	
	private CardLayout cards = new CardLayout();
	private String search = null;
	private int location = 0;
	final ByteArrayOutputStream stream = new ByteArrayOutputStream();
	PrintStream print = new PrintStream(stream);
	PrintStream old = System.out;

	
	public static void main(String[] args) {
	
		GUI test = new GUI();
		
		System.out.flush();
	}
	
	private JFrame frame;
	
	public GUI(){		
		makeFrame();
	}

	private void makeFrame(){
		final ListDirectory dire = new ListDirectory();
		System.setOut(print);

		
		
		//Method used to populate directory.
		populate(dire);
		
		final String HOME = "Home Screen Card";
		final String INSERT = "Insert Card";
		final String LOOKUP = "Lookup Card";
		final String EDIT = "Edit Card";
		final String DELETE = "Delete Card";
		
		
		frame = new JFrame("Newcastle University Phone Directory");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension size = new Dimension(300, 300);
		frame.setPreferredSize(size);
		
		final Container cont = new Container();
		
		frame.setContentPane(cont);
		
		frame.setLayout(cards);
		
		//	HOME SCREEN LAYOUT
		
		final JPanel home = new JPanel();	
		final JPanel insert = new JPanel();
		final JPanel lookup = new JPanel();
		final JPanel edit = new JPanel();
		final JPanel delete = new JPanel();
		
		home.setLayout(new BorderLayout());
		
		JLabel hmText = new JLabel("<html><center>Welcome to the Newcastle University Phone Directory.<br/>" +
				"<br/>Please select what you would like to do.<center><br/></html>");
		hmText.setAlignmentX(Component.CENTER_ALIGNMENT);
		home.add(hmText, BorderLayout.NORTH);
		
		JPanel centre = new JPanel();		
		centre.setLayout(new BoxLayout(centre, BoxLayout.Y_AXIS));		
		
		JButton btnLookup = new JButton("Lookup");
		btnLookup.setAlignmentX(Component.CENTER_ALIGNMENT);
		centre.add(btnLookup);
		btnLookup.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				cards.show(cont, LOOKUP);
			}
			
		});
		
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setAlignmentX(Component.CENTER_ALIGNMENT);
		centre.add(btnInsert);
		btnInsert.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				cards.show(cont, INSERT);
			}
			
		});
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setAlignmentX(Component.CENTER_ALIGNMENT);
		centre.add(btnEdit);
		btnEdit.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				cards.show(cont, EDIT);
			}
			
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		centre.add(btnDelete);
		btnDelete.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				cards.show(cont, DELETE);
			}
			
		});
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setAlignmentX(Component.CENTER_ALIGNMENT);
		centre.add(btnPrint);
		btnPrint.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				dire.print();				
				JOptionPane.showMessageDialog(frame, stream.toString());
				stream.reset();
			}
			
		});
		
		home.add(centre, BorderLayout.CENTER);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setAlignmentX(Component.RIGHT_ALIGNMENT);
		home.add(btnExit, BorderLayout.SOUTH);
		
		btnExit.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				System.setOut(old);
				System.exit(0);
			}
			
		});
		
		//	INSERT SCREEN LAYOUT
		
		insert.setLayout(new BoxLayout(insert, BoxLayout.Y_AXIS));
		
		JLabel inText = new JLabel("Please enter employee credentials:");
		inText.setAlignmentX(Component.CENTER_ALIGNMENT);
		insert.add(inText);
		
		final JTextField txtSurname = new JTextField("SURNAME");
		insert.add(txtSurname);
		
		final JTextField txtInitial = new JTextField("INITIAL");
		insert.add(txtInitial);
		
		final JTextField txtExt = new JTextField("EXTENSION");
		insert.add(txtExt);
		
		JPanel insertBottom = new JPanel();
		insertBottom.setLayout(new BoxLayout(insertBottom, BoxLayout.X_AXIS));
		
		JButton insButton = new JButton("Insert");
		
		insButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				dire.insert(txtSurname.getText(), txtInitial.getText(), txtExt.getText());
				dire.print();
				JOptionPane.showMessageDialog(frame, "Insertion Successful");
				cards.show(cont, HOME);
			}
			
		});
		
		JButton insBack = new JButton("Back");
		
		insBack.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				cards.show(cont, HOME);
			}
			
		});
		
		insertBottom.add(insButton);
		insertBottom.add(insBack);
		
		insert.add(insertBottom);
				
		//	LOOKUP SCREEN LAYOUT
		
		lookup.setLayout(new BoxLayout(lookup, BoxLayout.Y_AXIS));
		
		JLabel lookText = new JLabel("Please enter the name you are looking for:");
		lookText.setAlignmentX(Component.CENTER_ALIGNMENT);
		lookup.add(lookText);
		
		final String TXTSEARCH_DEFAULT = "Enter a name.";
		final JTextField txtSearch = new JTextField(TXTSEARCH_DEFAULT);
		lookup.add(txtSearch);
		
		JPanel lookBottom = new JPanel();
		lookBottom.setLayout(new BoxLayout(lookBottom, BoxLayout.X_AXIS));

		JButton lookSearch = new JButton("Search");

		lookSearch.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				dire.print(dire.search(txtSearch.getText()));
				JOptionPane.showMessageDialog(frame, stream.toString());
				stream.reset();
				txtSearch.setText(TXTSEARCH_DEFAULT);
				cards.show(cont, HOME);
			}
			
		});
				
		JButton lookBack = new JButton("Back");
		
		lookBack.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				cards.show(cont, HOME);
			}
			
		});
		
		lookBottom.add(lookSearch);
		lookBottom.add(lookBack);
		
		lookup.add(lookBottom);
		
		//	EDIT SCREEN LAYOUT
		
		edit.setLayout(new BoxLayout(edit, BoxLayout.Y_AXIS));
		
		JLabel ed1Text = new JLabel("Please enter the employee you wish to alter");
		ed1Text.setAlignmentX(Component.CENTER_ALIGNMENT);
		edit.add(ed1Text);
		
		final JTextField ed1Input = new JTextField(TXTSEARCH_DEFAULT);
		edit.add(ed1Input);
		
		JPanel ed1Bottom = new JPanel();
		ed1Bottom.setLayout(new BoxLayout(ed1Bottom, BoxLayout.X_AXIS));
		
		JButton ed1Edit = new JButton("Edit");
		ed1Edit.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				location = dire.search(ed1Input.getText());
				if(location < 0){
					JOptionPane.showMessageDialog(frame, "There are no entries by that name.");
				}
				else{
					search = ed1Input.getText();
					if(JOptionPane.showConfirmDialog(frame, "Change this entry?\nName: " + getName(dire, search) + "\nInitial: " + 
							getInitial(dire, search) + "\nExtension: " + getExt(dire, search), "Entry Alteration", 
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
					
						String input = JOptionPane.showInputDialog(frame, "Please enter the new extension:");
						
						if(input != null){
						
							dire.edit(location, input);
						
							JOptionPane.showMessageDialog(frame, "Entry Altered");
						}
					}
					
					
				}
				stream.reset();
				txtSearch.setText(TXTSEARCH_DEFAULT);
			}
			
		});
		
		
		JButton ed1Back = new JButton("Back");
		ed1Back.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				cards.show(cont, HOME);
			}
			
		});
		
		ed1Bottom.add(ed1Edit);
		ed1Bottom.add(ed1Back);
		
		edit.add(ed1Bottom);
		
		//	DELETE SCREEN LAYOUT
		
		delete.setLayout(new BoxLayout(delete, BoxLayout.Y_AXIS));
		
		JLabel delText = new JLabel("Please enter the credentials you wish to delete:");
		delText.setAlignmentX(Component.CENTER_ALIGNMENT);
		delete.add(delText);
		
		final JTextField delInput = new JTextField();
		delete.add(delInput);
		
		JPanel deleteBottom = new JPanel();
		deleteBottom.setLayout(new BoxLayout(deleteBottom, BoxLayout.X_AXIS));
		
		JButton delName = new JButton("By Name");
		deleteBottom.add(delName);
		
		delName.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				search = delInput.getText();
				
				if(JOptionPane.showConfirmDialog(frame, "Delete this entry?\nName: " + getName(dire, search) + "\nInitial: " + 
							getInitial(dire, search) + "\nExtension: " + getExt(dire, search), "Entry Alteration", 
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
					dire.delete(delInput.getText());
					
					JOptionPane.showMessageDialog(frame, stream.toString());
					stream.reset();
					cards.show(cont, HOME);
				}
			}
			
		});
		
		JButton delNum = new JButton("By Number");
		deleteBottom.add(delNum);
		
		delNum.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				search = delInput.getText();
				
				dire.deleteExt(delInput.getText());
				
				JOptionPane.showMessageDialog(frame, stream.toString());
				stream.reset();
				cards.show(cont, HOME);

			}
			
		});
		
		JButton delBack = new JButton("Back");
		deleteBottom.add(delBack);
		
		delBack.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				cards.show(cont, HOME);
			}
			
		});
		
		delete.add(deleteBottom);
				
		// 	ADDING CARDS TO FRAME
		
		frame.add(home, HOME);
		frame.add(insert, INSERT);
		frame.add(edit, EDIT);
		frame.add(lookup, LOOKUP);
		frame.add(delete, DELETE);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private String getName(ListDirectory dire, String sel){
		if(sel == null){
			return "";
		}
		else{		
			System.setOut(print);
			
			dire.print(dire.search(sel));
			
			String temp = stream.toString();
			
			String[] split;
			
			split = temp.split("\t");
			
			stream.reset();
			
			return split[0];
		}
	}
	
	private String getInitial(ListDirectory dire, String sel){
		if(sel == null){
			return "";
		}
		else{
			System.setOut(print);
			
			dire.print(dire.search(sel));
			
			String temp = stream.toString();
			
			String[] split;
			
			split = temp.split("\t");
			
			stream.reset();
			
			return split[1];
		}
	}
	
	private String getExt(ListDirectory dire, String sel){
		if(sel == null){
			return "";
		}
		else{
			System.setOut(print);
			
			dire.print(dire.search(sel));
			
			String temp = stream.toString();
			
			String[] split;
			
			split = temp.split("\t");
			
			stream.reset();
			
			return split[2];
		}
	}
	
	private void populate(ListDirectory dire){
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

				dire.insert(split[0], split[1], split[2]);

			} while ((temp = in.readLine()) != null);

			// Closes the FileReader stream.
			/* DO NOT REMOVE! */
			read.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
}
