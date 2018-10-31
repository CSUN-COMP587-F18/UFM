package ufm.universalfinancemanager.ui_test;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Locale;

import ufm.universalfinancemanager.R;
import ufm.universalfinancemanager.di.ActivityBindingModule_NetworthActivity;
import ufm.universalfinancemanager.networth.NetworthActivity;
import ufm.universalfinancemanager.support.Flow;
import ufm.universalfinancemanager.transactionhistory.TransactionHistoryActivity;
import ufm.universalfinancemanager.ufmApplication;
import ufm.universalfinancemanager.util.EspressoIdlingResource;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

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

    @Test
    public void testAddTransaction_isAdded() {
        addTestTransaction(1, 10.00, Flow.OUTCOME,
                "food", null, "account1", null);

        onView(withText("TRANSACTION 1")).check(matches(isDisplayed()));
        //deleteTransaction("TRANSACTION 1");
    }

    public void addTestTransaction(int number, double amount, Flow flow, String category,
                                   String toAccount, String fromAccount,
                                   String notes) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy", Locale.US);

        openAddTransactionMenu();

        onView(withId(R.id.name)).perform(typeText("TRANSACTION " + number),
                ViewActions.closeSoftKeyboard());
        onView(withId(R.id.amount)).perform(typeText(Double.toString(amount)),
                ViewActions.closeSoftKeyboard());

        if (flow == Flow.INCOME) {
            onView(withId(R.id.flow_income)).perform(click());

            onView(withId(R.id.toaccount)).perform(click());
            onView(withText(toAccount)).perform(click());
        } else if (flow == Flow.OUTCOME) {
            onView(withId(R.id.flow_expense)).perform(click());

            onView(withId(R.id.fromaccount)).perform(click());
            onView(withText(fromAccount)).perform(click());
        } else if(flow == Flow.TRANSFER) {
            onView(withId(R.id.flow_transfer)).perform(click());

            onView(withId(R.id.toaccount)).perform(click());
            onView(withText(toAccount)).perform(click());
            onView(withId(R.id.fromaccount)).perform(click());
            onView(withText(fromAccount)).perform(click());
        }

        //select category from spinner
        onView(withId(R.id.category)).perform(click());
        onView(withText(category)).perform(click());

        if(notes != null)
            onView(withId(R.id.notes)).perform(typeText(notes),
                    ViewActions.closeSoftKeyboard());

        onView(withId(R.id.done)).perform(click());
    }

    @Test
    public void testDeleteTransaction_isGone() {
        addTestTransaction(1, 1.00, Flow.OUTCOME,
                "food", null, "account1", null);

        deleteTransaction("TRANSACTION 1");
        onView(withText("TRANSACTION 1")).check(matches(not(isDisplayed())));
    }

    public void deleteTransaction(String name) {
        onView(withText(name)).perform(click());
        onView(withId(R.id.name)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.cancel)).perform(click());
    }


}
