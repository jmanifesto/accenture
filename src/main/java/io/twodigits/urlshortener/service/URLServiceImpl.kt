package io.twodigits.urlshortener.service

import io.twodigits.urlshortener.model.URL
import io.twodigits.urlshortener.repository.URLRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class URLServiceImpl : URLService {
    @Autowired
    private val urlRepository: URLRepository? = null

    override fun updateUrl(url: URL, user: String): Optional<URL>? {
        return urlRepository?.findById(url.id)?.map { foundUrl: URL ->
            foundUrl.url = foundUrl.url ?: url.url
            foundUrl.user = foundUrl.user ?: url.user
            foundUrl.urlId = foundUrl.urlId ?: url.urlId
            urlRepository.save(url)
        }
    }

    override fun listAllURLs(): Iterable<URL> {
        return urlRepository!!.findAll()
    }

    override fun listURLs(user: String): Iterable<URL> {
        return urlRepository!!.findByUser(user)
    }

    override fun addURL(user: String, url: String): URL {
        val newURL = URL()
        newURL.user = user
        newURL.url = url
        newURL.urlId = url + Date().toString();
        return urlRepository!!.save(newURL)
    }

    override fun getURL(id: Long, user: String): Optional<URL> {
        return urlRepository!!.findByIdAndUser(id, user)
    }

    override fun getURL(user: String): Iterable<URL>? {
        return urlRepository!!.findByUser(user)
    }

    override fun deleteURL(id: Long, user: String) {
        val url = urlRepository!!.findByIdAndUser(id, user)
        if (url.isPresent) {
            urlRepository.delete(url.get())
        }
    }
}