package packagename

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ModuleNameTest {

    @Test
    fun createCorrectNotification() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // ...
        // assertEquals(expected, actual)
    }
}
