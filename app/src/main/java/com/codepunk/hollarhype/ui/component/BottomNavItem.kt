package com.codepunk.hollarhype.ui.component

import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.ui.Route

sealed class BottomNavItem(
    val route: Route,
    val iconRes: Int,
    val labelRes: Int
) {
    data object Activity: BottomNavItem(
        route = Route.Activity,
        iconRes = R.drawable.ic_activity_black_24,
        labelRes= R.string.activity
    )

    data object GoLive: BottomNavItem(
        route = Route.GoLive,
        iconRes = R.drawable.ic_go_live_black_24,
        labelRes = R.string.go_live
    )

    data object Hype: BottomNavItem(
        route = Route.Hype,
        iconRes = R.drawable.ic_hype_black_24,
        labelRes = R.string.hype
    )

    data object Groups: BottomNavItem(
        route = Route.Groups,
        iconRes = R.drawable.ic_groups_black_24,
        labelRes = R.string.groups
    )
}