public class Account {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int id;
    private static int id_counter=0;
    public String accNo;
    public float Amount;
    private Client Acholder = new Client();

    public Account(float amount, Client c) {
        this.Amount = amount;
        Acholder.setAccounts(c.getAccounts());
        Acholder.setDetails(c.getDetails());
        this.id = id_counter++;
        this.accNo= "AC"+id;
        Acholder.totalAccounts = Acholder.totalAccountsCounter++;
    }

    @Override
    public String toString() {
        return "Account{" + "Account Number=" + accNo + ", Amount=" + Amount +", Account holder=" + Acholder.person_details.getCNIC()+ '}';
    }

    public String getAccNumber() {
        return accNo;
    }

    public String setAccNumber(String accNumber) {
        this.accNo = accNumber;
        return accNumber;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float amount) {
        this.Amount = amount;
    }

    public Client getACHolder() {
        return Acholder;
    }

    public void setACHolder(Client holder) {
        this.Acholder = holder;
    }

    float withdraw (float withdrawAmount){
        //returns remaining amount in account
        if (withdrawAmount<=this.Amount){
            this.Amount-=withdrawAmount;
            System.out.println("Amount withdrawn successfully");
            System.out.println("New balance is: " + Amount);
        }else{
            System.out.println("The amount entered is greater than current available balance");
        }
        return Amount;
    }
    float deposit (float depositAmount){
        Amount+=depositAmount;
        //adds amount in account
        return Amount;
    }
}