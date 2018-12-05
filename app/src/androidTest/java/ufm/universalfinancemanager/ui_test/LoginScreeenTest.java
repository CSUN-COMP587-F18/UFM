package ufm.universalfinancemanager.ui_test;

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

import ufm.universalfinancemanager.R;
import ufm.universalfinancemanager.budgetoverview.BudgetActivity;
import ufm.universalfinancemanager.login.LoginActivity;
import ufm.universalfinancemanager.util.EspressoIdlingResource;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Areeba on 11/21/2018.
 */
@RunWith(AndroidJUnit4.class)
public class LoginScreeenTest {
    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivityTestRule =
            new ActivityTestRule<LoginActivity>(LoginActivity.class) {
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

    @Test
    public void testLogin() {
        //signs in
        onView(withId(R.id.email_field)).perform(typeText("areebs97@gmail.com"),
                ViewActions.closeSoftKeyboard());
        onView(withId(R.id.pass_field)).perform(typeText("ufm123"),
                ViewActions.closeSoftKeyboard());

        onView(withId(R.id.login_button)).perform(click());

        //opens the main page of the app
        onView(ViewMatchers.withText("Transaction History")).check(matches(isDisplayed()));
    }
}
