package com.aihomework.floating_window

import android.app.Application
import android.content.Context
import android.view.WindowManager
import androidx.compose.material3.Text
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowSettings

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class ComposeFloatingWindowTest {

    private lateinit var context: Context
    private lateinit var composeFloatingWindow: ComposeFloatingWindow

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext<Application>()
        ShadowSettings.setCanDrawOverlays(true)
        composeFloatingWindow = ComposeFloatingWindow(context)
    }

    @Test
    fun `window is shown and content is set`() {
        // Given
        composeFloatingWindow.setContent {
            Text("Hello")
        }

        // When
        composeFloatingWindow.show()

        // Then
        assert(composeFloatingWindow.decorView.parent != null)
        assert(composeFloatingWindow.decorView.childCount == 1)
        assert(composeFloatingWindow.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED))
    }

    @Test
    fun `window is hidden`() {
        // Given
        composeFloatingWindow.setContent {
            Text("Hello")
        }
        composeFloatingWindow.show()

        // When
        composeFloatingWindow.hide()

        // Then
        assert(composeFloatingWindow.decorView.parent == null)
        assert(composeFloatingWindow.lifecycle.currentState == Lifecycle.State.CREATED)
    }

    @Test
    fun `update moves the window`() {
        // Given
        composeFloatingWindow.setContent { Text("Test") }
        composeFloatingWindow.show()

        val initialX = composeFloatingWindow.windowParams.x
        val initialY = composeFloatingWindow.windowParams.y

        // When
        composeFloatingWindow.windowParams.x += 100
        composeFloatingWindow.windowParams.y += 100
        composeFloatingWindow.update()

        // Then
        val latestParams = composeFloatingWindow.decorView.layoutParams as WindowManager.LayoutParams
        assert(latestParams.x == initialX + 100)
        assert(latestParams.y == initialY + 100)
    }
}
