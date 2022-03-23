package learn.bank;

import org.junit.Assert;
import org.junit.Test;

public class BankServiceTest {

    @Test
    public void addUser() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        Assert.assertEquals(bank.findByPassport("3434"), user);
    }

    @Test
    public void whenEnterInvalidPassport() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        Assert.assertNull(bank.findByRequisite("34", "5546"));
    }

    @Test
    public void addAccount() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        Assert.assertEquals(bank.findByRequisite("3434", "5546").getBalance(), 150D, 0.1);
    }

    @Test
    public void transferMoney() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        bank.addAccount(user.getPassport(), new Account("113", 50D));
        bank.transferMoney(user.getPassport(), "5546", user.getPassport(), "113", 150D);
        Assert.assertEquals(bank.findByRequisite(user.getPassport(), "113").getBalance(), 200D, 0.1);
    }

    @Test
    public void transferMoneyFalseBalance() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 149D));
        bank.addAccount(user.getPassport(), new Account("113", 50D));
        Assert.assertFalse(bank.transferMoney(user.getPassport(), "5546", user.getPassport(), "113", 150D));
    }

    @Test
    public void transferMoneyFalseDestAccount() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 345D));
        Assert.assertFalse(bank.transferMoney(user.getPassport(), "5546", user.getPassport(), "123412", 150D));
    }

    @Test
    public void transferMoneyCheckBalanceSrcAcc() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 1500D));
        bank.addAccount(user.getPassport(), new Account("5547", 150D));
        bank.transferMoney(user.getPassport(), "5546", user.getPassport(), "5547", 800D);
        Assert.assertEquals(700D, bank.findByRequisite(user.getPassport(), "5546").getBalance(), 0.1);
    }
}