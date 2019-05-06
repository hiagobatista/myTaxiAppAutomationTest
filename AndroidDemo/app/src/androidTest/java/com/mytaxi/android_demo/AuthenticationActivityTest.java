package com.mytaxi.android_demo;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.AuthenticationActivity;
import com.mytaxi.android_demo.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AuthenticationActivityTest {

    @Rule
    public ActivityTestRule<AuthenticationActivity> authActivityRule = new ActivityTestRule<>(AuthenticationActivity.class);

    private String username;
    private String password;

    @Before
    public void setUp() throws Exception {
        // Before test case execution.
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    @Test
    public void loginSuccessfulTest() throws Exception {
        username = "crazydog335";
        password = "venture";
        // Enter username input.
        onView(withId(R.id.edt_username)).perform(typeText(username));
        // Enter password input.
        onView(withId(R.id.edt_password)).perform(typeText(password));
        // Close soft keyboard.
        closeSoftKeyboard();
        // Click on login button.
        onView(withId(R.id.btn_login)).perform(click());
    }

    @Test
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
    }

    @After
    public void tearDown() throws Exception {
        // After test case execution.
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }
}
