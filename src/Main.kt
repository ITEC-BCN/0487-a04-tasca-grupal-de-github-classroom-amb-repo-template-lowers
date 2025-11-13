import kotlin.random.Random

fun main(){
    val DAUS: String = "⚀ ⚁ ⚂ ⚃ ⚄ ⚅"
    val CARES_DAU: Array<String> = arrayOf("⚀", "⚁", "⚂", "⚃", "⚄", "⚅")

    var partides: Int?
    var tiradesPerPartida: Int?
    var guayades: Int = 0
    var perdudas: Int = 0
    var empatades: Int = 0

    println(DAUS)
    println("Benvingut/da al joc dels daus.\nPer guanyar cada partida, la suma dels punts de les teves tirades dels teus daus ha de ser superior a la de la CPU")
    println(DAUS)

    // Llegim el número de partides que volem jugar
    do {
        println("Quantes partides vols jugar? (de 1 a 3)")
        partides = readLine()?.toIntOrNull()

        if (partides != null && (partides < 1 || partides > 3)){
            partides = null
            println("ERROR: Valor no acceptat!")
        }
    }while(partides == null)

    // Llegim el número de quantes tirades volem fer per cada partida
    do {
        println("Quantes tirades vols fer per cada partida? (de 1 a 6)")
        tiradesPerPartida = readLine()?.toIntOrNull()

        if (tiradesPerPartida != null && (tiradesPerPartida < 1 || tiradesPerPartida > 6)){
            tiradesPerPartida = null
            println("ERROR: Valor no acceptat!")
        }
    }while(tiradesPerPartida == null)

    // Declarem la matriu
    var tiradesGuardades: Array<IntArray>

    // Inicialitzem la matriu de partides files i (tiradesPerPartida + 1) columnes
    tiradesGuardades = Array(partides){IntArray((tiradesPerPartida + 1)) }

    // Repetim tantes vegades com partides
    for(partida in 0 until partides) {
        var acumuladorCPU: Int = 0
        var tiradaActual: Int = 0
        var tiradaCPUNova: Int = 0

        for (tirada in 0 until tiradesGuardades[partida].size - 1) {
            println("Tira el dau! (Intent ${tirada + 1})")
            tiradaActual = Random.nextInt(1, 6 + 1)
            println("Has tret un ${CARES_DAU[tiradaActual-1]} !")

            // Guardem la tirada
            tiradesGuardades[partida][tirada] = tiradaActual

            // Acumulem el sumatori a l'última columna de la fila
            tiradesGuardades[partida][tiradesPerPartida] += tiradaActual

            // Corregit: Trobem la tirada individual primer
            tiradaCPUNova = Random.nextInt(1, 6 + 1)

            // Acumulem la tirada
            acumuladorCPU += tiradaCPUNova

            // Mostrem el resultat de la tirada individual
            println("La CPU ha tret un ${CARES_DAU[tiradaCPUNova-1]} !")
        }

        println("Partida acabada!")
        println("Tu has aconseguit ${tiradesGuardades[partida][tiradesPerPartida]} punts")
        println("La CPU ha aconseguit $acumuladorCPU punts")

        if (tiradesGuardades[partida][tiradesPerPartida] > acumuladorCPU){
            println("Has guanyat!")
            guayades++
        }else if (tiradesGuardades[partida][tiradesPerPartida] < acumuladorCPU){
            println("Has perdut!")
            perdudas++
        }else{
            println("Heu empatat!")
            empatades++
        }
    }

    val totalPartidas: Double = partides!!.toDouble()

    val percentageGuanyat = (guayades.toDouble() / totalPartidas) * 100
    val percentagePerdudas = (perdudas.toDouble() / totalPartidas) * 100
    val percentageEmpat = (empatades.toDouble() / totalPartidas) * 100

    println("Partides Totals: $partides")
    println("Guanyades: ${guayades} (${String.format("%.2f", percentageGuanyat)}%)")
    println("Perdudes: ${perdudas} (${String.format("%.2f", percentagePerdudas)}%)")
    println("Empatades: ${empatades} (${String.format("%.2f", percentageEmpat)}%)")

}