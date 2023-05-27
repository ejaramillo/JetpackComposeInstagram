package cl.scotiabank.libraries.jetpackcomposeinstagram.login.data

import cl.scotiabank.libraries.jetpackcomposeinstagram.core.network.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(private val api: LoginService){
    //private val api = LoginService()

    suspend fun doLogin(user: String, password: String): Boolean{
        return api.doLogin(user = user, password = password)
    }
}
