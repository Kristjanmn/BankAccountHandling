package io.nqa.test.bankaccounthandling.service;

import io.nqa.commons.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

@Service
@RequiredArgsConstructor
public class ExternalCallService implements IExternalCallService {
    private final RestTemplate restTemplate;
    private final String baseUri = "https://httpstat.us/";
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Make request to httpstat.us API
     *
     * @param uri request uri
     * @return custom response
     */
    @Override
    public CustomResponse makeRequest(String uri) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(baseUri + uri, String.class);
            return new CustomResponse(true, response.getBody());
        } catch (RestClientException e) {
            if (HttpClientErrorException.class.isAssignableFrom(e.getClass()))
                return new CustomResponse(true, "Received client error " + ((HttpClientErrorException) e).getStatusText());
            if (HttpServerErrorException.class.isAssignableFrom(e.getClass()))
                return new CustomResponse(true, "Received server error " + ((HttpServerErrorException) e).getStatusText());
            if (e.getClass().equals(ResourceAccessException.class))
                return new CustomResponse(true, "Received resource access error " + e.getMessage());

            e.printStackTrace();
            return new CustomResponse(false, "External request response threw unhandled exception " + e.getClass().getSimpleName() + " with message: " + e.getMessage());
        }
    }

    /**
     * Call {@link #makeRequest(String)} with desired status code as integer.
     *
     * @param statusCode desired status code
     * @return custom response
     */
    @Override
    public CustomResponse makeRequest(int statusCode) {
        return makeRequest(String.valueOf(statusCode));
    }

    /**
     * Get random status code from httpstat.us
     *
     * @return custom response
     */
    @Override
    public CustomResponse getRandom() {
        return makeRequest("random/101,200-304,306-561");
    }

    /**
     * Make requests to get all status codes and check if they are handled properly.
     *
     * @return response to client once all requests have been performed
     */
    @Override
    public CustomResponse makeAllRequests() {
        logger.info("Starting multiple request for {} endpoints", baseUri);
//        makeRequest(100); continue - hangs
        makeRequest(101);
//        makeRequest(102); processing - hangs
//        makeRequest(103); early hints - hangs
        makeRequest(200);
        makeRequest(201);
        makeRequest(202);
        makeRequest(203);
        makeRequest(204);
        makeRequest(205);
        makeRequest(206);
        makeRequest(207);
        makeRequest(208);
        makeRequest(226);
        makeRequest(300);
        makeRequest(301);
        makeRequest(302);
        makeRequest(303);
        makeRequest(304);
//        makeRequest(305); use proxy - hangs
        makeRequest(306);
        makeRequest(307);
        makeRequest(308);
        makeRequest(400);
        makeRequest(401);
        makeRequest(402);
        makeRequest(403);
        makeRequest(404);
        makeRequest(405);
        makeRequest(406);
        makeRequest(407);
        makeRequest(408);
        makeRequest(409);
        makeRequest(410);
        makeRequest(411);
        makeRequest(412);
        makeRequest(413);
        makeRequest(414);
        makeRequest(415);
        makeRequest(416);
        makeRequest(417);
        makeRequest(418);
        makeRequest(421);
        makeRequest(422);
        makeRequest(423);
        makeRequest(424);
        makeRequest(425);
        makeRequest(426);
        makeRequest(428);
        makeRequest(429);
        makeRequest(431);
        makeRequest(451);
        makeRequest(500);
        makeRequest(501);
        makeRequest(502);
        makeRequest(503);
        makeRequest(504);
        makeRequest(505);
        makeRequest(506);
        makeRequest(507);
        makeRequest(508);
        makeRequest(510);
        makeRequest(511);
        // non-standard status codes
        makeRequest(419);
        makeRequest(420);
        makeRequest(440);
        makeRequest(449);
        makeRequest(450);
        makeRequest(460);
        makeRequest(463);
        makeRequest(494);
        makeRequest(495);
        makeRequest(496);
        makeRequest(497);
        makeRequest(498);
        makeRequest(499);
        makeRequest(520);
        makeRequest(521);
        makeRequest(522);
        makeRequest(523);
        makeRequest(524);
        makeRequest(525);
        makeRequest(526);
        makeRequest(527);
        makeRequest(530);
        makeRequest(561);
        logger.info("Finished with {} requests", baseUri);

        return new CustomResponse(true, "Made request for all supported codes");
    }
}
