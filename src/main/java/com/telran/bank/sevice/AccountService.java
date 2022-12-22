package com.telran.bank.sevice;

import com.telran.bank.entity.Account;
import com.telran.bank.exception.AccountNotFoundException;
import com.telran.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    private final List<Account> accounts = new ArrayList<>();

    public Account saveAccount (Account account){
        accounts.add(account);
        return account;
    }
    public List<Account> getAllAccounts(Map<String, String> allParams){
        List<Account> res = new ArrayList<>();
        Predicate myPredicate = new Predicate<Account>() {
            @Override
            public boolean test(Account account) {
                boolean result = true;
                for (Map.Entry<String, String> entry: allParams.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue().toLowerCase();
                    if (key.equals("id")) {result = ("," + value + ",").contains("," +
                            String.valueOf(account.getId()) + ",");}
                    if (entry.getKey().equals("email")) {result = ("," + entry.getValue() + ",").contains("," +
                            account.getEmail() + ",");}
                    if (entry.getKey().equals("creationDate")) {result = ("," + value + ",")
                            .contains("," + account.getCreationDate() + ",");}
                    if (entry.getKey().equals("firstName")) {result = ("," + value + ",")
                            .contains("," + account.getFirstName().toLowerCase() + ",");}
                    if (entry.getKey().equals("lastName")) {result = ("," + value + ",")
                            .contains("," + account.getLastName().toLowerCase() + ",");}
                    if (entry.getKey().equals("city")) {result = ("," + value + ",")
                            .contains("," + account.getCity().toLowerCase() + ",");}
                    if (entry.getKey().equals("country")) {result = ("," + value + ",")
                            .contains("," + account.getCountry().toLowerCase() + ",");}
                    if (key.equals("amountOfMoney")) {
                        System.out.println(""+value+" "+String.valueOf(account.getAmountOfMoney()).toLowerCase());
                        result = ("," + value + ",").contains("," +
                                String.valueOf(account.getAmountOfMoney()) + ",");}
                    if (result == false) break;
                }
                return result;
            }
        };

        Comparator<Account> myComp =  (a1, a2) ->{
            int sign = 1;
            if (allParams.containsKey("sort")) {
                String sortValue =allParams.get("sort");
                sign=(sortValue.contains("-")) ? -1 : 1 ;  // sort - sign
                sortValue = sortValue.replace("-","");
                if (sortValue.equals("email")) {return a1.getEmail().compareTo(a2.getEmail())*sign;};
                if (sortValue.equals("creationDate")) {return a1.getCreationDate().compareTo(a2.getCreationDate())*sign;};
                if (sortValue.equals("firstName")) {return a1.getFirstName().compareTo(a2.getFirstName())*sign;};
                if (sortValue.equals("lastName")) {return a1.getLastName().compareTo(a2.getLastName())*sign;};
                if (sortValue.equals("city")) {return a1.getCity().compareTo(a2.getCity())*sign;};
                if (sortValue.equals("country")) {return a1.getCountry().compareTo(a2.getCountry())*sign;};
            }
            return (a1.getId()>a2.getId() ? 1: a1.getId()==a2.getId() ? 0 : -1)*sign;
        };

        return  (List<Account>) accounts.stream().filter(myPredicate).sorted(myComp).collect(Collectors.toList());
    }

    public Account getAccount(Long id) {
        for (Account account : accounts) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        throw new AccountNotFoundException("Account not found");
    }
    public void transfer (String fromIdStr, String toIdStr, String amountStr){
        Account accFrom=null;
        Account accTo=null;
        Double balance = 0.0;
        for (Account account : accounts) {
            if (account.getId().equals(Long.valueOf(fromIdStr))) {
                accFrom = account;
                balance = account.getAmountOfMoney();
            }
            if (account.getId().equals(Long.valueOf(toIdStr))) {
                accTo = account;
            }
        }
        if (accFrom==null) throw new AccountNotFoundException("AccountFrom not found");
        if (accTo==null) throw new AccountNotFoundException("AccountTo not found");
        if (balance<=Double.valueOf(amountStr)) throw new AccountNotFoundException("Not enough money");
        accFrom.setAmountOfMoney(balance-Double.valueOf(amountStr));
        accTo.setAmountOfMoney(accTo.getAmountOfMoney()+Double.valueOf(amountStr));
    }
    public Account updateAccount(Long id, Account account) {
        for (Account acc : accounts) {
            if (acc.getId().equals(id)) {
                if(account.getEmail()!=null) acc.setEmail(account.getEmail());
                if(account.getFirstName()!=null) acc.setFirstName(account.getFirstName());
                if(account.getLastName()!=null) acc.setLastName(account.getLastName());
                if(account.getCity()!=null) acc.setCity(account.getCity());
                if(account.getCountry()!=null) acc.setCountry(account.getCountry());
                if(account.getAmountOfMoney()!=null) acc.setAmountOfMoney(account.getAmountOfMoney());
                return acc;
            }
        }
        throw new AccountNotFoundException("Account not found");
    }
}
