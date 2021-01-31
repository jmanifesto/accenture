package io.twodigits.urlshortener.service

import io.twodigits.urlshortener.model.URL
import io.twodigits.urlshortener.repository.URLRepository
import org.hibernate.bytecode.BytecodeLogger.LOGGER
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class URLServiceImpl : URLService {
    @Autowired
    private val urlRepository: URLRepository? = null
/*
    @Throws(Exception::class)
    fun getLongURLFromID(uniqueID: String?): String? {
        val dictionaryKey: Long = IDConverter.INSTANCE.getDictionaryKeyFromUniqueID(uniqueID)
        val longUrl: String = urlRepository.getUrl(dictionaryKey)
        LOGGER.info("Converting shortened URL back to {}", longUrl)
        return longUrl
    }*/

    private fun formatLocalURLFromShortener(localURL: String): String {
        val addressComponents = localURL.split("/".toRegex()).toTypedArray()
        // remove the endpoint (last index)
        val sb = StringBuilder()
        for (i in 0 until addressComponents.size - 1) {
            sb.append(addressComponents[i])
        }
        sb.append('/')
        return sb.toString()
    }

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

    override fun shortenURL(url: URL, localURL: String): URL {
        val newURL = URL()
        newURL.user = url.user
        newURL.url = url.url
        newURL.urlId = formatLocalURLFromShortener(localURL);
        return urlRepository!!.save(newURL)
    }

    override fun addURL(user: String, url: String): URL {
        val newURL = URL()
        newURL.user = user
        newURL.url = url
        //TODO: Generate URL
        return urlRepository!!.save(newURL)
    }

    override fun getURL(id: Long, user: String): Optional<URL> {
        return urlRepository!!.findByIdAndUser(id, user)
    }

    override fun getURL(id: Long): Optional<URL>? {
        return urlRepository!!.findById(id)
    }

    override fun deleteURL(id: Long, user: String) {
        val url = urlRepository!!.findByIdAndUser(id, user)
        if (url.isPresent) {
            urlRepository.delete(url.get())
        }
    }
}