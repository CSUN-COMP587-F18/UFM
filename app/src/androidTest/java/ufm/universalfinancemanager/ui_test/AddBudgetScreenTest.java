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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ufm.universalfinancemanager.budgetoverview.BudgetActivity;
import ufm.universalfinancemanager.support.Flow;
import ufm.universalfinancemanager.ufmApplication;
import ufm.universalfinancemanager.util.EspressoIdlingResource;

import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.InstrumentationRegistry.getInstrumentation;

import ufm.universalfinancemanager.R;

import static android.support.test.espresso.Espresso.onView;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Areeba on 10/27/2018.
 */
@RunWith(AndroidJUnit4.class)
public class AddBudgetScreenTest {

    @Rule
    public ActivityTestRule<BudgetActivity> mBudgetActivityTestRule =
            new ActivityTestRule<BudgetActivity>(BudgetActivity.class) {
                @Override
                protected void beforeActivityLaunched() {
                    super.beforeActivityLaunched();
                    /*((ufmApplication) InstrumentationRegistry.getTargetContext().
                            getApplicationContext()).getTransactionRepository().deleteAllTransactions();*/
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

    @Test
    public void testClickAddBudgetMenuItem_opensAddEditUI() {
        openAddBudgetMenu();
        onView(ViewMatchers.withId(R.id.name)).check(matches(isDisplayed()));
    }

    public void openAddBudgetMenu() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Add Budget")).perform(click());
    }

    @Test
    public void testAddBudget_isAdded() throws ParseException {
        Date start = new SimpleDateFormat("mmddyy").parse("102718");
        Date end = new SimpleDateFormat("mmddyy").parse("112718");


        addTestBudget("Budget 5", "food", 10.00, start, end);
        onView(withText("Budget 5")).check(matches(isDisplayed()));
    }

    public void addTestBudget(String name, String category, double amount, Date start, Date end) {
        openAddBudgetMenu();
        onView(withId(R.id.name)).perform(typeText(name),
                ViewActions.closeSoftKeyboard());

        onView(withId(R.id.category)).perform(click());
        onView(withText(category)).perform(click());

        onView(withId(R.id.add_amount)).perform(typeText(Double.toString(amount)),
                ViewActions.closeSoftKeyboard());

        onView(withId(R.id.submit)).perform(click());

    }


}
