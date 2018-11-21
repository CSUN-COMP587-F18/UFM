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

import java.text.ParseException;

import ufm.universalfinancemanager.home.HomeActivity;
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
/**
 * Created by Areeba on 11/10/2018.
 */
@RunWith(AndroidJUnit4.class)
public class AddCategoryScreenTest {
    @Rule
    public ActivityTestRule<HomeActivity> mHomeActivityTestRule =
            new ActivityTestRule<HomeActivity>(HomeActivity.class) {
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

    /*Test 1: opens the Menu UI and selects Add category*/
    @Test
    public void testClickAddCategoryMenuItem_opensAddEditUI() {
        openAddCategoryMenu();
        onView(ViewMatchers.withId(R.id.category_name)).check(matches(isDisplayed()));
    }

    public void openAddCategoryMenu() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Add Category")).perform(click());
    }

    @Test
    public void testAddCategory() {
        openAddCategoryMenu();
        onView(withId(R.id.category_name)).perform(typeText("blah"),
                ViewActions.closeSoftKeyboard());
        onView(withId(R.id.submit)).perform(click());

    }

}
