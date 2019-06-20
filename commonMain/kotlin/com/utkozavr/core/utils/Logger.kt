package com.utkozavr.core.utils

import com.github.aakira.napier.Napier

class Logger private constructor(){

    companion object {

        fun getInstance(){
            initLogger()
        }

        private fun getFullMsg(message: String, ex: Exception? = null): String{

            var fullMsg: String = message

            if(ex != null){
                fullMsg = "${fullMsg}   Exception message:  ${ex.message}"
            }

            return fullMsg

        }

        fun e(message: String, ex: Exception? = null, tag: String? = null){
            Napier.e(getFullMsg(message, ex), ex, tag)
        }

        fun w(message: String, ex: Exception? = null, tag: String? = null){
            Napier.w(getFullMsg(message, ex), ex, tag)
        }

        fun i(message: String, ex: Exception? = null, tag: String? = null){
            Napier.i(getFullMsg(message, ex), ex, tag)
        }

        fun d(message: String, ex: Exception? = null, tag: String? = null){
            Napier.d(getFullMsg(message, ex), ex, tag)
        }

        fun v(message: String, ex: Exception? = null, tag: String? = null){
            Napier.v(getFullMsg(message, ex), ex, tag)
        }

    }

}