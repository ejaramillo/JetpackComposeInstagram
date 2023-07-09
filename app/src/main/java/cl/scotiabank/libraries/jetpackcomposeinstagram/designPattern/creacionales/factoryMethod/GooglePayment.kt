package cl.scotiabank.libraries.jetpackcomposeinstagram.designPattern.creacionales.factoryMethod

class GooglePayment: Payment {
    override fun doPayment() {
        println("Haciendo pago con Google Payment")
    }
}
