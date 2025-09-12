Smart Travel Itinerary Generator
A full-stack web application that leverages the power of the Google Gemini generative AI to create detailed, day-by-day travel itineraries from a single, natural language prompt. This project demonstrates a modern, secure architecture with a Java Spring Boot backend and a responsive vanilla JavaScript front-end.

✨ Key Features
Natural Language Input: Users can describe their dream trip in plain English (e.g., "A 5-day cultural trip to Japan on a budget").

AI-Powered Generation: The backend communicates with the Google Gemini API to generate a creative and structured travel plan.

Full-Stack Architecture: A clear separation of concerns between the dynamic front-end and the robust Java backend.

Secure API Handling: The Gemini API key is securely stored on the backend, never exposed to the client-side browser.

Responsive UI: The user interface is built with Tailwind CSS to be fully responsive and looks great on any device.

🛠️ Tech Stack
This project was built using a modern set of tools and technologies:

Backend
Java 17: The core programming language.

Spring Boot 3: Framework for building the robust REST API.

Apache Maven: For project build and dependency management.

Spring Web: To create RESTful endpoints and handle HTTP requests.

Google Gemini API: The external generative AI service used for itinerary creation.

Frontend
HTML5: For the structure of the web page.

Tailwind CSS: For modern, utility-first styling without writing custom CSS.

Vanilla JavaScript: To handle user interactions, API calls to the backend, and dynamic rendering of the results.

🚀 Running the Project Locally
To run this project on your own machine, please follow these steps.

Prerequisites
JDK 17 (or later) installed.

Apache Maven installed.

A Google Gemini API Key. You can get one for free from Google AI Studio.

Backend Setup
Clone the repository:

git clone [https://github.com/your-username/java-ai-travel-planner.git](https://github.com/your-username/java-ai-travel-planner.git)
cd java-ai-travel-planner

Create the secrets file: Navigate to src/main/resources/ and create a new file named application.properties.

Add your API Key: Inside application.properties, add the following line, replacing YOUR_GEMINI_KEY_HERE with your actual key:

gemini.api.key=YOUR_GEMINI_KEY_HERE

Run the application: Go back to the root of the project in your terminal and run the following command:

./mvnw spring-boot:run

The backend server will start on http://localhost:8080.

Frontend Setup
With the backend server running, simply open a web browser.

Navigate to http://localhost:8080.

The index.html file will be served automatically, and you can start generating itineraries!
