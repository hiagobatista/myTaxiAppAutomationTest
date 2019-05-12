package com.mytaxi.android_demo.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.utils.EspressoIdlingResource;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.runner.RunWith;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class DriverProfileActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> dActivityRule = new ActivityTestRule<>(MainActivity.class, true,false);

    private MainActivity mActivity = null;
    private Intent dialerScreenIntent;

    @Before("@profile-feature")
    public void setUp() throws Exception {
        // Before test case execution.
        dActivityRule.launchActivity(new Intent());
        mActivity = dActivityRule.getActivity();

        // Add idling to network calls.
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    @Given("^I navigate to driver profile$")
    public void i_navigate_to_driver_profile() throws Throwable {
        assertNotNull(dActivityRule);
        onView(withId(R.id.textSearch)).perform(typeText("sa"));
        closeSoftKeyboard();

        onView(withText("Sarah Scott"))
                .inRoot(
                        withDecorView(not(is(dActivityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
    }

    @When("^I should see (.*) info displayed$")
    public void i_should_see_driver_info_displayed(String driverName) throws Throwable {
        onView(withId(com.mytaxi.android_demo.R.id.textViewDriverName)).check(matches(withText(driverName)));
    }

    @When("^I click on call button$")
    public void i_click_on_login_button() throws Throwable {
        Intents.init();
        dialerScreenIntent = new Intent();
        Instrumentation.ActivityResult stubResult = new Instrumentation.ActivityResult(Activity.RESULT_OK, dialerScreenIntent);

        intending(hasAction(Intent.ACTION_DIAL)).respondWith(stubResult);
        onView(withId(R.id.fab)).perform(click());
    }

    @Then("^Phone dialer screen should be open displaying driver's phone (.*)$")
    public void phone_dialer_screen_should_be_open_displaying_driver_phone_number(String phoneNumber) throws Throwable {
        intended(Matchers.allOf(hasAction(Intent.ACTION_DIAL), hasData(Uri.parse("tel:" + phoneNumber))));
        Intents.release();
    }

    @After("@profile-feature")
    public void tearDown() throws Exception {
        // After test case execution.
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
        dActivityRule.finishActivity();
    }
}
