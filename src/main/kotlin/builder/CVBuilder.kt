package builder

import org.abdozcan.builder.EducationBuilder
import org.abdozcan.builder.ExperiencesBuilder
import org.abdozcan.builder.LanguagesBuilder
import org.abdozcan.builder.ProjectsBuilder
import org.abdozcan.builder.SkillsBuilder

@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@DslMarker
annotation class BuilderScope

@BuilderScope
object SummaryScope

data class CV(
    val header: Header,
    val summary: String?,
    val experiences: List<ExperiencesBuilder.Experience>,
    val education: List<EducationBuilder.School>,
    val projects: List<ProjectsBuilder.Project>,
    val skills: List<SkillsBuilder.SkillSet>,
    val languages: List<LanguagesBuilder.Language>
)

fun cv(block: CVBuilder.() -> Unit): CV = CVBuilder().apply(block).build()

@BuilderScope
class CVBuilder {
    private lateinit var header: Header
    private var summary: String? = null
    private var experiences: List<ExperiencesBuilder.Experience> = emptyList()
    private var education: List<EducationBuilder.School> = emptyList()
    private var projects: List<ProjectsBuilder.Project> = emptyList()
    private var skills: List<SkillsBuilder.SkillSet> = emptyList()
    private var languages: List<LanguagesBuilder.Language> = emptyList()

    fun build() = CV(
        header = header,
        summary = summary,
        experiences = experiences,
        education = education,
        projects = projects,
        skills = skills,
        languages = languages
    )


    fun header(block: HeaderBuilder.() -> Unit) {
        header = HeaderBuilder().apply(block).build()
    }

    fun summary(block: SummaryScope.() -> String) {
        summary = SummaryScope.block()
    }

    fun experiences(block: ExperiencesBuilder.() -> Unit) {
        experiences = ExperiencesBuilder().apply(block).build()
    }

    fun education(block: EducationBuilder.() -> Unit) {
        education = EducationBuilder().apply(block).build()
    }

    fun projects(block: ProjectsBuilder.() -> Unit) {
        projects = ProjectsBuilder().apply(block).build()
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }
}