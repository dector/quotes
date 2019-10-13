package io.github.dector.quotes.storage

import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.domain.Uuid
import io.github.dector.quotes.domain.new
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.BehaviorSpec

class InMemoryQuotesStorageTest : BehaviorSpec({

    Given("In-memory storage") {
        val storage = InMemoryQuotesStorage()

        Then("Is empty") {
            storage.count() shouldBe 0
        }

        When("Insert new item") {
            val uuid = Uuid.new()
            val quote = Quote(uuid, "Quote 1", "Author 1")

            storage.insert(quote)

            Then("It should be saved") {
                storage.count() shouldBe 1

                val storedQuote = storage.find(uuid)!!
                storedQuote.content shouldBe quote.content
                storedQuote.author shouldBe quote.author
            }

            And("Insert item with same uuid") {
                val quote2 = Quote(uuid, "Quote 2", "Author 2")

                shouldThrow<IllegalStateException> {
                    storage.insert(quote2)
                }
            }
        }
    }
})
