import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.random.Random


fun main() {
    println("Empezamos aqui")


    for (i in 0..5) {
        GlobalScope.launch {
            funcionEnCorrutina(i);
        }
    }

    // runblocking --> lanza la corrutina y hasta no acabe no empieza la siguiente de forma interna
    println("Empieza el runblocking")
    runBlocking {
        funcionEnCorrutina(99)  // hasta que el 99 no acaba el proceso de la corrutina
    }
    println("finaliza el runblocking")


    println("Esperamos un rato")
    Thread.sleep(20000)
    println("Termino aqui")

}


//suspend indica que esta finción se va a ejecutar en una corrutina
private suspend fun funcionEnCorrutina(i : Int){
    println("Soy una coroutine $i y me estoy ejecutandome en 2º plano")
    delay(Random.nextLong(1000,3000))
    println("Soy una corrutina $i y he terminado mi trabajo")
}