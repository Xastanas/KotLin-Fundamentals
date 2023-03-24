//Import Scanner and Time packages from Java Libraries
import java.sql.Time
import java.util.*


fun main(args: Array<String>){

    val scanner = Scanner(System.`in`)

    var startTimeString: String
    var endTimeString: String

    // Loop until valid start time is inputted
    do {
        print("Set start time (HH:MM::SS): ")
        startTimeString = scanner.nextLine()
    } while (!startTimeString.validate())

    // Split inputted starting time string into int for hours minutes and seconds
    val start = startTimeString.split(":").let {
        Time(it[0].toInt(), it[1].toInt(), it[2].toInt())
    }

    // Loop until valid end time is inputted
    do {
        print("Set end time: HH:MM:SS: ")
        endTimeString = scanner.nextLine()
    } while (!endTimeString.validate())

    // Split inputted end time string into int for hours minutes and seconds
    val stop = endTimeString.split(":").let {
        Time(it[0].toInt(), it[1].toInt(), it[2].toInt())
    }

    val diff: Time = difference(start, stop)

    // Print results with string template
    print("TIME DIFFERENCE: ${start.hours}:${start.minutes}:${start.seconds} - ")
    print("${stop.hours}:${stop.minutes}:${stop.seconds} ")
    print("= ${diff.hours}:${diff.minutes}:${diff.seconds}")
}


// function for validating time inputs
fun String.validate(): Boolean {
    val splitString = split(":")

    with(splitString) {
        if (size != 3) {
            println("Invalid time format")
            return false
        }
        if (splitString[0].toInt() !in 0..23) {
            println("Invalid hour")
            return false
        }
        // Check for valid minute
        if (splitString[1].toInt() !in 0..59) {
            println("Invalid minute")
            return false
        }
        // Check for valid second
        if (splitString[2].toInt() !in 0..59) {
            println("Invalid second")
            return false
        }
    }
    return true
}

// Function for calculating time difference
fun difference(start: Time, stop: Time): Time {
    val diff = Time(0, 0, 0)

    if (stop.seconds > start.seconds) {
        --start.minutes
        start.seconds += 60
    }

    diff.seconds = start.seconds - stop.seconds
    if (stop.minutes > start.minutes) {
        --start.hours
        start.minutes += 60
    }

    diff.minutes = start.minutes - stop.minutes
    diff.hours = start.hours - stop.hours

    return diff
}