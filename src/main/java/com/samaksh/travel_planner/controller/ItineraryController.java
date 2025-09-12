package com.samaksh.travel_planner.controller;

import com.samaksh.travel_planner.service.GeminiAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

// A simple record to map the incoming JSON from the front-end.
// The front-end will send: {"prompt": "user's text..."}
record ItineraryRequest(String prompt) {}

@RestController
@RequestMapping("/api/itinerary")
// Allowing requests from any origin, which is fine for local development.
@CrossOrigin(origins = "*") 
public class ItineraryController {

    private final GeminiAiService geminiAiService;

    @Autowired
    public ItineraryController(GeminiAiService geminiAiService) {
        this.geminiAiService = geminiAiService;
    }

    /**
     * This endpoint listens for POST requests at /api/itinerary/generate.
     * It expects a JSON body with a "prompt" field.
     * @param request The incoming request body, automatically mapped to our ItineraryRequest record.
     * @return The JSON string response from the Gemini AI service.
     */
    @PostMapping("/generate")
    public String generateItinerary(@RequestBody ItineraryRequest request) {
        // We take the prompt from the request and pass it to the service layer.
        return geminiAiService.getItinerary(request.prompt());
    }
}

