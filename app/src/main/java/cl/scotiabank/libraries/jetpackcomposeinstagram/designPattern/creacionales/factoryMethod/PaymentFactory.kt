package cl.scotiabank.libraries.jetpackcomposeinstagram.designPattern.creacionales.factoryMethod

import cl.scotiabank.libraries.jetpackcomposeinstagram.designPattern.creacionales.factoryMethod.TypePayment.CARD
import cl.scotiabank.libraries.jetpackcomposeinstagram.designPattern.creacionales.factoryMethod.TypePayment.GOOGLEPAY

class PaymentFactory {

    companion object{
        fun buildPayment(typePayment: TypePayment): Payment{
            return when(typePayment){
                CARD -> CardPayment()
                GOOGLEPAY -> GooglePayment()
            }
        }
    }
}
