package ufm.universalfinancemanager.ui_test;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.DatePicker;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ufm.universalfinancemanager.R;
import ufm.universalfinancemanager.reminderhistory.ReminderHistoryActivity;
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

/**
 * Created by Areeba on 11/18/2018.
 */
@RunWith(AndroidJUnit4.class)
public class AddReminderScreenTest {

    @Rule
    public ActivityTestRule<ReminderHistoryActivity> mReminderActivityTestRule =
            new ActivityTestRule<ReminderHistoryActivity>(ReminderHistoryActivity.class) {
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
    public void testClickAddReminderMenuItem_opensAddEditUI() {
        openAddReminderMenu();
        onView(ViewMatchers.withId(R.id.name)).check(matches(isDisplayed()));
    }

    @Test
    public void testAddReminder_isAdded() {
        addTestReminder("Reminder 2");
        onView(withText("Reminder 2")).check(matches(isDisplayed()));
    }

    public void addTestReminder(String name) {
        onView(withId(R.id.label)).perform(typeText(name),
                ViewActions.closeSoftKeyboard());

        onView(withId(R.id.timePicker)).perform(PickerActions.setDate(2018, 11 + 1, 4));
        onView(withId(R.id.submit_account)).perform(click());

    }

    public void openAddReminderMenu() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Add Reminder")).perform(click());
    }


}
