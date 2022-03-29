package com.ktoggle.plugins

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ktoggle.models.*
import com.ktoggle.models.NumberToggleRaw
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.litote.kmongo.coroutine.CoroutineDatabase

fun Application.configureRouting(database: CoroutineDatabase) {

    val mapOfToggles = mutableMapOf<String, KToggle<*>>()

    // Starting point for a Ktor app:
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        post("/add/feature-number") {
            val resultModel = call.receive<NumberToggleRaw>()
            database.getCollection<NumberToggleRaw>().save(resultModel)
            call.respondText("Toggle with key ${resultModel.key}", status = HttpStatusCode.Created)
        }

        post("/add/feature-boolean") {
            val resultModel = call.receive<BooleanToggleRaw>()
            database.getCollection<BooleanToggleRaw>().save(resultModel)
            call.respondText("Toggle with key ${resultModel.key}", status = HttpStatusCode.Created)
        }

        post("/add/feature-string") {
            val resultModel = call.receive<StringToggleRaw>()
            database.getCollection<StringToggleRaw>().save(resultModel)

            call.respondText("Toggle with key ${resultModel.key}", status = HttpStatusCode.Created)
        }

        post("/add/feature-json") {
            val resultModel = call.receive<JsonToggleRaw>()
            database.getCollection<JsonToggleRaw>().save(resultModel)

            call.respondText("Toggle with key ${resultModel.key}", status = HttpStatusCode.Created)
        }

        get("/all") {
            val togglesModel = TogglesModel(
                numberToggles = database.getCollection<NumberToggleRaw>().find().toList(),
                booleanToggles = database.getCollection<BooleanToggleRaw>().find().toList(),
                stringToggles = database.getCollection<StringToggleRaw>().find().toList()
            )
            call.respond(Gson().toJsonTree(togglesModel))
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
