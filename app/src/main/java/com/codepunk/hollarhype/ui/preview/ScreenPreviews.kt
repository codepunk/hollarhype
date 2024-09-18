package com.codepunk.hollarhype.ui.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

private const val PIXEL_8 = "id:pixel_8_pro"
private const val PIXEL_8_PRO_LANDSCAPE =
    "spec:id=reference_phone,shape=Normal,width=998,height=448,unit=dp,dpi=420"
private const val PIXEL_5_LANDSCAPE =
    "spec:id=reference_phone,shape=Normal,width=851,height=393,unit=dp,dpi=440"
private const val PIXEL_TABLET_PORTRAIT =
    "spec:id=reference_tablet,shape=Normal,width=800,height=1280,unit=dp,dpi=276"

// Pixel 8 Pro

@Preview(
    name = "Pixel 8 Pro",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = PIXEL_8,
    group = "Light Mode"
)
@Preview(
    name = "Pixel 8 Pro Landscape",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = PIXEL_8_PRO_LANDSCAPE,
    group = "Light Mode"
)
@Preview(
    name = "Pixel 8 Pro Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = PIXEL_8,
    group = "Dark Mode"
)
@Preview(
    name = "Pixel 8 Pro Landscape Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = PIXEL_8_PRO_LANDSCAPE,
    group = "Dark Mode"
)

// Pixel 5

@Preview(
    name = "Pixel 5",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.PIXEL_5,
    group = "Light Mode"
)
@Preview(
    name = "Pixel 5 Landscape",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = PIXEL_5_LANDSCAPE,
    group = "Light Mode"
)
@Preview(
    name = "Pixel 5 Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_5,
    group = "Dark Mode"
)
@Preview(
    name = "Pixel 5 Landscape Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = PIXEL_5_LANDSCAPE,
    group = "Dark Mode"
)

// Pixel Tablet

@Preview(
    name = "Pixel Tablet Portrait",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = PIXEL_TABLET_PORTRAIT,
    group = "Light Mode"
)
@Preview(
    name = "Pixel Tablet",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.PIXEL_TABLET,
    group = "Light Mode"
)
@Preview(
    name = "Pixel Tablet Portrait Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = PIXEL_TABLET_PORTRAIT,
    group = "Dark Mode"
)
@Preview(
    name = "Pixel Tablet Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_TABLET,
    group = "Dark Mode"
)
annotation class ScreenPreviews