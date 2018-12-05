package ufm.universalfinancemanager.unit_tests;

import org.junit.Test;
import org.mockito.Mock;

import ufm.universalfinancemanager.addeditaccount.AddEditAccountPresenter;
import ufm.universalfinancemanager.db.UserDataSource;
import ufm.universalfinancemanager.db.UserRepository;
import ufm.universalfinancemanager.db.entity.Account;
import ufm.universalfinancemanager.db.entity.Transaction;
import ufm.universalfinancemanager.support.AccountType;
import ufm.universalfinancemanager.support.atomic.User;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by Areeba on 11/22/2018.
 */

public class TestingAccounts {

    @Mock
    private User mUser;

    @Mock
    private UserRepository mRepository;

    private AddEditAccountPresenter mPresenter;

    private String name = "BOFA";
    private double balance = 500.00;
    private AccountType accountType = AccountType.CHECKING;

    @Test
    public void testSavingandDeletingAccount() {
        mPresenter = new AddEditAccountPresenter(mUser, mRepository, null);
        mPresenter.saveAccount(name, balance, accountType);

        Account acc = mUser.getAccount(name);
        assertEquals(acc.getName(), name);
        assertEquals(acc.getBalance(), balance);
        assertEquals(acc.getType(), accountType);

        //deletes the account and all the transactions linked to it
        mPresenter.deleteAccount();

    }
}
