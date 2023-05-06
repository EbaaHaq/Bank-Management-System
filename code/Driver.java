import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
public class Driver {
    private static List<Person> persons = new ArrayList<Person>();

    private static Person searchPerson(String ID) {
        for (Person p : persons) {
            if (p.getCNIC().compareTo(ID) == 0)
                return p;
        }
        return null;
    }
    public static void main(String args[]) {
        // PERSONS FILE
        File FILE1 = new File("PERSONS.txt");
        try {
            FILE1.createNewFile();
            System.out.println("File created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // CUSTOMERS FILE
        File FILE2 = new File("CUSTOMERS.txt");
        try {
            FILE2.createNewFile();
            System.out.println("File created successfully.");
        } catch(IOException e) {
            e.printStackTrace();
        }
        // ACCOUNTS FILE
        File FILE3 = new File("ACCOUNTS.txt");
        try {
            FILE3.createNewFile();
            System.out.println("File created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // BANK FILE
        File FILE4 = new File("BANK.txt");
        try {
            FILE4.createNewFile();
            System.out.println("File created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Person p1 = new Person("Ali", "111", "03314576556");
        Person p2 = new Person("Ahmad", "222", "03316789654");

        persons.add(p1);
        persons.add(p2);
        Bank bank = new Bank("ABC");
        for(Person p : persons){
            loadPeople(FILE1,p.getCNIC());
        }


        Scanner input = new Scanner(System.in);
        String choice = "";
        while (choice.compareTo("F") != 0) {
            System.out.println("Enter the following \n C - adding clients \n A - adding account");
            System.out.println(" W - withdrawing money \n D - deposit money \n I - client Info \n B- Bank's Info ");
            System.out.println(" X - Account detail \n F - exit");
            switch (input.next()) {


                case "C": {
                    System.out.println("Enter Person's CNIC");
                    String cnic = input.next();
                    ArrayList<Person> clList = new ArrayList<>();
                    for (Person p : persons) {
                        if (cnic.equals(p.getCNIC())) {
                            bank.addClient(p);
                            System.out.println("Client created");
//                            System.out.println();
                            loadCustomers(cnic,FILE2);
                        } else {
//                            System.out.println("invalid cnic");
                            continue;
                        }
                    }
                    break;
                }
                case "A": {
                    System.out.println("Enter CNIC to open account");
                    String cnic = input.next();
                    for (Client c : bank.getClients()) {
                        if (cnic.equals(c.getDetails().getCNIC())) {
                            System.out.println("Enter opening balance: ");
                            float balance = input.nextFloat();
                            bank.addAccount(balance, c);
                            System.out.println("Account created");
                            loadAccounts(balance, c,FILE3);
                        } else {
                            System.out.println("Client does not exist");

                        }
                    }
                    break;
                }


                case "B": {
                    System.out.println(bank);
                    Scanner scanner = null;
                    try {
                        scanner = new Scanner(FILE4);
                        String line = null;
                        while (scanner.hasNextLine()){
                                System.out.println(line);
                            break;
                            }
                    }
                    catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    loadBank(bank, FILE4);
                    break;
                }


                case "W": {
                    System.out.println("Enter cnic: ");
                    String cnic = input.next();
                    for (Client c : bank.getClients()) {
                        if (cnic.equals(c.getDetails().getCNIC())) {
                            System.out.println("Enter account number: ");
                            String accountNumber = input.next();
                            for (Account a : bank.getAccounts()) {
                                if (accountNumber.equals(a.getAccNumber())) {
                                    System.out.println("Enter amount to withdraw: ");
                                    float amountWith = input.nextFloat();
                                    a.withdraw(amountWith);
                                    loadAccounts(amountWith,c,FILE3);
                                } else {
                                    System.out.println("No account exists");
                                }
                            }

                        } else {
                            System.out.println("No Client exists");
                        }
                    }
                    break;
                }


                case "D": {
                    System.out.println("Enter cnic: ");
                    String cnic = input.next();
                    for (Client c : bank.getClients()) {
                        if (cnic.equals(c.getDetails().getCNIC())) {
                            System.out.println("Enter account number: ");
                            String accountNumber = input.next();
                            for (Account a : bank.getAccounts()) {
                                if (accountNumber.equals(a.getAccNumber())) {
                                    System.out.println("Enter amount to deposit: ");
                                    float amountDep = input.nextFloat();
                                    a.deposit(amountDep);
                                    System.out.println("Amount deposited successfully");
                                    loadAccounts(amountDep,c,FILE3);
                                    System.out.println("New balance is: " + a.getAmount());
                                } else {
                                    System.out.println("No account exists");
                                    break;
                                }
                            }
                        } else {
                            System.out.println("No Client exists");
                            break;

                        }
                    }
                    break;
                }


                case "I": {
                    System.out.println("Enter CNIC to check client's info");
                    String cnic = input.next();
                    Scanner scanner = null;
                    try {
                        scanner = new Scanner(FILE2);
                        int lineNum = 0;
                        String line = null;
                        while (scanner.hasNextLine()){
                            line = scanner.nextLine();
                            if (line.contains(cnic)) {
                                System.out.println(line);
                                break;
                            }
                            else{
                                System.out.println("client does not exists");
                            }
                            lineNum++;}
                    }
                    catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    for (Client c : bank.getClients()) {
                        if (cnic.equals(c.getDetails().getCNIC())) {
                            System.out.println(c);
//                            for (Account a : c.getAccounts()) {
//                                System.out.println(a);
//                            }
                        } else {
                            System.out.println("No client with this cnic exists");
                            break;
                        }
                    }
                    break;
                }
                case "X": {
                    System.out.println("Enter Account number to check account's info");
                    String accNum = input.next();
                    Scanner scanner = null;
                    try {
                        scanner = new Scanner(FILE3);
                        int lineNum = 0;
                        String line = null;
                        while (scanner.hasNextLine()) {
                            line = scanner.nextLine();
                            if (line.contains(accNum)) {
                                System.out.println(line);
                                break;
                            }
                            lineNum++;}
                    }
                    catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
//                    for (Account a : bank.getAccounts()) {
//                        if (accNum.equals(a.getAccNumber())) {
//                            System.out.println("Account found");
//                            System.out.println(a);
//                        } else {
//                            System.out.println("No account with this Account number exists");
//                        }
//                    }
                    break;
                }


                case "F": {
                    System.exit(0);
                }


                default:
                    System.out.println("Enter valid input");
            }
        }


    }


    private static void loadPeople(File FILE1,String cnic) {
//        try {
//            File file = new File("src/Persons.txt");
//            Scanner sc = new Scanner(file);
//
//// read() method : reading and printing Characters
//            // one by one
//            while (sc.hasNextLine()) {
//                String l = sc.nextLine();
//                String st[] = l.split(" ");
//                Person p = new Person(st[0], st[1], st[2]);
//                persons.add(p);
//                System.out.println(l);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Scanner scanner = null;
        try {
            scanner = new Scanner(FILE1);
            int lineNum = 0;
            String line = null;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains(cnic)) {
                    System.out.println(line);
                    break;
                }
                lineNum++;}
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // write
        for (int i = 0; i < persons.size(); i++) {
            try {
                FileWriter fileWriter1 = new FileWriter(FILE1, true);
//                BufferedWriter br = new BufferedWriter(fileWriter1);
                fileWriter1.write(String.valueOf(persons.get(i)));
                fileWriter1.write("\n");
                System.out.println("Successfully added");
//                br.close();
                fileWriter1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void loadAccounts(float balance, Client c,File FILE3) {

        // write
        for (int i = 0; i < persons.size(); i++) {
            if (Objects.equals(c.person_details.getCNIC(), persons.get(i).getCNIC())) {
                try {
                    FileWriter fileWriter3 = new FileWriter(FILE3, true);
                    fileWriter3.write(String.valueOf(c));
                    fileWriter3.write("\n");
                    System.out.println("Successfully added");
                    fileWriter3.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static Bank loadBank(Bank bank ,File FILE4) {

        // write
        try {
            FileWriter fileWriter4 = new FileWriter(FILE4, true);
//                System.out.println(persons.get(i).getCNIC());
            fileWriter4.write(String.valueOf(bank));
            System.out.println("Successfully added");
            fileWriter4.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void loadCustomers(String cnic,File FILE2) {
        int a;
        try {
            a = 0;
            Scanner scanner = null;
            scanner = new Scanner(FILE2);
            int lineNum = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;
                if (line.contains(cnic)) {
                    a++;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (a == 0) {
            for (int i = 0; i < persons.size(); i++) {
                if (Objects.equals(cnic, persons.get(i).getCNIC())) {
                    try {
                        FileWriter fileWriter2 = new FileWriter(FILE2, true);
                        fileWriter2.write(String.valueOf(persons.get(i)));
                        fileWriter2.write("\n");
                        System.out.println("Successfully added");
                        fileWriter2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
