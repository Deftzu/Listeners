import kotlin.concurrent.thread
fun main() {
    // Employe
    val commis1 = Commis(1, null)
    val souschef1 = SousChef(1, commis1)
    val chef1 = Chef(1, souschef1)
    val serveur1 = Serveur(1, chef1)

    // Client
    val c1 = Client("Doryan")
    c1.request("Pizza")
}
data class Request(val plat: String, val idClient: Int)
class Client(val name: String){
    fun request(plat: String) = Request(plat, 1)
}

abstract class Employe {
    abstract var subaltern: Employe?
    abstract fun onRequestReceived(request: Request)
}

class Chef(val id: Int, override var subaltern: Employe?) : Employe(){
    override fun onRequestReceived(request: Request) {
        print("Chef number : $id is cooking ${request.plat}")
        Thread.sleep(1000)
        print("Fait")
    }
}

class SousChef(val id: Int, override var subaltern: Employe?) : Employe(){
    override fun onRequestReceived(request: Request) {
        print("Sous Chef number : $id is cooking ${request.plat}")
        Thread.sleep(1000)
        print("Fait")
    }
}

class Serveur(val id: Int, override var subaltern: Employe?) : Employe() {
    override fun onRequestReceived(request: Request) {
        print("Sous Chef number : $id is cooking ${request.plat}")
        Thread.sleep(1000)
        print("Fait")
    }
}

class Commis(val id: Int, override var subaltern: Employe?) : Employe(){
    override fun onRequestReceived(request: Request) {
        print("Sous Chef number : $id is cooking ${request.plat}")
        Thread.sleep(1000)
        print("Fait")
    }
}