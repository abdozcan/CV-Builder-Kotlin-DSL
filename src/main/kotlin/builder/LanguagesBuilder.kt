package org.abdozcan.builder

import builder.BuilderScope

@BuilderScope
class LanguagesBuilder {
    private val languages = mutableListOf<Language>()

    data class Language(
        val language: String? = null,
        val isNative: Boolean = false
    )

    fun build(): List<Language> = languages

    fun language(block: LanguagesBuilder.() -> String): Language {
        return Language(language = block()).apply {
            languages.add(this@apply)
        }
    }

    infix fun Language.`is native?`(native: Boolean) {
        languages.set(index = languages.lastIndex, copy(isNative = native))
    }
}