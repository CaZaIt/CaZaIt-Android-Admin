package org.bmsk.domain.exception

class EmptyResponseBodyException: Exception("The server returned a successful response, but the body was empty.")