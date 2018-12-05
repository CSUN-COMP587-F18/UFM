package ufm.universalfinancemanager.unit_tests;

/**
 * Created by Areeba on 11/28/2018.
 */


import org.junit.Test;
import org.mockito.Mock;

import ufm.universalfinancemanager.addeditaccount.AddEditAccountPresenter;
import ufm.universalfinancemanager.addeditbudget.AddEditBudgetContract;
import ufm.universalfinancemanager.addeditbudget.AddEditBudgetPresenter;
import ufm.universalfinancemanager.db.UserDataSource;
import ufm.universalfinancemanager.db.UserRepository;
import ufm.universalfinancemanager.db.entity.Account;
import ufm.universalfinancemanager.db.entity.Transaction;
import ufm.universalfinancemanager.support.AccountType;
import ufm.universalfinancemanager.support.atomic.User;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class TestingBudget {
    @Mock
    private User mUser;

    @Mock
    private UserRepository mRepository;

    @Mock
    private AddEditBudgetContract.View mView;

    private AddEditBudgetPresenter mPresenter;

    private String name = "BOFA";
    private String cat = "food";
    private double amount = 500.00;
    Date today = new Date();

    @Test
    public void testSavingandDeletingBudget() {
        mUser = Mockito.mock(User.class);
        mRepository = Mockito.mock(UserRepository.class);
        mPresenter = new AddEditBudgetPresenter(mUser, mRepository);
        mView = Mockito.mock(AddEditBudgetContract.View.class);
        mPresenter.takeView(mView);
        mPresenter.saveBudget(name, cat, amount, today, today  );
        verify(mView).showMessage("Budget successfully saved.");
        //assertTrue(mUser.hasCategory(name));

        mPresenter.deleteBudget();

    }


    @Test
    public void testAddedTransactionsinBudgetsOverview() {

    }
}
