package ufm.universalfinancemanager.transactionhistory;

import java.util.List;

import ufm.universalfinancemanager.BasePresenter;
import ufm.universalfinancemanager.BaseView;
import ufm.universalfinancemanager.db.entity.Transaction;
import ufm.universalfinancemanager.support.ListItem;

/**
 * Created by smh7 on 12/11/17.
 */

public interface TransactionHistoryContract {

    interface View extends BaseView<Presenter> {
        void showTransactions(List<Transaction> items);

        void showNoTransactions();

        void showAddTask();
    }

    interface Presenter extends BasePresenter<View> {
        void result(int requestCode, int resultCode);

        void loadTransactions();

        void showAddTransaction();

        void showEditTransaction(Transaction t);

        void takeView(TransactionHistoryContract.View view);

        void dropView();
    }
}
