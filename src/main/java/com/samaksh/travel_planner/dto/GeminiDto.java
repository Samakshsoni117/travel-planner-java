package com.samaksh.travel_planner.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

// Using Java records for immutable, concise DTOs

// --- Request DTOs ---
public class GeminiDto {

    public record Part(String text) {}

    public record Content(List<Part> parts) {}

    public record SystemInstruction(List<Part> parts) {}
    
    public record GenerationConfig(@JsonProperty("response_mime_type") String responseMimeType) {}

    public record GeminiRequest(
        List<Content> contents,
        @JsonProperty("system_instruction") SystemInstruction systemInstruction,
        @JsonProperty("generation_config") GenerationConfig generationConfig
    ) {}


    // --- Response DTOs ---

    public record GeminiResponse(List<Candidate> candidates) {}

    public record Candidate(Content content) {}
    
}
