package me.pedrocaires.lab;

import java.util.Objects;

public class Main {

    public static void main(String[] args) {
//        var bankAccount1 = new BankAccount();
//        var bankAccount2 = new BankAccount();
//        var bankAccount3 = new BankAccount2();
//        bankAccount1.name = "xisDe";
//        bankAccount3.abobora = "xisDe";
//        System.out.println(bankAccount1.hashCode());
//        System.out.println(bankAccount3.hashCode());

//        var hashMapTest = new HashMap<BankAccount, String>();
//        hashMapTest.put(new BankAccount(), "pfrr");
//        hashMapTest.forEach();
//        System.out.println(hashMapTest.get(new BankAccount()));
//        var pedro = 1;
//        long* daniel = bankAccount3.deCoco;
//        System.out.println(pedro == daniel);
//        var isEqual = Objects.equals(null, null);
//        System.out.println(isEqual);
//        long time1 = System.nanoTime();
//        Long sum = 0L; // uses Long, not long
//        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
//            sum += i;
//        }
//        System.out.println(sum);
//        System.out.println(System.nanoTime() - time1 / 100000);
        var a = Status.PENDING;
        var b = Status.CONFIRMED;
        System.out.println(Objects.equals(a, b));
        //48866324825320
        //48924805076670
        //48946421837281
    }
}

enum Status{
    CONFIRMED("confirmed"), PENDING("pending");

    private final String pedro;

    Status(String pedro) {
        this.pedro = pedro;
    }

    public String getPedro() {
        return pedro;
    }

}

class BankAccount2 {
    public String abobora;
    public Long deCoco;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccount2)) return false;
        BankAccount2 that = (BankAccount2) o;
        return Objects.equals(abobora, that.abobora) && Objects.equals(deCoco, that.deCoco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(abobora, deCoco);
    }
}

class BankAccount {
    public String name;
    public Long balance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccount)) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(name, that.name) && Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, balance);
    }
}
