package io.github.dector.quote.api_server.v1

import io.github.dector.quote.api_server.getQuotesJsonString
import spark.Request
import spark.Response

val v1_quotes by lazy {{ req: Request, resp: Response ->
    resp.type("application/json")
    getQuotesJsonString()
}}
