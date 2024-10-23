package es.unican.gasolineras.puntoInteres;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.not;
import static es.unican.gasolineras.utils.MockRepositories.getTestRepository;

import android.content.Context;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import dagger.hilt.android.testing.BindValue;
import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.UninstallModules;
import es.unican.gasolineras.R;
import es.unican.gasolineras.activities.main.MainView;
import es.unican.gasolineras.injection.RepositoriesModule;
import es.unican.gasolineras.repository.IGasolinerasRepository;

@UninstallModules(RepositoriesModule.class)
@RunWith(AndroidJUnit4.class)
public class AnhadirPuntoInteresUITest {

    @Rule(order = 0)  // the Hilt rule must execute first
    public HiltAndroidRule hiltRule = new HiltAndroidRule(this);

    @Rule(order = 1)
    public ActivityScenarioRule<MainView> activityRule = new ActivityScenarioRule<>(MainView.class);

    // I need the context to access resources, such as the json with test gas stations
    final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

    // Mock repository that provides data from a JSON file instead of downloading it from the internet.
    @BindValue
    final IGasolinerasRepository repository = getTestRepository(context, R.raw.gasolineras_ccaa_06);

    @Before
    public void setUp() {
        View decorView;
        activityRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());

    }

    @Test
    public void TestAnhadirPuntoInteres() {

        // Caso de exito
        openActionBarOverflowOrOptionsMenu(context);
        onView(withId(R.id.menuItemAnhadirPuntoInteres)).perform(click());
        onView(withId(R.id.textView3)).perform(click());
        onView(withId(R.id.textView3)).perform(typeText("casa"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.textView4)).perform(typeText("43.46227"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.textView5)).perform(typeText("-3.80974"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.buttonGuardar)).perform(click());
        onView(withText("Punto de interés guardado")).inRoot(RootMatchers.withDecorView(not(decorView))).check(matches(isDisplayed()));




    }

}
