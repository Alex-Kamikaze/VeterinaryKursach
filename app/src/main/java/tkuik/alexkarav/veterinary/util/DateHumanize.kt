package tkuik.alexkarav.veterinary.util

import java.time.LocalDateTime
import java.util.Calendar

class DateHumanize {
    fun getCurrentTimeOfDay(): String {
        val calendar = Calendar.getInstance()
        val currentHour = LocalDateTime.of(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            calendar.get(Calendar.SECOND)
        ).hour
        when (currentHour) {
            in 0..6 -> {
                return "Доброй ночи"
            }
            in 7..12 -> {
                return "Доброго утра"
            }
            in 13..18 -> {
                return "Доброго дня"
            }
            in 19..23 -> {
                return "Доброго вечера"
            }
        }
        return "Доброго времени суток"
    }
}