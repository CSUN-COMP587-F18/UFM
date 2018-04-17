package ufm.universalfinancemanager.addeditbudget;

import android.support.annotation.Nullable;

import java.util.List;

import ufm.universalfinancemanager.BasePresenter;
import ufm.universalfinancemanager.BaseView;
import ufm.universalfinancemanager.support.Flow;
import ufm.universalfinancemanager.support.atomic.Category;

/**
 * Created by Areeba on 2/23/2018.
 */

public interface AddEditBudgetContract {
    interface View extends BaseView<AddEditBudgetContract.Presenter> {

        void showLastActivity(boolean success);

        void showMessage(String message);
        void updateCategories(@Nullable List<Category> categories);
    }

    interface Presenter extends BasePresenter<AddEditBudgetContract.View> {
        void makeBudget(String name, String category, double amount);
        void loadTransactions(String name, String category, double amount);

        void deleteBudget();

        void takeView(AddEditBudgetContract.View v);

        void dropView();
    }
}
