package com.codepunk.hollarhype.ui.screen.landing

import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.screen.auth.LandingRoute
import kotlinx.serialization.Serializable

@Serializable
enum class BottomNavItem(
    val route: LandingRoute,
    val iconRes: Int,
    val labelRes: Int,
    val contentDescriptionRes: Int
) {
    ACTIVITY(
        route = LandingRoute.Activity,
        iconRes = R.drawable.ic_activity_black_24,
        labelRes = R.string.activity,
        contentDescriptionRes = R.string.activity
    ),

    GO_LIVE(
        route = LandingRoute.GoLive,
        iconRes = R.drawable.ic_go_live_black_24,
        labelRes = R.string.go_live,
        contentDescriptionRes = R.string.go_live
    ),

    HYPE(
        route = LandingRoute.Hype,
        iconRes = R.drawable.ic_hype_black_24,
        labelRes = R.string.hype,
        contentDescriptionRes = R.string.hype
    ),

    GROUPS(
        route = LandingRoute.Groups,
        iconRes = R.drawable.ic_groups_black_24,
        labelRes = R.string.groups,
        contentDescriptionRes = R.string.groups
    )
}
