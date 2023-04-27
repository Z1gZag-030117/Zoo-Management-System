package po;

public class Admin {
    private String account;   //账号
    private int ID;                 //自增ID
    private String name;           //姓名
    private String password;      //密码
    private String telephone;
    private String  identity;




    public void setAccountNumber(String accountNumber) { this.account=accountNumber; }
    void setName(String name) {
        this.name=name;
    }
    public void setPassword(String password) {
        this.password=password;
    }
    void setTelephone(String telephone){this.telephone=telephone;}
    void setidentity(String identity){this.identity=identity;}
    void setID(int ID){this.ID=ID;}


    public String getAccountNumber() { return this.account; }
    String getName() {
        return this.name;
    }
    public String getPassword() {
        return this.password;
    }
    String getTelephone(){return this.telephone;}
    String getidentity(){return this.identity;}
    public int getID(){return this.ID;}
}
