import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ComplexSystemStore (private val filePath : String) {
    private val cache : HashMap<String, String>

    init {
        println("Reading data from the file: $filePath")
        cache = HashMap()
    }

    fun store(key : String, value : String) {
        cache[key] = value
    }

    fun read(key: String) = cache[key] ?: ""

    fun commit() = println("Storing cache data to file $filePath")
}

data class User(val login: String)

class UserRepository {
    private val systemPreferences = ComplexSystemStore("/data/default.prefs")

    fun save(user: User) {
        systemPreferences.store("USER_KEY", user.login)
        systemPreferences.commit()
    }

    fun findFirst() : User = User(systemPreferences.read("USER_KEY"))
}

class FacadeTest {
    @Test
    fun testFacade() {
        val userRepo = UserRepository()
        val user = User("hieu")
        userRepo.save(user)

        val retrievedUser = userRepo.findFirst()

        Assertions.assertThat(retrievedUser.login).isEqualTo("hieu")
    }
}