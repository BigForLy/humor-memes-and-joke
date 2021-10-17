package com.example.humormemeandjoke.dataClasses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TronaldDumpJsonClass(
    @SerialName("appeared_at")
    val appearedAt: String?,
    @SerialName("created_at")
    val createdAt: String?,
    @SerialName("_embedded")
    val embedded: Embedded?,
    @SerialName("_links")
    val links: Links?,
    @SerialName("quote_id")
    val quoteId: String?,
    @SerialName("tags")
    val tags: List<String?>?,
    @SerialName("updated_at")
    val updatedAt: String?,
    @SerialName("value")
    val value: String?
) {
    fun toParse() : String{
        return value!!
    }
    @Serializable
    data class Embedded(
        @SerialName("author")
        val author: List<Author?>?,
        @SerialName("source")
        val source: List<Source?>?
    ) {
        @Serializable
        data class Author(
            @SerialName("author_id")
            val authorId: String?,
            @SerialName("bio")
            val bio: String?,
            @SerialName("created_at")
            val createdAt: String?,
            @SerialName("_links")
            val links: Links?,
            @SerialName("name")
            val name: String?,
            @SerialName("slug")
            val slug: String?,
            @SerialName("updated_at")
            val updatedAt: String?
        ) {
            @Serializable
            data class Links(
                @SerialName("self")
                val self: Self?
            ) {
                @Serializable
                data class Self(
                    @SerialName("href")
                    val href: String?
                )
            }
        }

        @Serializable
        data class Source(
            @SerialName("created_at")
            val createdAt: String?,
            @SerialName("filename")
            val filename: String?,
            @SerialName("_links")
            val links: Links?,
            @SerialName("quote_source_id")
            val quoteSourceId: String?,
            @SerialName("remarks")
            val remarks: String?,
            @SerialName("updated_at")
            val updatedAt: String?,
            @SerialName("url")
            val url: String?
        ) {
            @Serializable
            data class Links(
                @SerialName("self")
                val self: Self?
            ) {
                @Serializable
                data class Self(
                    @SerialName("href")
                    val href: String?
                )
            }
        }
    }

    @Serializable
    data class Links(
        @SerialName("self")
        val self: Self?
    ) {
        @Serializable
        data class Self(
            @SerialName("href")
            val href: String?
        )
    }
}