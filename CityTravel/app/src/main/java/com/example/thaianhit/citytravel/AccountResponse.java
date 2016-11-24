package com.example.thaianhit.citytravel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by T.N on 11/20/2016.
 */

public class AccountResponse
{
    List<Account> results;

    public List<Account> getResults() {
        return results;
    }

    public void setResults(List<Account> results) {
        this.results = results;
    }
}
