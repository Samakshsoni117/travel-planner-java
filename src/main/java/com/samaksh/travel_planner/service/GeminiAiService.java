package com.samaksh.travel_planner.service;

import com.samaksh.travel_planner.dto.GeminiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GeminiAiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash-preview-05-20:generateContent?key=";

    private final RestTemplate restTemplate;

    @Autowired
    public GeminiAiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getItinerary(String userPrompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String systemPrompt = "You are a world-class travel planning expert. Your task is to generate a detailed, day-by-day travel itinerary based on a user's request." +
                              "You MUST respond with ONLY a valid JSON object, without any markdown formatting (e.g., no ```json)." +
                              "The JSON object must have the following structure:" +
                              "{" +
                              "  \"title\": \"A concise, catchy title for the trip, e.g., 'An Adventurous 4-Day Trip to Goa'\"," +
                              "  \"summary\": \"A brief, 1-2 sentence summary of the trip.\"," +
                              "  \"daily_plan\": [" +
                              "    {" +
                              "      \"day\": 1," +
                              "      \"theme\": \"A short theme for the day, e.g., 'Arrival and Beach Relaxation'\"," +
                              "      \"activities\": [" +
                              "        {" +
                              "          \"time\": \"e.g., Morning, Afternoon, Evening\"," +
                              "          \"description\": \"A detailed description of the activity.\"," +
                              "          \"budget_tip\": \"An optional tip related to budget, e.g., 'Pack a lunch to save money.'\"" +
                              "        }" +
                              "      ]" +
                              "    }" +
                              "  ]" +
                              "}";

        // Build the request body using our DTOs
        var userPart = new GeminiDto.Part(userPrompt);
        var systemPart = new GeminiDto.Part(systemPrompt);
        var content = new GeminiDto.Content(List.of(userPart));
        var systemInstruction = new GeminiDto.SystemInstruction(List.of(systemPart));
        var generationConfig = new GeminiDto.GenerationConfig("application/json");

        var requestPayload = new GeminiDto.GeminiRequest(List.of(content), systemInstruction, generationConfig);

        HttpEntity<GeminiDto.GeminiRequest> entity = new HttpEntity<>(requestPayload, headers);

        try {
            GeminiDto.GeminiResponse response = restTemplate.postForObject(GEMINI_API_URL + apiKey, entity, GeminiDto.GeminiResponse.class);
            
            // Extract the text from the nested response structure
            if (response != null && response.candidates() != null && !response.candidates().isEmpty()) {
                GeminiDto.Candidate firstCandidate = response.candidates().get(0);
                if (firstCandidate.content() != null && firstCandidate.content().parts() != null && !firstCandidate.content().parts().isEmpty()) {
                    return firstCandidate.content().parts().get(0).text();
                }
            }
            throw new RuntimeException("Failed to get a valid response from the Gemini API.");
        } catch (Exception e) {
            // In a real app, you'd have more sophisticated error handling
            System.err.println("Error calling Gemini API: " + e.getMessage());
            throw new RuntimeException("Error communicating with the AI service.", e);
        }
    }
}

