package ufm.universalfinancemanager;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by Areeba on 10/8/2018.
 */
@RunWith(AndroidJUnit4.class)
public class AppContextTest {
    @Test

    //Test the Context of  the Application
    public void appContextTest() {
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("ufm.universalfinancemanager", appContext.getPackageName());
    }
}
