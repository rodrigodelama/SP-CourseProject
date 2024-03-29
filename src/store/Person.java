package store;

import java.util.Scanner;
import java.io.*;

//Systems Programming Course Project
//Rodrigo De Lama - 100451775@alumnos.uc3m.es - @RDLF11
//Jaime Mato - 100451639@alumnos.uc3m.es - @Pekeniojimi
//Manuel Morales - 100451690@alumnos.uc3m.es - @ikaoseu

public class Person implements Comparable {

    //Person Attributes
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    //Person Constructor
    public Person(int id, String firstName, String lastName, String email) {
        try {
            setID(id);
            setFirstName(firstName);
            setLastName(lastName);
            setEmail(email);
        } catch (PersonException pe) { //pe is Person
            pe.printStackTrace(); //Prints this throwable and its backtrace to the standard error stream
            System.exit(1); //System code exit 1
        }
    }

    public Person() {
        this(0000, "Not defined", "Not defined", "xxxx@xxxx.com");
    }

    public Person(String file) {
        readFromFile(file);
    }
    
    //Methods
    //Getters return private variables on demand
    public int getID() {
        return id;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    //Setters
    public void setFirstName(String firstName) throws PersonException {
        if (firstName.length() > 15) {
            throw new PersonException("The first name must have a maximum of 15 characters");
        } else {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) throws PersonException {
        if (lastName.length() > 15) {
            throw new PersonException("The last name must have a maximum of 15 characters");
        } else {
            this.lastName = lastName;
        }
    }

    public void setEmail(String email) throws PersonException {
        // test than email has @ before set
        this.email = email;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void set(String[] personData) {
        try {
            setID(Integer.parseInt(personData[0]));
            setFirstName(personData[1]);
            setLastName(personData[2]);
            setEmail(personData[3]);
        } catch (PersonException pe) {
            pe.printStackTrace();
            System.exit(1);
        }
    }

    //Person toString method
    public String toString() {
        return id + "\\|" + firstName + "\\|" + lastName + "\\|" + email + "\n";
    }

    public void print() {
        System.out.println(this);
    }

    public static Person readFromStdio() {
        System.out.println("Setting new Person");
        Person result = new Person();
        try {
            Scanner userInput = new Scanner(System.in);
                System.out.print("Id: ");
            result.setID(Integer.parseInt(userInput.next()));
                System.out.print("First Name: ");
            result.setFirstName(userInput.next());
                System.out.print("Last Name: ");
            result.setLastName(userInput.next());
                System.out.print("email: ");
            result.setEmail(userInput.next());
            userInput.close();
        } catch (PersonException pe) {
            pe.printStackTrace();
            System.exit(1);
        }
        return result;
    }

    public static Person readFromFile(String file) {
        Person result = new Person();
        BufferedReader in = null;
        String line = null;
        if (file != null) {
            try {
                in = new BufferedReader(new FileReader(file));
                if ((line = in.readLine()) != null) {
                    String[] data = line.split("\\|");
                    result.set(data);
                    in.close();
                    return result;
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
                System.exit(1);
            }
        }
        return result;
    }

    public void writeToFile(String file) {
        BufferedWriter out = null;
        try {
            if (file != null) {
                out = new BufferedWriter(new FileWriter(file, true));
                out.write(toString());
                out.flush();
                out.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace(); // to debug
            System.exit(1);
        }
    }

    @Override
    public int compareTo(Object anotherPerson) {

        try {
            //NOT NEEDED
            //VAT = (Provider) VAT; //Explicit casting from object to Provider
            //VSCode states: Comparable is a raw type. References to generic type Comparable<T> should be parameterized Java(16777788)

            //Compares a provider object to another and returns a 0, -1 or 1
            if (anotherPerson != null) {
                if (id.compareTo(anotherPerson.getID()) < 0) {
                    return -1;
                } else if (id.compareTo(anotherPerson.getID()) > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
            return -1;
            
        } catch (ClassCastException e) {
            System.err.println("Please input a Provider into this comparable");
        }
        
    }

}
