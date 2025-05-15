package builder

@BuilderScope
class LocationBuilder(private val country: String) {
    var city: String? = null
    var address: String? = null

    fun build(): String = (address?.let { "$it, " } ?: "") + (city?.let { "$it, " } ?: "") + country
}