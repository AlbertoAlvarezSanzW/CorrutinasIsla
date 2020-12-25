import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock





suspend fun main() {


    comenzar()
    Thread.sleep(80000)


}

val mutex = Mutex()

val CUBOS_NECESARIOS = 4
val LENA_NECESARIA = 2
val RAMA_NECESARIA = 1
val COMIDA_NECESARIA = 1

var cubosActuales = 0
var lenaActual = 0
var ramasActuales = 0
var comidaActual = 0



suspend fun comenzar() {


    runBlocking {

        amigo_A()
        amigo_C()
        amigo_B()

      }
}

fun amigo_A(){

    GlobalScope.async {
        for (i in 0..3){
            println("El Amigo A, va a por un cubo de agua")
            delay(4000)
            println("El Amigo A, ha vuelto con el cubo de agua")
            Hamaca("El Amigo A",1000)
            cubosActuales = cubosActuales + 1
        }
        // madre mia ... lo que me costo sacar esto.. inicialmente lo meti en comenzar...
        if (cubosActuales == CUBOS_NECESARIOS && lenaActual == LENA_NECESARIA && ramasActuales == RAMA_NECESARIA && comidaActual == COMIDA_NECESARIA)
            println("Barca construida y aprovisionada con exito.\nPosiblemente se coman entre ellos.")


    }
}

suspend fun Hamaca(nombre: String, tiempo : Long){

    runBlocking {

        mutex.withLock {
            println("$nombre, quiere descansar")
            println("$nombre, se tumba en la hamaca")
            delay(tiempo)
            println("$nombre, se levanta de la hamaca")
            println("$nombre, deja de descansar")

        }
    }
}

suspend fun amigo_B(){

    repeat(2){
        println("El Amigo B, va a por leña")
        Hacha("El Amigo B",5000)
        lenaActual = lenaActual + 1
        Hamaca("El Amigo B",3000)

    }
}

suspend fun Hacha(nombre: String, tiempo: Long){

    runBlocking {

        mutex.withLock {
            println("$nombre, coge el hacha")
            delay(tiempo)
            println("$nombre, deja el hacha")
            println("$nombre, vuelve con la leña")
        }

    }
}

suspend fun amigo_C(){

    GlobalScope.async {
        delay(1000)
        println("El Amigo C, va a por ramas")
        println("El Amigo C, vuelve con ramas")
        delay(3000)
        ramasActuales = ramasActuales + 1
        println("El Amigo C, va a Cazar")
        Hacha("El Amigo C",4000)
        comidaActual = comidaActual + 1
    }
}
