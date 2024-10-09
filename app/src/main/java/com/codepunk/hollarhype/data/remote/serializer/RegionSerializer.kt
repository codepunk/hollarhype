package com.codepunk.hollarhype.data.remote.serializer

import com.codepunk.hollarhype.util.intl.Region
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class RegionSerializer : KSerializer<Region> {

    @Suppress("SpellCheckingInspection")
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(
            serialName = "com.codepunk.hollarhype.util.intl.Region",
            kind = PrimitiveKind.STRING
        )

    override fun serialize(encoder: Encoder, value: Region) {
        encoder.encodeString(value.regionCode)
    }

    override fun deserialize(decoder: Decoder): Region = Region.of(decoder.decodeString())

}
