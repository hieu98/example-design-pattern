import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

interface DataSource

class DatabaseDatasource : DataSource

class NetworkDatasource : DataSource

abstract class DataSourceFactory {
    abstract fun makeDataSource() : DataSource

    companion object {
        inline fun <reified T: DataSource> createFactory() : DataSourceFactory =
            when(T::class){
                DatabaseDatasource::class -> DatabaseFactory()
                NetworkDatasource::class -> NetworkFactory()
                else -> throw IllegalArgumentException()
            }
    }
}

class NetworkFactory : DataSourceFactory() {
    override fun makeDataSource(): DataSource = NetworkDatasource()
}

class DatabaseFactory : DataSourceFactory() {
    override fun makeDataSource(): DataSource = DatabaseDatasource()
}


class AbstractFactoryTest {
    @Test
    fun afTest() {
        val dataSourceFactory = DataSourceFactory.createFactory<NetworkDatasource>()
        val dataSource = dataSourceFactory.makeDataSource()
        println("Create datasource: $dataSource")
        Assertions.assertThat(dataSource).isInstanceOf(NetworkDatasource::class.java)
    }
}