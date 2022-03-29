package com.ktoggle.plugins

import com.ktoggle.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    val mapOfToggles = mutableMapOf<String, KToggle<*>>()

    // Starting point for a Ktor app:
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        post("/add/feature-number") {
            val toggle = call.receive<NumberToggleRaw>()
            mapOfToggles[toggle.key] = KNumberToggle(toggle.value)
            call.respondText("Toggle with key ${toggle.key}", status = HttpStatusCode.Created)
        }

        post("/add/feature-boolean") {
            val toggle = call.receive<BooleanToggleRaw>()
            mapOfToggles[toggle.key] = KBooleanToggle(toggle.value)
            call.respondText("Toggle with key ${toggle.key}", status = HttpStatusCode.Created)
        }

        post("/add/feature-string") {
            val toggle = call.receive<StringToggleRaw>()
            mapOfToggles[toggle.key] = KStringToggle(toggle.value)
            call.respondText("Toggle with key ${toggle.key}", status = HttpStatusCode.Created)
        }

        post("/add/feature-json") {
            val toggle = call.receive<JsonToggleRaw>()
            mapOfToggles[toggle.key] = KJsonToggle(toggle.value)
            call.respondText("Toggle with key ${toggle.key}", status = HttpStatusCode.Created)
        }

        get("/all") {
            call.respond(mapOfToggles)
        }

        get("/feature") {
            val key = call.parameters["key"] ?: return@get call.respondText(
                "Missing or malformed key", status = HttpStatusCode.BadRequest
            )
            val feature = mapOfToggles[key]
            if (feature != null) {
                call.respond(feature)
            } else {
                call.respond(status = HttpStatusCode.NotFound, "feature with Key: $key not found")
            }
        }
    }

    routing {}
}
