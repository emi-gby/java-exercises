import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        String filePath = "contacts.txt";

        List<String> lines = new ArrayList<>();


        //adding lines to temp memory -> used to delete contacts
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){

            String line;
            while((line = reader.readLine()) != null){
                lines.add(line);
            }

        }
        catch(FileNotFoundException e){
            System.out.println("Could not locate contacts file location.");
        }
        catch(IOException e){
            System.out.println("Could not read contact file.");
        }




        String userInput = "";

        while(!userInput.equals("Q")){
            System.out.print("""
                    \n-------Contact Manager-------
                    1. Add contact;
                    2. List all contacts;
                    3. Search contact;
                    4. Delete a contact;
                    Q. Quit;
                    -----------------------------------
                    Chose an option :""");
            userInput = scanner.nextLine().toUpperCase();

            switch (userInput){
                case "1" -> addContact(filePath, scanner,lines);
                case "2" -> listContacts(filePath);
                case "3" -> searchContact(filePath, scanner);
                case "4" -> deleteContact(filePath, scanner, lines);
                case "Q" -> System.out.println("System finalization completed.");
                default -> System.out.println("Not a valid option!");
            }

        }

        scanner.close();
    }
    static void addContact(String filePath, Scanner scanner, List<String> lines){
        try(FileWriter writer = new FileWriter(filePath, true)){
            System.out.print("Name: ");
            String name = scanner.nextLine();

            try{
                System.out.print("Phone(format: 12345678): ");
                int phone = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Email: ");
                String email = scanner.nextLine();

                if(email.contains("@") && email.contains(".com")){

                    String contactInfo = name + ";" + phone + ";" + email;

                    writer.write(contactInfo + "\n");

                    lines.add(contactInfo);

                    System.out.println("Contact added successfully.");

                }
                else{
                    System.out.println("Invalid email (format: _@_.com). ");
                }

            }
            catch(InputMismatchException e){
                scanner.nextLine();
                System.out.println("Invalid phone (format: 12345678).");
            }


        }
        catch (FileNotFoundException e){
            System.out.println("Could not locate file location");
        }
        catch (IOException e){
            System.out.println("Could not add contact");
        }

    }
    static void listContacts(String filePath){
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){

            System.out.println("--------------------------");
            System.out.println("------Contacts List-------");

            String line;
            while((line = reader.readLine()) != null){

                String [] parts = line.split(";");
                System.out.println("Name: "+ parts[0]);
                System.out.println("Phone: "+ parts[1]);
                System.out.println("Email: "+ parts[2]);
                System.out.println("--------------------------");

            }
        }
        catch(FileNotFoundException e){
            System.out.println("Could not locate contacts file location.");
        }
        catch(IOException e){
            System.out.println("Could not read contact file.");
        }
    }
    static String searchContact(String filePath, Scanner scanner){
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){

            System.out.print("Enter contact name: ");
            String contactName = scanner.nextLine();


            String line;
            while((line = reader.readLine()) != null){

                String [] parts = line.split(";");
                String name = parts[0];

                if(contactName.equals(name)) {
                    System.out.println("---------Contact---------");
                    System.out.println("Name: " + name);
                    System.out.println("Phone: " + parts[1]);
                    System.out.println("Email: " + parts[2]);
                    System.out.println("--------------------------");
                    return line;
                }
            }

            System.out.println("Contact not found.");

        }
        catch(FileNotFoundException e){
            System.out.println("Could not locate contacts file location.");
        }
        catch(IOException e){
            System.out.println("Could not read contact file.");
        }
        return null;
    }
    static void deleteContact(String filePath, Scanner scanner,
                              List<String> lines){

        String line = searchContact(filePath, scanner);

        if(line!=null){
            try(FileWriter writer = new FileWriter(filePath)){

                for(String l : lines){
                    if(!l.contains(line)){
                        writer.write(l+"\n");
                    }
                }
                lines.remove(line);
                System.out.println("Contact deleted successfully.");
            }
            catch (FileNotFoundException e){
                System.out.println("Could not locate file location");
            }
            catch (IOException e){
                System.out.println("Could not add contact");
            }
        }

    }
}
