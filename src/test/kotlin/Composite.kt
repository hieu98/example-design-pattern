import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

open class Equipment (
    open val price : Int,
    val name : String
)

open class Composite(name: String) : Equipment(0, name) {
    private val equipments = ArrayList<Equipment>()

    override val price : Int
        get() = equipments.map {it.price}.sum()

    fun add(equipment: Equipment) = apply { equipments.add(equipment) }
}

class Computer : Composite("PC")
class Processor : Equipment(1000, "Processor")
class HardDrive : Equipment(250,"Hard Driver")
class Memory : Composite("Memory")
class ROM : Equipment(100,"ROM")
class RAM : Equipment(75, "RAM")

class CompositeTest{
    @Test
    fun testComposite(){
        val memory = Memory()
            .add(ROM())
            .add(RAM())
        val pc = Computer()
            .add(Processor())
            .add(HardDrive())
            .add(memory)
        println("PC price: ${pc.price}")

        Assertions.assertThat(pc.name).isEqualTo("PC")
        Assertions.assertThat(pc.price).isEqualTo(1425)
    }
}