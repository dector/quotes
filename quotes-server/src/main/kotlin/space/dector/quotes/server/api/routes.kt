package space.dector.quotes.server.api

import io.github.dector.quotes.domain.Quote
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing


fun Application.installRootRedirect() =
    routing {
        get("/") { call.respondRedirect(ROOT_REDIRECT) }
    }

fun Application.installApiRouting() =
    routing {
        route("/api/v2") {
            get("quotes") {
                val quotes = fetchAllQuotes()
                call.respond(quotes)
            }
        }
    }

private fun fetchAllQuotes(): List<Quote> {
    return listOf(
        Quote(
            "We live in a society exquisitely dependent on science and technology, in which hardly anyone knows anything about science and technology.",
            "Carl Sagan"
        ),
        Quote(
            "Only two things are infinite, the universe and human stupidity, and I'm not sure about the former.",
            "Albert Einstein"
        ),
        Quote("Everything is theoretically impossible, until it is done.", "Robert A. Heinlein"),
        Quote(
            "Most people say that it is the intellect which makes a great scientist. They are wrong: it is character.",
            "Albert Einstein"
        ),
        Quote(
            "All science requires mathematics. The knowledge of mathematical things is almost innate in us. This is the easiest of sciences, a fact which is obvious in that no one's brain rejects it; for laymen and people who are utterly illiterate know how to count and reckon.",
            "Roger Bacon"
        ),
        Quote(
            "Equipped with his five senses, man explores the universe around him and calls the adventure Science.",
            "Edwin Powell Hubble"
        )
    )

}

private const val ROOT_REDIRECT = "https://github.com/dector/quotes"
