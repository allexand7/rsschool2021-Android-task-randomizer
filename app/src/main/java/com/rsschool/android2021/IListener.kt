package com.rsschool.android2021

interface IListener {

    fun onActionClickForSecondFragment(min: Int, max: Int)

    fun onActionClickForFirstFragment(prevValue: Int)
}
