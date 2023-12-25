package io.github.soumikuxd.kotlinboot

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DBSeeder(val hotelRepository: HotelRepository) : CommandLineRunner {
    override fun run(vararg args: String?) {
        //Remove any old entries
        this.hotelRepository.deleteAll()

        // Create some sample entries
        val ibis = Hotel("Ibis", 3, 30)
        val novotel = Hotel("Novotel", 3, 50)
        val tulip = Hotel("Tulip", 4, 80)
        val sheraton = Hotel("Sheraton", 5, 120)

        // Add these samples to the repository
        val hotels = listOf<Hotel>(ibis, novotel, tulip, sheraton)
        this.hotelRepository.saveAll(hotels)
        println("--DB Initialised")
    }
}