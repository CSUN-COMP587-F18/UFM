package ufm.universalfinancemanager.ui_test;

/**
 * Created by Areeba on 11/2/2018.
 */
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
import ufm.universalfinancemanager.networth.NetworthActivity;
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

@RunWith(AndroidJUnit4.class)
public class AddAccountScreenTest {
    @Rule
    public ActivityTestRule<NetworthActivity> mAccountActivityTestRule =
            new ActivityTestRule<NetworthActivity>(NetworthActivity.class) {
                @Override
                protected void beforeActivityLaunched() {
                    super.beforeActivityLaunched();
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

    /*Test: adds an account and checks to if it shows up on the networth page of the app*/
    @Test
    public void testClickAddBudgetMenuItem_opensAddEditUI() {
        openAddAccountMenu();
        onView(ViewMatchers.withId(R.id.name)).check(matches(isDisplayed()));
    }

    public void openAddAccountMenu() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Add Account")).perform(click());
    }

    @Test
    public void testAddAccount_isAdded() throws ParseException {


        addTestAccount("Account 2", 500, "Checking");
        onView(withText("Account 2")).check(matches(isDisplayed()));
    }

    public void addTestAccount(String name, double amount, String type) {
        openAddAccountMenu();

        //adds a name
        onView(withId(R.id.name)).perform(typeText(name),
                ViewActions.closeSoftKeyboard());

        //amount
        onView(withId(R.id.balance)).perform(typeText(Double.toString(amount)),
                ViewActions.closeSoftKeyboard());

        //account type: checking savings..
        onView(withId(R.id.accounttype)).perform(click());
        onView(withText(type)).perform(click());

        //perfroms action to submit
        onView(withId(R.id.submit_account)).perform(click());

    }

}
