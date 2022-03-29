package com.ktoggle

import com.ktoggle.plugins.configureRouting
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo


fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        val client = KMongo.createClient().coroutine
        val database = client.getDatabase("toggles_db")
        configureRouting(database)
        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
            }
        }
    }.start(wait = true)
}
