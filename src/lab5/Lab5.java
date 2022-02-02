package lab5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Lab5 extends JFrame implements ActionListener {
	private JTextField input, name, number;
	private JButton b1,b2,b3,b4,b5,b6;
	
	private PhoneBook myBook;
	private ArrayList<Person> persons = new ArrayList<>();
	
	private int personcounter = 0;
	
	
public Lab5() {
	 myBook = new PhoneBook();
	 JFrame frame = new JFrame();
	 
	 Font theFont = new Font("SansSerif", Font.PLAIN, 20);
	 
	 frame.setTitle("Interactive phone book");
	 frame.setFont(theFont);
	 
	 Container c = getContentPane();
	 c.setLayout(new GridLayout(3,3));
	 
	 b1 = new JButton("load");
  	 b2 = new JButton("save");
  	 b3 = new JButton("search");
  	 b4 = new JButton("next");
  	 b5 = new JButton("add");
  	 b6 = new JButton("delete");
  	 
  	 c.add(b1);
  	 b1.setEnabled(true);
  	 b1.addActionListener(this);
  	 
  	 c.add(b2);
  	 b2.setEnabled(false);
  	 b2.addActionListener(this);
  	 
  	 input = new JTextField();
  	 c.add(input);
  	 input.addActionListener(this);
  	 
  	 c.add(b3);
  	 b3.setEnabled(false);
  	 b3.addActionListener(this);
  	 
  	 c.add(b4);
  	 b4.setEnabled(false);
  	 b4.addActionListener(this);
  	 
  	 name = new JTextField();
  	 c.add(name);
  	 name.addActionListener(this);
  	 
     c.add(b5);
     b5.setEnabled(false);
     b5.addActionListener(this);
     
     c.add(b6);
     b6.setEnabled(false);
     b6.addActionListener(this);
     
     number = new JTextField();
     c.add(number);
     number.setEnabled(false);
     
     pack(); 
     setVisible(true);
     setDefaultCloseOperation(EXIT_ON_CLOSE);
}
	private void display(Person person) {
		
	  name.setText(person.getFullName());
	  number.setText(Integer.toString(person.getPhoneNumber()));
	
	}
	
	private void search(String arg) {
		persons = myBook.search(arg);
		if(persons.isEmpty()) {
			name.setText("provide name");
			number.setText("");
		}else  {
			display(persons.get(0));
		}
		if(persons.size() > 1) {
			b4.setEnabled(true);
			personcounter = 0;
		}else {
			b4.setEnabled(false);
		}
		input.setText("");
	}
	
	private void nextname() {
		personcounter++;
		display(persons.get(personcounter));
		if(personcounter+1 == persons.size()) {
			end();
		}
	}
	private void end() {
		b4.setEnabled(false);
		personcounter = 0;
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		if(actionEvent.getSource() == b1) {
			String arg = input.getText();
			String load = myBook.load(arg);
			
			if(!load.equals("try again")) {
				b2.setEnabled(true);
				b3.setEnabled(true);
				b5.setEnabled(true);
				b6.setEnabled(true);
			}
			input.setText("");
			name.setText(load);
		}
		if(actionEvent.getSource() == b2) {
			String arg = input.getText();
			if(arg.isEmpty()) {
				input.setText("provide name of file");
				
			}else {
				String response = myBook.save(arg);
				name.setText(response);
			}
			input.setText("");
		}
		if(actionEvent.getSource() == b3) {
			String arg = input.getText();
			search(arg);
		}
		
		if(actionEvent.getSource() == b4) {
			nextname();
		}
		if(actionEvent.getSource() == b6) {
			String inputnumber = number.getText();
			String inputname = name.getText();
			
			if(!inputnumber.isEmpty() && !inputname.isEmpty()) {
				String reponse = myBook.deletePerson(inputname, Integer.parseInt(inputnumber));
				input.setText(reponse);
			}
		}
		 if (actionEvent.getSource() == b5){
	            if (name.isEnabled()){
	          
	                boolean success = myBook.addPerson(name.getText(), Integer.parseInt(number.getText()));
	                
	                input.setText( success ? "Person added" : "Person not added");
	                name.setEnabled(false);
	                number.setEnabled(false);
	                name.setText("");
	                number.setText("");
	            }else{
	                
	                input.setText("give me name and number");
	                name.setEnabled(true);
	                number.setEnabled(true);
	                name.setText("");
	                number.setText("");
	            }
	        }

	    }
	public static void main(String[] args) {
		
		Lab5 lab = new Lab5();
	}
	
}
