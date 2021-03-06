package ru.job4j.codereview;

import ru.job4j.sorting.User;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


/**
 * Не описан класс User в этом пакете.
 * Т.е. не переопределен метод equals,  compareTo и TreeMap не отсортирует себя по ключу.
 * Вообще не откомпилируется.  В остальном все вроде хорошо.
 *
 *
 */

public class Bank {

    private TreeMap<User, ArrayList<Account>> treemap = new TreeMap<>();

    public void addUser(User user) {
        this.treemap.put(user, new ArrayList<>());
    }

    public void delete(User user) {
        this.treemap.remove(user);
    }

    /**
     * Здесь из this.treemap.get(user) мы получаем  ArrayList<>()
     * если usera нет то этот код вернет null и если у него потом дернуть  get() будет npe
     * @param user
     * @param account
     */
    public void add(User user, Account account) {
        this.treemap.get(user).add(account);
    }

    private Account getActualAccount(User user, Account account) {
        ArrayList<Account> list = this.treemap.get(user);
        return list.get(list.indexOf(account));
    }

    public void deleteAccount(User user, Account account) {
        this.treemap.get(user).remove(account);
    }

    public List<Account> getAccounts(User user) {
        return this.treemap.get(user);
    }

    public boolean transfer(User user1, Account account1,
                                 User user2, Account account2, double amount) {
        return this.treemap.get(user1).contains(account1)
                && this.treemap.get(user2).contains(account2)
                && getActualAccount(user1, account1).transfer(
                getActualAccount(user2, account2), amount);
    }

    public String toString() {
        return "bank{" + "accounts=" + treemap + "}";
    }

    public static  void main(String[] args) {
        Bank bnk = new Bank();
        User usr = new User("GH", 12);
        Account acc = new Account(232, "ggg");
        bnk.addUser(usr);
        bnk.add(usr, acc);
        bnk.getAccounts(usr);
    }
}