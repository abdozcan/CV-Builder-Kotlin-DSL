package org.abdozcan

import builder.cv
import org.abdozcan.builder.ExperiencesBuilder.WorkStatus.Worked
import org.abdozcan.builder.ExperiencesBuilder.WorkStatus.Working
import org.abdozcan.renderer.markdown
import org.abdozcan.renderer.print

fun main() {
    print {
        markdown {
            cv {
                header {
                    name = "John Doe"
                    title = "Senior Software Engineer"
                    email = "johndoe@johndoe.com"

                    location(country = "USA") {
                        city = "Alaska"
                        address = "4211 Spenard Rd"
                    }

                    phone { "+123 456 7890" }

                    links {
                        github { "johndoe" }
                        linkedin { "en/johndoe" }
                        link { "johndoe.com" }
                    }
                }

                summary { "A passionate software engineer with experience in building scalable applications." }

                experiences {
                    "I am" {
                        Working `as` "Senior Software Engineer" since 2021.Apr `in` "USA" at "InnovateX"
                        doing = "Leading a team of engineers to build innovative software solutions"
                    }
                    "I" {
                        Worked `as` "Software Engineer" from 2018.Jan to 2020.Dec `in` "USA" at "TechCorp"
                        doing = "Developed and maintained web applications"
                    }
                }

                education {
                    school(name = "MIT") {
                        major = "Computer Science"
                        degree = "Bachelor"
                        years = "2014 - 2018"
                        gpa = 3.9 from 4.0
                    }
                }

                projects {
                    project(name = "ProjectX") {
                        description = "A project management tool built using Kotlin and Spring Boot."
                        link = "https://github.com/johndoe/projectx"
                    }
                    project(name = "ProjectY") {
                        link = "https://github.com/johndoe/projecty"
                    }
                }

                skills {
                    "Programming Languages" {
                        listOf("Kotlin", "Java", "Python", "C")
                    }
                    "Tools & Frameworks" {
                        listOf("Android SDK", "Spring Boot", "Git", "IntelliJ IDEA")
                    }
                }

                languages {
                    language { "English" } `is native?` Yes
                    language { "Spanish" }
                }
            }
        }
    }
}