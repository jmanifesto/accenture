package io.twodigits.urlshortener.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.service.URLService;
import io.twodigits.urlshortener.utils.URLValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api",
        produces = { MediaType.APPLICATION_JSON_VALUE })
public class URLController {
    private static final Logger LOGGER = LoggerFactory.getLogger(URLController.class);
    @Autowired
    private URLService urlService;

    @ApiOperation(value = "Get list of all urls in the System ", response = Iterable.class, tags = "url-controller")
    @GetMapping(value = "/urls")
    public Iterable<URL> getAllURLs() {
        return urlService.listAllURLs();
    }

    @ApiOperation(value = "Get urls by user ", response = Optional.class, tags = "url-controller")
    @GetMapping(value ="/url/{id}")
    public Optional<URL> getUrlByUser(@PathVariable Long id) {
        return urlService.getURL(id);
    }

    @ApiOperation(value = "Create and save url by user ", response = URL.class, tags = "url-controller")
    @PostMapping(value ="/url")
    public URL createURL(@RequestBody URL url, HttpServletRequest request) throws Exception {
        String longUrl = url.getUrl();
        LOGGER.info("Received url to shorten: " + longUrl);
        if (URLValidator.INSTANCE.validateURL(longUrl)) {
            String localURL = request.getRequestURL().toString();
            URL shortenedUrl = urlService.shortenURL(url, localURL + url);
            LOGGER.info("Shortened url to: " + shortenedUrl);
            return shortenedUrl;
        }
        throw new Exception("Please enter a valid URL");
    }

    @ApiOperation(value = "Update url by user ", response = URL.class, tags = "url-controller")
    @PutMapping(value ="/url/{user}")
    public Optional<URL> updateURL(@RequestBody URL url, @PathVariable String user) {
        return urlService.updateUrl(url, user);
    }

    @ApiOperation(value = "Delete url by user ", tags = "url-controller")
    @DeleteMapping(value ="/url/{id}")
    void deleteURL(@PathVariable Long id, @RequestBody URL url) {
        urlService.deleteURL(id, url.getUser());
    }
}