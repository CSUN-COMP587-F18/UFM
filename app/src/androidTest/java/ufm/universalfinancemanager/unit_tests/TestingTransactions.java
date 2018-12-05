package ufm.universalfinancemanager.unit_tests;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Date;

import javax.inject.Inject;

import ufm.universalfinancemanager.addedittransaction.AddEditTransactionContract;
import ufm.universalfinancemanager.addedittransaction.AddEditTransactionPresenter;
import ufm.universalfinancemanager.db.UserDataSource;
import ufm.universalfinancemanager.db.UserRepository;
import ufm.universalfinancemanager.db.entity.Transaction;
import ufm.universalfinancemanager.support.Flow;
import ufm.universalfinancemanager.support.atomic.User;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Areeba on 11/22/2018.
 */

public class TestingTransactions {

    @Mock
    private UserRepository mTransactionRepository;

    @Mock
    User mUser;

    @Mock
    private AddEditTransactionContract.View mView;


    private AddEditTransactionPresenter mPresenter;

    private String TRANSACTION_NAME = "TEST TRANSACTION 3";
    private Flow TRANSACTION_FLOW = Flow.OUTCOME;
    private double TRANSACTION_AMOUNT = 2.00;
    private String TRANSACTION_CATEGORY = "food";
    private String TRANSACTION_FROMACCOUNT = "Checking";
    private Date TRANSACTION_DATE = new Date();

    @Test
    public void testSaveandDeleteTransaction() {
        mUser = Mockito.mock(User.class);
        mTransactionRepository = Mockito.mock(UserRepository.class);
        mPresenter = new AddEditTransactionPresenter(mTransactionRepository, mUser, "1");
        mView = Mockito.mock(AddEditTransactionContract.View.class);
        mPresenter.takeView(mView);
        mPresenter.saveTransaction(TRANSACTION_NAME, TRANSACTION_FLOW, TRANSACTION_AMOUNT, TRANSACTION_CATEGORY, TRANSACTION_FROMACCOUNT, null,  TRANSACTION_DATE, "jhger");

        verify(mView).showMessage("Transaction Added");

        mPresenter.deleteTransaction();
        verify(mView).showLastActivity(true);

    }
}
