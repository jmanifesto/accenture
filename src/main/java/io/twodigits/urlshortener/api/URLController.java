package io.twodigits.urlshortener.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api",
        produces = { MediaType.APPLICATION_JSON_VALUE })
public class URLController {

    @Autowired
    private URLService urlService;

    @ApiOperation(value = "Get list of all urls in the System ", response = Iterable.class, tags = "url-controller")
    @GetMapping(value = "/urls")
    public Iterable<URL> getAllURLs() {
        return urlService.listAllURLs();
    }

    @ApiOperation(value = "Get urls by user ", response = Optional.class, tags = "url-controller")
    @GetMapping(value ="/url/{user}")
    public Iterable<URL> getUrlByUser(@PathVariable String user) {
        return urlService.getURL(user);
    }

    @ApiOperation(value = "Create and save url by user ", response = URL.class, tags = "url-controller")
    @PostMapping(value ="/url")
    public URL createURL(@RequestBody String user, String url) {
        return urlService.addURL(user, url);
    }

    @ApiOperation(value = "Update url by user ", response = URL.class, tags = "url-controller")
    @PutMapping(value ="/url/{user}")
    public Optional<URL> updateURL(@RequestBody URL url, @PathVariable String user) {
        return urlService.updateUrl(url, user);
    }

    @ApiOperation(value = "Delete url by user ", tags = "url-controller")
    @DeleteMapping(value ="/url/{user}")
    void deleteURL(@RequestBody URL url,@PathVariable String user) {
        urlService.deleteURL(url.getId(), user);
    }
}