package com.aydem.ui.fragments.energy_saving.list

abstract class SwipeControllerActions {
    fun onLeftClicked(position: Int) {}
    open fun onRightClicked(position: Int) {}
}