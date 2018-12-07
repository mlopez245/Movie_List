//Moises Lopez
/*			Project Watch Movies All Day
 *
 * Purpose of Project: Is to create a list of your favorite movies to
 * then print them out to a text file.							   
 *
 * How To Use It: In text fields, you're going to input the name of movie, year,
 * director, and length in minutes. You click submit to save it to a list.
 * You click "Clear Fields" to clear all text fields if you want to add another movie.
 * If you want to delete your current list, you go to Edit -> Delete.
 * To save list to a text file, you go to File -> Save.
 * 
 * Model Name: Movie; View Name: LineFrame; Controller Name: Implemented in the JFrame
 * 
 * Types of Serialization Supported: Text.
 * 
 * Future Enhancements: Add a category to the list so you can sort the list by genre. 
 * Also sort the list by name, year, and length.
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Movie { //model class
    private String name;
    private String year;
    private String director;
    private String length;
    
    public void setName(String name) {
        this.name = name;      
    }
    public String getName() {
    	return name;
    }
    public void setYear(String year) {
        this.year = year;       
    }
    public String getYear() {
    	return year;
    }
    public void setDirector(String director) {
        this.director = director;       
    }
    public String getDirector() {
    	return director;
    }
    public void setLength(String length) {
        this.length = length;      
    }
    public String getLength() {
    	return length;
    }
    public Movie(String name, String year, String director, String length) { //constructor
        setName(name);
        setYear(year);
        setDirector(director);
        setLength(length);
    }
    public Movie(int parseInt, int parseInt2) {
		// TODO Auto-generated constructor stub
	}
	public String toString() { //to string
        return ("Movie Name: "+name+" ("+year+") \nDirector: "+director+" \nLength: "+length+" minutes"+"\n");
    }
}

class LineFrame extends JFrame implements ActionListener {
	ArrayList<Movie> myMovies = new ArrayList<>(); //Arraylist to store movie details
	JTextField name;
	JTextField year;
	JTextField director;
	JTextField length;
	JTextArea display;	   
    public LineFrame() {
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setBounds(200,200,550,275);
    	setTitle("Movie List");
    	Container c = getContentPane();
    	
    	Font font = new Font("sansserif", Font.BOLD,12); //Create font for JTextFields
        
    	JPanel panCenter = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	JLabel Nm = new JLabel("Name:     ");
        panCenter.add(Nm);       
        name = new JTextField();
        name.setFont(font);
        panCenter.add(name);
        name.setPreferredSize(new Dimension(160,30));
        c.add(panCenter,BorderLayout.CENTER);
        
        JLabel y = new JLabel("Year:       ");
        panCenter.add(y);       
        year = new JTextField();
        year.setFont(font);
        panCenter.add(year);
        year.setPreferredSize(new Dimension(160,30));
        c.add(panCenter,BorderLayout.CENTER);
        
        JLabel Dr = new JLabel("Director: ");
        panCenter.add(Dr);
        director = new JTextField();
        director.setFont(font);
        panCenter.add(director);
        director.setPreferredSize(new Dimension(160,30));
        c.add(panCenter,BorderLayout.CENTER);
        
        JLabel lt = new JLabel("Length:   ");
        panCenter.add(lt);       
        length = new JTextField();
        length.setFont(font);
        panCenter.add(length);
        length.setPreferredSize(new Dimension(160,30));
        c.add(panCenter,BorderLayout.CENTER);
        
        JButton clear = new JButton("Clear Fields");       
        panCenter.add(clear);
        c.add(panCenter,BorderLayout.CENTER);
        clear.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) { //controller
        		name.setText(""); //clear all text fields
        		year.setText("");
        		director.setText("");
        		length.setText("");
        		repaint();
        		}
        	});
        JButton btnSubmit = new JButton("Submit");
        panCenter.add(btnSubmit);
        btnSubmit.addActionListener(new ActionListener() { //controller
        	public void actionPerformed(ActionEvent e) { 
        		Movie movie = new Movie(name.getText(), year.getText(), director.getText(), length.getText());
                myMovies.add(movie);
                display.setText(display.getText() + "\n" + movie.toString());
                repaint();
                }
        	});
        JMenuBar bar = new JMenuBar();
        JMenu mnuFile = new JMenu("File");
        JMenuItem miSave = new JMenuItem("Save");
        bar.add(mnuFile);
        mnuFile.add(miSave);        
        miSave.addActionListener(new ActionListener() { //controller
        	public void actionPerformed(ActionEvent e) {
        		JFileChooser jfc = new JFileChooser();
                if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        File f = jfc.getSelectedFile();
                        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
                        for (Movie p : myMovies) {
                            pw.println(p); //Print to a text file
                        }
                        pw.close();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,"Could not save file.");
                        }
                    }
                }
        	});        
        JMenu mnuEdit = new JMenu("Edit");
        JMenuItem miDel = new JMenuItem("Delete");
        bar.add(mnuEdit);
        mnuEdit.add(miDel);
        setJMenuBar(bar);
        miDel.addActionListener(new ActionListener() { //controller
        	public void actionPerformed(ActionEvent e) {
        		myMovies.clear(); //clear list
        		display.setText(""); //clear JTextbox area
        		repaint();        		
        	}
        	});   
        JPanel panCenter1 = new JPanel(new FlowLayout()); //view
        display = new JTextArea(10,25);
        JScrollPane scroll = new JScrollPane(display); 
        /*Create a scroll bar to navigate through the list
         of movies
         */
        scroll = new JScrollPane(display,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panCenter1.add(scroll);       
        display.setFont(font);                    
        display.setEditable(false);
        c.add(panCenter1,BorderLayout.EAST);           
    }	
	public void actionPerformed(ActionEvent e) {}
}

public class MovieList1 {
    public static void main(String[] args) {   	
        LineFrame frm = new LineFrame();
        frm.setVisible(true); //show frame
    }
}