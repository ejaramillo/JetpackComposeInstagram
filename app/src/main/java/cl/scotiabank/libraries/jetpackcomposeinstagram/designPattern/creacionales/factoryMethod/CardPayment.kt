package cl.scotiabank.libraries.jetpackcomposeinstagram.designPattern.creacionales.factoryMethod

class CardPayment : Payment{
    override fun doPayment() {
        println("Haciendo pago con Card Payment")

    }
}
