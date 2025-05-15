package org.abdozcan.builder

import builder.BuilderScope

@BuilderScope
class EducationBuilder {
    private val schools = mutableListOf<School>()

    data class School(
        val name: String,
        val major: String,
        val years: String?,
        val degree: String,
        val gpa: String?
    )

    @BuilderScope
    class SchoolBuilder(private val name: String) {
        var major: String? = null
        var years: String? = null
        var degree: String? = null
        var gpa: String? = null

        fun build(): School {
            return School(
                name = name,
                major = requireNotNull(major) { "`major` must be set" },
                degree = requireNotNull(degree) { "`degree` must be set" },
                years = years,
                gpa = gpa
            )
        }

        infix fun Double.from(other: Double): String = "$this / $other"
    }

    fun build(): List<School> = schools

    fun school(name: String, block: SchoolBuilder.() -> Unit) {
        schools.add(SchoolBuilder(name = name).apply(block).build())
    }
}