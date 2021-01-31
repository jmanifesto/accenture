import org.springframework.cloud.contract.spec.Contract


Contract.make {
    description "should return even when number input is even"
    request{
        method GET()
        url("/api/urls")
    }
    response {
        body '''\
		[
                {
                    "id": 1,
                    "urlId": "google",
                    "url": "www.google.de",
                    "user": "jleyria"
                },
                {
                    "id": 2,
                    "urlId": "mongodb",
                    "url": "www.mongodb.de",
                    "user": "short"
                }
        ]
	'''
        status 200
    }
}