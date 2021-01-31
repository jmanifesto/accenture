package io.twodigits.urlshortener.model

import lombok.*
import javax.persistence.*

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
class URL {

    companion object {
        private const val serialVersionUID = 1L
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    /**
     * The unique ID of an URL
     */
    @Column(nullable = false)
    var urlId: String? = null

    /**
     * The URL for which a short URL is provided
     */
    @Column(nullable = false)
    var url: String? = null

    /**
     * The ID of a user to which this URL belongs
     */
    @Column(nullable = false)
    var user: String? = null
}