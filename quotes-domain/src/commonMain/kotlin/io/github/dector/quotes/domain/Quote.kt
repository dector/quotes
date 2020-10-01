package io.github.dector.quotes.domain

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


@Serializable
data class Quote(
    @Serializable(with = UuidSerializer::class)
    val uuid: Uuid,
    val content: String,
    val author: String
) {
    constructor(content: String, author: String) :
            this(Uuid.new(), content, author)

    val quote: String get() = content
}

object UuidSerializer : KSerializer<Uuid> {
    override val descriptor = PrimitiveSerialDescriptor(
        "uuid", PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): Uuid {
        return Uuid(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, uuid: Uuid) {
        encoder.encodeString(uuid.value)
    }
}
