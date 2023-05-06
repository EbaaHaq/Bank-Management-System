import java.util.ArrayList;

public class Bank {
    String name;
    private ArrayList<Client> clList = new ArrayList<>();
    private ArrayList<Account> acList = new ArrayList<>();

    public Bank(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bank{" + "name='" + name + '\'' + ", clients=" + clList +  '}';
//        ", accounts=" + acList +
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Client> getClients() {
        return clList;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clList = clients;
    }

    public ArrayList<Account> getAccounts() {
        return acList;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.acList = accounts;
    }

    public void addAccount(float amount, Client c) {
        Account a = new Account(amount,c);
        System.out.println("account is created with Account number: " + a.accNo);
        acList.add(a);
        c.addAccount(a);

    }

    public Client addClient(Person p) {
        Client c = new Client(p);
        System.out.println(c);
        clList.add(c);

        return c;
    }

    public float totalAmount() {
        float total_amount = 0;

        for (Account a:acList){
            total_amount += a.getAmount();
        }

        // returns the total amount in all the accounts of all the client combined
        return total_amount;
    }

    public Boolean removeClient(int CNIC) {
        for (Client c:clList){
            if (clList.size()!=0){
                if (CNIC==c.getId()){
                    for (Account a:c.getAccounts()){
                        acList.remove(a);
                    }
                    clList.remove(c);
                    System.out.println("Client"+c+ "removed");
                }
            }else{
                System.out.println("No clients in bank");
            }

        }
        // when client is removed all the accounts of that client from the bank are also destroyed
        return true;
    }
    public void searchAcc (String accNum){
        Boolean found = false;
        while (found==false){
            for (Account a:acList){
                if (accNum.equals(a.getAccNumber())){
                    System.out.println("Account found");
                    found=true;
                    break;

                }else{
                    System.out.println("Account not found");

                }
            }

        }

    }

    public Client searchCustomerDetail(String CNIC){
        for (Client c:clList){
            if (CNIC.equals(c.getDetails().getCNIC())){
                System.out.println("Client found");
                System.out.println(c);
            }else{
                System.out.println("Client not found");
            }
        }
        return null;
    }
}
