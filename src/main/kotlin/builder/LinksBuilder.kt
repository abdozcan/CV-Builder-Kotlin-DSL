package org.abdozcan.builder

import builder.BuilderScope

@BuilderScope
class LinksBuilder {
    private val links = mutableListOf<String>()

    fun build(): List<String> = links

    fun github(block: LinksBuilder.() -> String) {
        links.add("github.com/${block()}")
    }

    fun linkedin(block: LinksBuilder.() -> String) {
        links.add("linkedin.com/${block()}")
    }

    fun link(block: LinksBuilder.() -> String) {
        links.add(block())
    }
}