package gov.cdc.ncezid.eip.services.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import gov.cdc.ncezid.eip.rest.ApiVersion;
import gov.cdc.ncezid.eip.services.About;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by marcelo on 10/4/16.
 */

@RestController
@RequestMapping("/info/")
@ApiVersion({1})
public class InfoServiceController {
    @Autowired
    private About about;

    @RequestMapping(value="/about", method = GET)
           // produces = {"application/cdc.info.about-v1+json"}) //This forces Safari to download the file instead of opening it on the browser.
    @ResponseBody
    public About about() throws JsonProcessingException {
        return about;
    }

    @RequestMapping(value="/version", method = GET)
    public String getVersion() {
        return "Version: " + getClass().getPackage().getImplementationVersion();
    }

    @RequestMapping("/ping")
    public String ping() {
        return "Hello There! I'm alive.\nYou pinged me at " + ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT);
    }


}

