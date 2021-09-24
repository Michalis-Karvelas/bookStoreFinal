package com.groupproject.controllers;

import static java.util.Objects.isNull;

import com.groupproject.entities.Account;
import com.groupproject.repository.AccountRepository;
import com.groupproject.requests.AccountRequest;
import com.groupproject.responses.AccountResponse;
import com.groupproject.responses.Response;
import com.groupproject.services.AccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = " * ", allowedHeaders = " * ")
@RequestMapping(value="/api/account")
@RestController
public class AccountController {

    @Autowired
    private AccountServiceImpl accountServiceImpl;


    // list of all the accounts
    @GetMapping(value="/getall")
    public AccountResponse getAll(){
        log.info("Ready to find all the Accounts");
        return new AccountResponse("Found all the Accounts",accountServiceImpl.getAll());
    }

    // get account by id
    @GetMapping(value="/getbyid/{id}")
    public AccountResponse getById(@PathVariable Long id){
        log.info("Ready to find account by id");
        return new AccountResponse("Found the account",accountServiceImpl.getAccountById(id));
    }

    // get accountId by username
    @GetMapping(value="/getaccountidbyusername/{username}")
    public AccountResponse getAccountIdByUsername(@PathVariable String username){
        log.info("Ready to find accountId by username");
        if(accountServiceImpl.getAccountByUsername(username)==0L){
            log.info("There is no account with this username");
            return new AccountResponse("There is no account with this username",accountServiceImpl.getAccountByUsername(username));
        } else {
            log.info("Found the account id of this username");
            return new AccountResponse("Found the account id from this username",accountServiceImpl.getAccountByUsername(username));
        }
    }

    //create new account
    @PostMapping(value="/new",consumes = "application/json",
            produces = "application/json")
    public Response createNewAccount(@RequestBody AccountRequest request){

        log.info("Ready to create a new Account");
        accountServiceImpl.createAccount(request);
        log.info("The account has been saved");
        return new Response("The account has been saved");

    }

    // update account with id
    @PutMapping(value="/update/{id}",consumes = "application/json", produces = "application/json")
    public Response updateExistingAccount(@PathVariable(value = "id") Long id,
                                          @RequestBody AccountRequest request){
        log.info("ready to update an account");
        Account account = accountServiceImpl.updateAccount(id, request);
        if (isNull(account)) {
            return new Response("There is no such account");
        }
        return new Response("The account has been updated");
    }

    // delete account by id
    @DeleteMapping("/delete/{id}")
    public Response deleteAccount(@PathVariable Long id){
        log.info("Ready to delete an account");
        accountServiceImpl.deleteById(id);
        return new Response("The account has been deleted");
    }
}
