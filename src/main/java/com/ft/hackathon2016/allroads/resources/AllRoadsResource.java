package com.ft.hackathon2016.allroads.resources;

import java.util.*;
import java.util.function.Supplier;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ft.hackathon2016.allroads.fetchers.ContentApiClient;
import com.ft.hackathon2016.allroads.model.ContentRequest;
import com.ft.hackathon2016.allroads.model.SuggestedContent;
import com.ft.hackathon2016.allroads.service.SuggestedContentSvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/__health")
public class AllRoadsResource {

  private static final Logger LOG = LoggerFactory.getLogger(AllRoadsResource.class);

  private final SuggestedContentSvc suggestedContentSvc;

  public AllRoadsResource(final SuggestedContentSvc suggestedContentSvc) {
    this.suggestedContentSvc = suggestedContentSvc;
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public List<SuggestedContent> suggestContentFor(ContentRequest request) {

    try {
      LOG.info("Requested url {}, content {}", request.getUrl(), request.getContent());

      List<SuggestedContent> suggestedContents = suggestedContentSvc.getSuggestedContent(request.getContent());

      return suggestedContents;
    }

    catch (Exception e){
      LOG.error("EXCEPTION THROWN: " + e.getMessage(), e);
      return Collections.emptyList();
    }
  }
}
