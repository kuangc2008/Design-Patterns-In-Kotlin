import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

//Based on: http://stackoverflow.com/a/13030163/361832

interface Plant

class OrangePlant : Plant

class ApplePlant : Plant

abstract class PlantFactory {

    abstract fun makePlant(): Plant

    companion object {
        inline fun <reified T : Plant> createFactory(): PlantFactory =
            when (T::class) {  //inline函数中，可以使用reified，这样T可以使用class
                OrangePlant::class -> OrangeFactory()
                ApplePlant::class -> AppleFactory()
                else -> throw IllegalArgumentException()
            }
    }
}

class AppleFactory : PlantFactory() {
    override fun makePlant(): Plant = ApplePlant()
}

class OrangeFactory : PlantFactory() {
    override fun makePlant(): Plant = OrangePlant()
}


class AbstractFactoryTest {

    @Test
    fun `Abstract Factory`() {
        val plantFactory = PlantFactory.createFactory<OrangePlant>()   // 使用的话，将它添加到泛型即可
        val plant = plantFactory.makePlant()
        println("Created plant: $plant")

        assertThat(plant).isInstanceOf(OrangePlant::class.java)
    }
}
