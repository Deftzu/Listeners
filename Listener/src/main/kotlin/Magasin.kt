import kotlin.concurrent.thread

fun main() {
    val shop= Shop()
    // Créer un garde, un doorman, et deux caissier
    val guard1 = Guard(1)
    val doorman1 = Doorman(2)
    val cashier1 = Cashier(3)

    shop.addListener(guard1)
    shop.addListener(doorman1)
    shop.addListener(cashier1)

    // Ouverture de la boutique
    // un client arrive porte 1
    shop.openDoor(1)

    // 5 secondes après un client arrive porte 2
    Thread.sleep(5000)
    shop.openDoor(2)

    // 10 secondes après un client arrive porte 3
    Thread.sleep(5000)
    shop.openDoor(3)
}

interface DoorListener {
    fun onDoorOpened(door: Door)
    fun onDoorClosed(door: Door)
}

class Door(val id: Int) {
    val listeners = mutableListOf<DoorListener>()
    fun open() {
        println("Door $id opened")
        for (listener in listeners){
            listener.onDoorOpened(this)
        }
    }
    fun close() {
        println("Door $id closed")
        for (listener in listeners){
            listener.onDoorClosed(this)
        }
    }
    fun addListener(person: Person) {
        listeners.add(person)
    }
    fun removeListener(person: Person) {
        listeners.remove(person)
    }
}

class Shop {
    val doors = listOf(Door(1), Door(2), Door(3))

    fun openDoors() {
        for (door in doors) {
            door.open()
        }
    }

    fun closeDoors() {
        for (door in doors) {
            door.close()
        }
    }

    fun openDoor(id: Int) {
        doors.first { it.id == id }.open()
    }

    fun closeDoor(id: Int) {
        doors.first { it.id == id }.close()
    }

}

interface Person {
    fun onDoorOpened(door: Door)
    fun onDoorClosed(door: Door)
}

class Guard(val id: Int) : Person{
    //doit être alerté à chaque ouverture de porte, ne fait rien lors de la ferture d'une porte
    override fun onDoorOpened(door: Door) {
        print("Le guardian $id à été alerté que la porte ${door.id} à été ouverte")
    }

    override fun onDoorClosed(door: Door) {
        // ne fait rien
    }
}
// class Doorman : doit fermer la porte 5 secondes après l'ouverture
class Doorman(val id: Int) : Person{

    override fun onDoorOpened(door: Door) {
        Thread.sleep(500)
        print("Le doorman $id ferme la porte ${door.id}")
        door.close()
    }

    override fun onDoorClosed(door: Door) {
        // ne fait rien
    }
}
// class Cashier : doit dire bonjour à chaque ouverture de porte et doit dire au revoir à chaque fermeture
class Cashier(val id: Int) : Person{

    override fun onDoorOpened(door: Door) {
        println("Le caissier $id dit : Bonjour")
    }

    override fun onDoorClosed(door: Door) {
        println("Le caissier $id dit : Au revoir")
    }

}

