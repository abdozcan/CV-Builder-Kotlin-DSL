package builder

import org.abdozcan.builder.LinksBuilder

@BuilderScope
object PhoneScope

data class Header(
    val name: String,
    val title: String,
    val phone: String,
    val email: String?,
    val location: String,
    val links: List<String>
)

@BuilderScope
class HeaderBuilder {
    var name: String? = null
    var title: String? = null
    var email: String? = null
    private var phone: String = ""
    private var location: String = ""
    private var links: List<String> = emptyList()

    fun build() = Header(
        name = requireNotNull(name) { "`name` must be set" },
        title = requireNotNull(title) { "`title` must be set" },
        phone = phone,
        email = requireNotNull(email) { "`email` must be set" },
        location = location,
        links = links
    )

    fun location(country: String, block: LocationBuilder.() -> Unit = {}) {
        location = LocationBuilder(country).apply(block).build()
    }

    fun phone(block: PhoneScope.() -> String) {
        phone = PhoneScope.block()
    }

    fun links(block: LinksBuilder.() -> Unit) {
        links = LinksBuilder().apply(block).build()
    }
}