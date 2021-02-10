package org.acme

import io.quarkus.redis.client.RedisClient
import io.quarkus.redis.client.RedisClientName
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@ApplicationScoped
@Path("/hello")
class GreetingResource(val redisClient: RedisClient) {

    init {
        redisClient.set(listOf("some_key", "it works"))
    }

    @GET
    @Path("/sentinel")
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(): String {
        val result = redisClient.get("some_key")?.toString() ?: return "value not found"
        return result
    }
}