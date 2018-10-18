package ufm.universalfinancemanager.ui_test;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ufm.universalfinancemanager.R;
import ufm.universalfinancemanager.di.ActivityBindingModule_NetworthActivity;
import ufm.universalfinancemanager.networth.NetworthActivity;
import ufm.universalfinancemanager.transactionhistory.TransactionHistoryActivity;
import ufm.universalfinancemanager.ufmApplication;
import ufm.universalfinancemanager.util.EspressoIdlingResource;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Areeba on 10/17/2018.
 */
@RunWith(AndroidJUnit4.class)
public class AddTransactionScreenTest {

    @Rule
    public ActivityTestRule<TransactionHistoryActivity> mTransactionActivityTestRule =
            new ActivityTestRule<TransactionHistoryActivity>(TransactionHistoryActivity.class) {
                @Override
                protected void beforeActivityLaunched() {
                    super.beforeActivityLaunched();
                    ((ufmApplication) InstrumentationRegistry.getTargetContext().
                            getApplicationContext()).getTransactionRepository().deleteAllTransactions();
                }
            };

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }

    //tests the add transaction name
    @Test
    public void testClickAddTransactionMenuItem_opensAddEditUI() {
        openAddTransactionMenu();
        onView(ViewMatchers.withId(R.id.name)).check(matches(isDisplayed()));
    }

    //opens up the add transaction page from the menu
    public void openAddTransactionMenu() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Add Transaction")).perform(click());
    }
}
