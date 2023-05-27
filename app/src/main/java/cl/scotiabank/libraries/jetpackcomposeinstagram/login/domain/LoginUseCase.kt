package cl.scotiabank.libraries.jetpackcomposeinstagram.login.domain

import cl.scotiabank.libraries.jetpackcomposeinstagram.login.data.LoginRepository

class LoginUseCase {
    private val repository = LoginRepository()

    suspend operator fun invoke(user: String, password: String): Boolean{
        return repository.doLogin(user = user, password = password)
    }
}
