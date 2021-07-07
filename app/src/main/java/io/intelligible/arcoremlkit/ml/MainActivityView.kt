
package io.intelligible.arcoremlkit.ml

import android.opengl.GLSurfaceView
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import io.intelligible.arcoremlkit.R
import io.intelligible.arcoremlkit.common.helpers.SnackbarHelper
import io.intelligible.arcoremlkit.common.samplerender.SampleRender

/**
 * Wraps [R.layout.activity_main] and controls lifecycle operations for [GLSurfaceView].
 */
class MainActivityView(val activity: MainActivity, renderer: AppRenderer) : DefaultLifecycleObserver {
  val root = View.inflate(activity, R.layout.activity_main, null)
  val surfaceView = root.findViewById<GLSurfaceView>(R.id.surfaceview).apply {
    SampleRender(this, renderer, activity.assets)
  }
  val useCloudMlSwitch = root.findViewById<SwitchCompat>(R.id.useCloudMlSwitch)
  val scanButton = root.findViewById<TextView>(R.id.scanButton)
  val resetButton = root.findViewById<TextView>(R.id.clearButton)
  val snackbarHelper = SnackbarHelper().apply {
    setParentView(root.findViewById(R.id.coordinatorLayout))
    setMaxLines(6)
  }

  override fun onResume(owner: LifecycleOwner) {
    surfaceView.onResume()
  }

  override fun onPause(owner: LifecycleOwner) {
    surfaceView.onPause()
  }

  fun post(action: Runnable) = root.post(action)

  /**
   * Toggles the scan button depending on if scanning is in progress.
   */
  fun setScanningActive(active: Boolean) = when(active) {
    true -> {
      scanButton.isEnabled = false
      scanButton.setText("Scanning")
    }
    false -> {
      scanButton.isEnabled = true
      scanButton.setText("Scanning")
    }
  }
}