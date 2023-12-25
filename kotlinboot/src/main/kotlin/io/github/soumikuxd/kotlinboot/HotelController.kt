package io.github.soumikuxd.kotlinboot

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/hotels")
class HotelController(val hotelRepository: HotelRepository) {
    /**
     * Get a list of all the hotels
     */
    @GetMapping("/all")
    fun all(): MutableIterable<Hotel> = this.hotelRepository.findAll()

    /**
     * Find a hotel by name
     */
    @GetMapping("/{name}")
    fun getByName(@PathVariable("name") name: String): List<Hotel> = this.hotelRepository.findByName(name)

    /**
     * Check in guests to a hotel
     */
    @PostMapping("/checkin")
    fun checkIn(@RequestBody checkInRequest: CheckInRequest) {
        // Find the hotel
        val hotel = this.hotelRepository.findByName(checkInRequest.hotelName)[0]
        // Check in the guests
        hotel.checkIn(checkInRequest.guestCount)
        // Save to DB
        this.hotelRepository.save(hotel)
    }

    /**
     * Add a hotel to the list
     */
    @PostMapping("/add")
    fun add(@RequestBody newHotel: Hotel) {
        this.hotelRepository.save(newHotel)
    }
}