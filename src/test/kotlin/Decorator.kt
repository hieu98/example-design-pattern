import org.junit.jupiter.api.Test

interface CoffeeMachine {
    fun makeSmallCoffee()
    fun makeLargeCoffee()
}

class NormalCoffeeMachine : CoffeeMachine{
    override fun makeSmallCoffee() {
        println("Normal coffee machine: Making small coffee")
    }

    override fun makeLargeCoffee() {
        println("Normal coffee machine: Making large coffee")
    }
}

class EnhancedCoffeeMachine(private val coffeeMachine: CoffeeMachine) : CoffeeMachine  by coffeeMachine {
    override fun makeLargeCoffee() {
        println("Enhanced Coffee machine: Making large coffee")
    }

    fun makingMilkCoffee() {
        println("Enhanced coffee machine: Making milk coffee")
        coffeeMachine.makeSmallCoffee()
        println("Enhanced coffee machine: Adding milk")
    }
}

class DecoratorTest {
    @Test
    fun testDecorator() {
        val normalCoffeeMachine = NormalCoffeeMachine()
        val enhancedCoffeeMachine = EnhancedCoffeeMachine(normalCoffeeMachine)

        enhancedCoffeeMachine.makeSmallCoffee()
        println("-----------------------------")
        enhancedCoffeeMachine.makeLargeCoffee()
        println("-----------------------------")
        enhancedCoffeeMachine.makingMilkCoffee()
    }
}