package io.github.dector.quotes.storage

import io.github.dector.quotes.domain.Quote
import io.github.dector.quotes.domain.Uuid
import io.github.dector.quotes.domain.new
import io.kotlintest.IsolationMode
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.BehaviorSpec

class InMemoryQuotesStorageTest : BehaviorSpec({

    Given("In-memory storage") {
        val storage: QuotesStorage = InMemoryQuotesStorage()

        Then("Is empty") {
            storage.count() shouldBe 0
        }

        When("Insert new item") {
            val uuid = Uuid.new()
            val quote = Quote(uuid, "Quote 1", "Author 1")

            storage.insert(quote)

            Then("It should be saved") {
                storage.count() shouldBe 1

                val storedQuote = storage.findById(uuid)!!
                storedQuote.content shouldBe quote.content
                storedQuote.author shouldBe quote.author
            }

            And("Insert item with same uuid") {
                val quote2 = Quote(uuid, "Quote 2", "Author 2")

                shouldThrow<IllegalStateException> {
                    storage.insert(quote2)
                }
            }

            And("Upsert item same uuid") {
                val quote3 = Quote(uuid, "Quote 3", "Author 3")

                storage.upsert(quote3)

                Then("Stored quote should be updated") {
                    storage.count() shouldBe 1

                    val storedQuote = storage.findById(uuid)!!
                    storedQuote.content shouldBe quote3.content
                    storedQuote.author shouldBe quote3.author
                }
            }
        }

        And("Has few items") {
            val quote1 = Quote("Quote 1", "Author 1")
            val quote2 = Quote("Quote 2", "Author 2")
            val quote3 = Quote("Quote 3", "Author 3")

            storage.insert(quote1)
            storage.insert(quote2)
            storage.insert(quote3)

            When("Request item by uuid") {
                val item = storage.findById(quote2.uuid)

                Then("Operation is not desctructive") {
                    storage.count() shouldBe 3
                }

                Then("It should return correct item") {
                    item shouldBe quote2
                }
            }
        }

        And("Has few other items") {
            val uuid = Uuid.new()
            storage.insert(Quote(uuid, "Quote 1", "Author 1"))
            storage.insert(Quote("Quote 2", "Author 2"))
            storage.insert(Quote("Quote 3", "Author 3"))

            Then("") {
                storage.count() shouldBe 3
            }

            When("Add few unique items") {
                storage.insert(Quote("Quote 4", "Author 4"))
                storage.insert(Quote("Quote 5", "Author 5"))

                Then("") {
                    storage.count() shouldBe 5
                }

                And("Update existing item") {
                    storage.upsert(Quote(uuid, "Quote 6", "Author 6"))

                    Then("") {
                        storage.count() shouldBe 5
                    }

                    And("Delete existing item") {
                        storage.delete(uuid)

                        Then("") {
                            storage.count() shouldBe 4
                        }
                    }
                }
            }
        }
    }
}) {
    override fun isolationMode() = IsolationMode.InstancePerLeaf
}
