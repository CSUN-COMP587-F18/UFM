package ufm.universalfinancemanager.unit_tests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ufm.universalfinancemanager.addeditaccount.AddEditAccountContract;
import ufm.universalfinancemanager.addeditaccount.AddEditAccountPresenter;
import ufm.universalfinancemanager.addeditcategory.AddEditCategoryPresenter;
import ufm.universalfinancemanager.addedittransaction.AddEditTransactionContract;
import ufm.universalfinancemanager.db.UserRepository;
import ufm.universalfinancemanager.db.entity.Account;
import ufm.universalfinancemanager.networth.NetworthContract;
import ufm.universalfinancemanager.networth.NetworthPresenter;
import ufm.universalfinancemanager.support.AccountType;
import ufm.universalfinancemanager.support.Flow;
import ufm.universalfinancemanager.support.Networth;
import ufm.universalfinancemanager.support.atomic.User;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Areeba on 11/28/2018.
 */

public class TestingAccounts {

    @Mock
    private UserRepository mTransactionRepository;

    @Mock
    User mUser;

    @Mock
    private AddEditAccountContract.View view;

    @Mock
    private NetworthContract.View view2;

    AddEditAccountPresenter presenter;
    NetworthPresenter presenterNetworth;

    private String name = "CHASE";

    @Test
    public void testExistedAccountView() {
        mUser = Mockito.mock(User.class);
        presenter = new AddEditAccountPresenter(mUser, mTransactionRepository, null);
        view = Mockito.mock(AddEditAccountContract.View.class);
        presenter.takeView(view);
        presenter.saveAccount(name, 500, AccountType.CHECKING);
        verify(view).showMessage("Account successfully saved.");

    }

    @Test
    public void testNetworthTotal_ifNewAccountIsAdded() {
        mUser = Mockito.mock(User.class);
       presenterNetworth = new NetworthPresenter(mUser);
       view2 = Mockito.mock(NetworthContract.View.class);
       presenterNetworth.takeView(view2);
       verify(view2).showNetworth(mUser.getAccounts());
    }
}

    /*private User mUser;

    @Mock
    private UserRepository mTransactionRepository;

    private AddEditAccountPresenter mPresenter;

    private String name = "CHASE";
    private Account account;

    @Test
    public void testSavingandDeletingAccount() {
        mUser = Mockito.mock(User.class);
        Mockito.doThrow(NullPointerException.class).when(mUser);
        mPresenter = new AddEditAccountPresenter(mUser, mTransactionRepository, null);
        mPresenter.saveAccount(name, 500, AccountType.CHECKING);
        assertEquals(mUser.getAccount(name), name);

    }*/