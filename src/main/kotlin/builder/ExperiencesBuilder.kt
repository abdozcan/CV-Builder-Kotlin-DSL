package org.abdozcan.builder

import builder.BuilderScope

@BuilderScope
class ExperiencesBuilder {
    private val experiences = mutableListOf<Experience>()
    
    data class Experience(
        val place: String,
        val position: String,
        val location: String,
        val startDate: String,
        val endDate: String? = null,
        val description: String? = null
    )

    enum class WorkStatus {
        Working,
        Worked
    }

    

    @BuilderScope
    class PlaceBuilder {
        private lateinit var place: String
        private lateinit var position: String
        private lateinit var location: String
        private lateinit var startDate: String
        private var endDate: String? = null
        var doing: String? = null

        fun build(): Experience = Experience(place, position, location, startDate, endDate, description = doing)
        infix fun WorkStatus.`as`(position: String) { this@PlaceBuilder.position = position }
        infix fun Unit.from(date: String) { this@PlaceBuilder.startDate = date }
        infix fun Unit.to(date: String) { this@PlaceBuilder.endDate = date }
        infix fun Unit.since(date: String) { this@PlaceBuilder.startDate = date }
        infix fun Unit.`in`(location: String) { this@PlaceBuilder.location = location }
        infix fun Unit.at(place: String) { this@PlaceBuilder.place = place }
    }

    fun build(): List<Experience> = experiences
    operator fun String.invoke(block: PlaceBuilder.() -> Unit) = experiences.add(PlaceBuilder().apply(block).build())
}