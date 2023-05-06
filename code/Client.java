import java.util.ArrayList;

public class Client {
    public int id;
    private static int id_counter;

    public Client() {
    }

    Person person_details = new Person();
    private ArrayList<Account> acList = new ArrayList<>();


    public static int totalAccountsCounter;
    public int totalAccounts;

    public Client(Person p) {
        this.id=id_counter++;
        person_details.setName(p.getName());
        person_details.setCNIC(p.getCNIC());
        person_details.setNumber(p.getNumber());


    }

    @Override
    public String toString() {
        return "Client{" + "Personal details[="+ person_details + "],\nAccount details=" + acList + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getDetails() {
        return person_details;
    }

    public void setDetails(Person details) {
        this.person_details = details;
    }

    public ArrayList<Account> getAccounts() {
        return acList;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.acList = accounts;
    }

    public int getTotalAccounts() {
        return totalAccounts;
    }

    public void setTotalAccounts(int totalAccounts) {
        this.totalAccounts = totalAccounts;
    }

    public float getTotalAmount() {
        float total = 0;
        // returns the total amount in all the accounts of any client combined
        for (Account a:acList){
            total+=a.getAmount();
        }
        return total;
    }


    void withdraw(float amount, String accNo){
        // returns the remaining amount in account
        for (Account a: acList){
            if (accNo.equals(a.getAccNumber())){
                a.Amount-=amount;
            }
        }
    }

    void deposit (float amount, String accNo){
        // returns the current amount in account
        for (Account a: acList){
            if (accNo.equals(a.getAccNumber())){
                a.Amount+=amount;
            }
        }
    }

    void addAccount (Account a){
        // adding account to accountList
        acList.add(a);
    }

    public float getTotal() {
        float t = 0;
        for(Account a: acList ){
            t= a.Amount;
        }
        return t;
    }
}

