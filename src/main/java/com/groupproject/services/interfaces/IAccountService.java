package com.groupproject.services.interfaces;

import com.groupproject.entities.Account;
import com.groupproject.requests.AccountRequest;
import java.util.List;

public interface IAccountService {

    //list for all accounts
    List<Account> getAll();

    //account with id
    Account getAccountById(Long id);

    //find account by username
    Long getAccountByUsername(String username);

    //new account
    boolean createAccount(AccountRequest request);

    //update account with id
    Account updateAccount(Long id, AccountRequest request);
    Account updateAccount(Long id,double x);


//    //update account's coins
//    Account updateAccountCoins(Long id, AccountRequest request);

    //delete account with id
    boolean deleteById(Long id);

}
