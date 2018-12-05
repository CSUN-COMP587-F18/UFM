package ufm.universalfinancemanager.unit_tests;

/**
 * Created by Areeba on 11/27/2018.
 */
import org.junit.Test;
import org.mockito.Mock;

import ufm.universalfinancemanager.addeditaccount.AddEditAccountPresenter;
import ufm.universalfinancemanager.addeditcategory.AddEditCategoryContract;
import ufm.universalfinancemanager.addeditcategory.AddEditCategoryPresenter;
import ufm.universalfinancemanager.addeditcategory.AddEditCategoryPresenter_Factory;
import ufm.universalfinancemanager.db.UserDataSource;
import ufm.universalfinancemanager.db.UserRepository;
import ufm.universalfinancemanager.db.entity.Account;
import ufm.universalfinancemanager.db.entity.Category;
import ufm.universalfinancemanager.db.entity.Transaction;
import ufm.universalfinancemanager.support.AccountType;
import ufm.universalfinancemanager.support.Flow;
import ufm.universalfinancemanager.support.atomic.User;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class TestingCategory {

    @Mock
    private User mUser;

    @Mock
    private AddEditCategoryContract.View mView;
    private AddEditCategoryPresenter mPresenter;

    private String name = "lol";
    private Flow flow = Flow.OUTCOME;

    @Test
    public void testSavingandDeletingCategory() {
        mUser = Mockito.mock(User.class);
        mPresenter = new AddEditCategoryPresenter(mUser, null);
        mView = Mockito.mock(AddEditCategoryContract.View.class);
        mPresenter.takeView(mView);
        mPresenter.saveCategory(name, flow);

        verify(mView).showMessage("Category Added");

    }
}
