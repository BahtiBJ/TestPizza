package com.bbj.testpizza.view

sealed class StateModel<out T>  {

    class Success<T>(val data : T) : StateModel<T>()

    object Loading : StateModel<Nothing>()

    class Error(val error : Throwable)  : StateModel<Nothing>()

}