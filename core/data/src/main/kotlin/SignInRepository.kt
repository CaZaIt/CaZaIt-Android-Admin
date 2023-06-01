//import com.bmsk.model.LoggedInUser
//
///**
// * Class that requests authentication and user information from the remote data source and
// * maintains an in-memory cache of login status and user credentials information.
// */
//
//class SignInRepository(val dataSource: datasource.SignInDataSource) {
//
//    // in-memory cache of the loggedInUser object
//    var user: LoggedInUser? = null
//        private set
//
//    val isSignIn: Boolean
//        get() = user != null
//
//    init {
//        // If user credentials will be cached in local storage, it is recommended it be encrypted
//        // @see https://developer.android.com/training/articles/keystore
//        user = null
//    }
//
//    fun signOut() {
//        user = null
//        dataSource.signOut()
//    }
//
//    fun signIn(username: String, password: String): Result<LoggedInUser> {
//        // handle login
//        val result = dataSource.signIn(username, password)
//
//        if (result is Result.Success) {
//            setLoggedInUser(result.data)
//        }
//
//        return result
//    }
//
//    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
//        this.user = loggedInUser
//        // If user credentials will be cached in local storage, it is recommended it be encrypted
//        // @see https://developer.android.com/training/articles/keystore
//    }
//}