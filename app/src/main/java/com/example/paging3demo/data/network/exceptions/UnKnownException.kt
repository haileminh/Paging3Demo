package com.example.paging3demo.data.network.exceptions

import java.lang.Exception

class UnKnownException : Exception() {

    override val message: String?
        get() = "Some Unknown Error Occurred"
}