package cl.scotiabank.libraries.jetpackcomposeinstagram.model

sealed class Routes(val route: String) {
    object Pantalla1: Routes(route = "pantalla1")
    object Pantalla2: Routes(route = "pantalla2")
    object Pantalla3: Routes(route = "pantalla3")
    object Pantalla4: Routes(route = "pantalla4/{age}"){
        fun createRoute(age: Int) = "pantalla4/$age"
    }
    object Pantalla5: Routes(route = "pantalla5?name={name}"){
        fun createRoute(name: String) = "pantalla5?name=$name"
    }
}