package com.talhadengiz.tomurcuk.ui.fragment.requirement

abstract class SwipeControllerActions {
    fun onLeftClicked(position: Int) {}
    open fun onRightClicked(position: Int) {}
}