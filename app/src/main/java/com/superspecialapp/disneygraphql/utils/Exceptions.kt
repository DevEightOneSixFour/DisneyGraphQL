package com.superspecialapp.disneygraphql.utils

open class CustomException: Exception() {
    open val msg = "Error"
}
class EmptyResponseException(override val msg: String = "Empty Query"): CustomException()
class FailedResponseException(override val msg: String = "Failed Network Call"): CustomException()