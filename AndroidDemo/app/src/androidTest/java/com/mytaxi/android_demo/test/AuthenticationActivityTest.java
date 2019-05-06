package com.mytaxi.android_demo.test;

import android.app.Activity;
import android.content.Intent;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.activities.AuthenticationActivity;
import com.mytaxi.android_demo.utils.EspressoIdlingResource;

import org.junit.Rule;
import org.junit.runner.RunWith;

import cucumber.api.java.Before;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class AuthenticationActivityTest {

    @Rule
    public ActivityTestRule<AuthenticationActivity> authActivityRule = new ActivityTestRule<>(AuthenticationActivity.class);

    private Activity activity;

    @Before("@login-feature")
    public void setUp() throws Exception {
        // Before test case execution.
        authActivityRule.launchActivity(new Intent());
        activity = authActivityRule.getActivity();

        // Add idling to network calls.
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    @Given("^I am on login screen$")
    public void i_am_on_login_screen() throws Throwable {
        assertNotNull(activity);
    }

    @When("^I enter email (.*)$")
    public void i_enter_email(String username) throws Throwable {
        onView(withId(R.id.edt_username)).perform(typeText(username));
        closeSoftKeyboard();
    }

    @And("^I enter password (.*)$")
    public void i_enter_password(String password) throws Throwable {
        onView(withId(R.id.edt_password)).perform(typeText(password));
        closeSoftKeyboard();
    }

    @And("^I click on login button$")
    public void i_click_on_login_button() throws Throwable {
        onView(withId(R.id.btn_login)).perform(click());
    }

    @Then("^I should not see error message$")
    public void i_should_not_see_error_message() throws Throwable {
        assertTrue(activity.isFinishing());
    }

    @Then("^I should see error message$")
    public void i_should_see_error_message() throws Throwable {
        onView(withId(android.R.id.content)).check(matches(isDisplayed()));
    }

    /*@Test
    public void loginSuccessfulTest() throws Exception {
        username = "crazydog335";
        password = "venture";
        // Enter username input.
        onView(ViewMatchers.withId(R.id.edt_username)).perform(typeText(username));
        // Enter password input.
        onView(withId(R.id.edt_password)).perform(typeText(password));
        // Close soft keyboard.
        closeSoftKeyboard();
        // Click on login button.
        onView(withId(R.id.btn_login)).perform(click());
    }*/

    /*@Test
    public void loginFailedTest() throws Exception {
        username = "test";
        password = "test1234";
        // Enter username input.
        onView(withId(R.id.edt_username)).perform(typeText(username));
        // Enter password input.
        onView(withId(R.id.edt_password)).perform(typeText(password));
        // Close soft keyboard.
        closeSoftKeyboard();
        // Click on login button.
        onView(withId(R.id.btn_login)).perform(click());
        // Validate login failed.
        onView(withId(android.R.id.content)).check(matches(isDisplayed()));
    }*/

    @After("@login-feature")
    public void tearDown() throws Exception {
        // After test case execution.
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
        authActivityRule.finishActivity();
    }
}
