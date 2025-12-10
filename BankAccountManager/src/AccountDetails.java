public class AccountDetails {

    private String name;
    private double balance;

    public AccountDetails(String name, double balance){
        this.name = name;
        this.balance = balance;
    }

    public String getName(){
        return this.name;
    }
    public double getBalance(){
        return this.balance;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }

}
