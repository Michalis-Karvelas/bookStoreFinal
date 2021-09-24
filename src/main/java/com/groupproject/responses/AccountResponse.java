package com.groupproject.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groupproject.entities.Account;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponse extends Response{

    private List<Account> accounts;
    private Account account;
    private Long accountId;

    public AccountResponse(String msg, List<Account> accounts){
        super(msg);
        this.accounts=accounts;
    }

    public AccountResponse(String msg, Account account){
        super(msg);
        this.account=account;
    }

    public AccountResponse(String msg, Long accountId) {
        super(msg);
        this.accountId = accountId;
    }
}
