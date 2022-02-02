package lab5;

import java.util.ArrayList;
import java.io.*;

public class PhoneBook {
	private ArrayList<Person> listOfNumbers;
	
	
	public PhoneBook() {
		listOfNumbers = new ArrayList<>();
	}
	
	public String load(String arg) {
		File fileToRead = new File(arg);
		
		BufferedReader filereader;
		try {
			filereader = new BufferedReader(new FileReader(fileToRead));
            String str;
            
            while((str = filereader.readLine())!= null){
                String[] temp = str.split("\\s+");
                
                Person person = new Person(temp[0], temp[1], Integer.parseInt(temp[2]));
                listOfNumbers.add(person);
            }

            filereader.close();
        }catch (IOException e) {
           
            return "ERROR:" + e;
        }

        return "loaded";
    }
	
	public ArrayList<Person> search(String arg) {
		ArrayList<Person> match = new ArrayList<Person>();
		
		for(Person person: listOfNumbers) {
			if(arg.equalsIgnoreCase(person.getSurname()) || arg.equals(Integer.toString(person.getPhoneNumber()))) {
				match.add(person);
			}
		}
		return match;
		
	}
	public String deletePerson(String arg1, int arg2)
	{
		String str = "";
        for(Person person: listOfNumbers){
            if(arg1.equalsIgnoreCase(person.getFullName()) && arg2 == person.getPhoneNumber()) {
                listOfNumbers.remove(person);
                return "person deleted";
                }else {
                	str = "person / number does not exist";
        }
            }
        return str;
    }
	
	public boolean addPerson(String arg1, int arg2) {
		for(Person person: listOfNumbers) {
			if(arg2 == person.getPhoneNumber()) {
				return false;
			}
		}
		String [] slot = arg1.split("\\s+");
		if(slot.length !=2) {
			return false;
		}
		
		Person newperson = new Person(slot[0],slot[1], arg2);
		listOfNumbers.add(newperson);
		return true;
	}
	public String save(String arg) {
		File saveto = new File(arg);
		
		BufferedWriter filewriter;
		
		try {
			filewriter = new BufferedWriter(new FileWriter(saveto));
			
			for(Person person: listOfNumbers) {
				filewriter.write(String.format("%-20s%-5d\n",person.getFullName(), person.getPhoneNumber() ));
			}
			filewriter.close();
			
		}catch(IOException e) {
			return "could not save";
		}
		return "saved  " + listOfNumbers.size() + "  people to file";
		
	}
	

}
