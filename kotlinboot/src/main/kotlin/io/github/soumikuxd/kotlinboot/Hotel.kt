package io.github.soumikuxd.kotlinboot

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Hotel(val name: String, val classification: Int, val roomCount: Int) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long = 0
    var freeRooms = this.roomCount

    // Check in guests to the hotel
    // Assumption is that every guest occupies a single room for the sake of simplicity
    fun checkIn(guestCount: Int) {
        if (guestCount <=  this.freeRooms) this.freeRooms = this.freeRooms.minus(guestCount)
    }

    // Check out guests from the hotel
    fun checkOut(guestCount: Int) {
        this.freeRooms = this.freeRooms.plus(guestCount)
    }
}