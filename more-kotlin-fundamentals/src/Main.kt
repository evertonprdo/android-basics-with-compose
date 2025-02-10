fun main() {
    val events = mutableListOf(
        Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, durationInMinutes = 0),
        Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15),
        Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30),
        Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60),
        Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10),
        Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45),
        Event(
            title = "Study Kotlin",
            description = "Commit to studying Kotlin at least 15 minutes per day.",
            daypart = Daypart.EVENING,
            durationInMinutes = 15
        )
    )
    /*  val shortEventsAmount = events.fold(0) { total, event ->
            if (event.durationInMinutes < 60) { total + 1 }
            else { total }
        }   */

    val shortEvents = events.filter { it.durationInMinutes < 60 }
    println("You have ${shortEvents.size} events")


    /*  val daypartSummary = events.fold(
            hashMapOf<Daypart, Int>(
                Daypart.MORNING to 0,
                Daypart.AFTERNOON to 0,
                Daypart.EVENING to 0,
            )
        ) { summary, event ->
            when (event.daypart) {
                Daypart.MORNING -> summary[Daypart.MORNING] = summary[Daypart.MORNING]!! + 1
                Daypart.AFTERNOON -> summary[Daypart.AFTERNOON] = summary[Daypart.AFTERNOON]!! + 1
                Daypart.EVENING -> summary[Daypart.EVENING] = summary[Daypart.EVENING]!! + 1
            }
            summary
        }   */

    val groupedEvents = events.groupBy { it.daypart }
    groupedEvents.forEach { (daypart, events) -> println("$daypart: ${events.size} events") }

    println("Last event of the day: ${events.last().title}")

    println("Duration of first event of the day: ${events[0].durationOfEvent}")
}


data class Event(val title: String, val description: String? = null, val daypart: Daypart, val durationInMinutes: Int)

enum class Daypart {
    MORNING,
    AFTERNOON,
    EVENING
}

val Event.durationOfEvent
    get() = if (durationInMinutes < 60) {
        "short"
    } else {
        "long"
    }
