package org.abdozcan.builder

import builder.BuilderScope

@BuilderScope
class SkillsBuilder {
    private val skills = mutableListOf<SkillSet>()

    data class SkillSet(
        val category: String? = null,
        val skills: List<String>? = null
    )

    fun build(): List<SkillSet> = skills

    operator fun String.invoke(block: SkillsBuilder.() -> List<String>) = skills.add(
        SkillSet(category = this, skills = block())
    )
}