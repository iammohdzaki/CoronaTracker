package com.zaki.coronatracker.utils.navigation

import androidx.navigation.NavDirections

/**
 * Developer : Mohammad Zaki
 * Created On : 01-04-2020
 */
sealed class NavigationCommand(val id: Int = 0) {
    data class To(val directions: NavDirections) : NavigationCommand()
    object Back : NavigationCommand()
    data class BackTo(val destinationId: Int) : NavigationCommand()
    object ToRoot : NavigationCommand()
}