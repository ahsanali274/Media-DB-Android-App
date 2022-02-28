package com.app.mediadbapp.callback

interface ItemClickListener<T> {
    fun onClickItem(item: T)
}