package org.abdozcan.renderer

import builder.CV

fun CV.toMarkdown(): String = markdown { this }

fun markdown(block: () -> CV): String {
    var text = ""

    operator fun String.unaryPlus() {
        text += this
    }

    block().apply {
        with(header) {
            +"# $name\n"
            +"${title}\n"
            +"$email"
            +" | $phone"
            +" | $location\n"
            links.let {
                it.forEachIndexed { index, link ->
                    +link
                    if (index != it.lastIndex) +" | "
                }
            }
        }
        summary?.let {
            +"\n## Summary\n"
            +it
        }

        experiences.let {
            if (it.isNotEmpty()) +"\n## Experience"
            it.forEachIndexed { index, experience ->
                +"\n**${experience.position}**\n"
                +"${experience.place}\n"
                +"${experience.startDate} - ${experience.endDate ?: "Present"}\n"
                +"${experience.location}\n"
                experience.description?.let { +"> $it\n" }
            }
        }

        education.let {
            +"\n## Education\n"
            it.forEachIndexed { index, school ->
                if (it.size > 1) +"\n"
                +"### ${school.name}\n"
                +school.degree
                +" - ${school.major}"
                school.years?.let { +"\n$it\n" }
                school.gpa?.let { +"GPA: $it\n" }
            }
        }

        projects.let {
            if (it.isNotEmpty()) +"## Projects\n"
            it.forEachIndexed { index, project ->
                project.name.let { name ->
                    project.link?.let { link ->
                        +"### $name [\uD83D\uDD17](${link})"
                    } ?: +"### $name"
                    +"\n"
                }
                project.description?.let { +"$it\n" }
            }
        }

        skills.let {
            if (it.isNotEmpty()) +"## Skills\n"
            it.forEachIndexed { index, skillSet ->
                skillSet.category?.let { +"**$it:** " }
                skillSet.skills?.let {
                    it.forEachIndexed { index, skill ->
                        +skill
                        if (index != it.lastIndex) +", "
                        else +"\n"
                    }
                }
            }
        }

        languages.let {
            if (it.isNotEmpty()) +"## Languages\n"
            it.forEachIndexed { index, language ->
                language.language?.let {
                    +it
                    if (language.isNative) +"(Native)"
                }
                if (index != it.lastIndex) +", "
            }
        }
    }

    return text
}