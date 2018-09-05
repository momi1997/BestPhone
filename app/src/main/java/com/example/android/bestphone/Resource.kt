package com.example.android.bestphone

class Resource<T> (val status : Status, val theData : T) {

    companion object {

        fun <T> success (data : T) : Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> new (data : T) : Resource<T> {
            return Resource(Status.NEW, data)
        }

        fun <T> error (data : T) : Resource<T> {
            return Resource(Status.ERROR, data)
        }
    }

    enum class Status {
        NEW,
        SUCCESS,
        ERROR
    }
}