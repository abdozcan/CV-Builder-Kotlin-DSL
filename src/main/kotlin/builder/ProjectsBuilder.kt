package org.abdozcan.builder

import builder.BuilderScope

@BuilderScope
class ProjectsBuilder {
    private val projects = mutableListOf<Project>()

    data class Project(
        val name: String,
        val description: String? = null,
        val link: String? = null
    )

    @BuilderScope
    class ProjectBuilder(private val name: String) {
        var description: String? = null
        var link: String? = null

        fun build(): Project {
            return Project(
                name = name,
                description = description,
                link = link
            )
        }
    }

    fun build(): List<Project> = projects

    fun project(name: String, block: ProjectBuilder.() -> Unit) {
        projects.add(ProjectBuilder(name = name).apply(block).build())
    }
}