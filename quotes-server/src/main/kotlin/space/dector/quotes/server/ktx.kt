package space.dector.quotes.server


/**
 * Try to read server port from **environment** configuration.
 * Fallback to `default` port value instead.
 *
 * @param default fallback value
 *
 * @return preferred server port
 */
internal fun detectPort(
    default: Int = 8080,
): Int {
    val environmentPort = System.getenv("PORT")
        ?.toIntOrNull()
    if (environmentPort != null) return environmentPort

    return default
}
