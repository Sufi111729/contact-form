# Contact Form Backend

This is a Spring Boot backend for handling contact form submissions. It is configured to work with a frontend hosted at [https://sufiweb.netlify.app](https://sufiweb.netlify.app).

## Features

- REST API for contact form submissions
- Email sending via Gmail SMTP
- CORS configuration for frontend integration

## Getting Started

1. **Clone the repository:**

2. **Configure email settings in `src/main/resources/application.properties`:**

3. **Build and run the application:**

## API Endpoint

- `POST /api/contact` — Accepts contact form data in JSON format.

## CORS

CORS is enabled for `https://sufiweb.netlify.app` in `src/main/java/com/contect/sufi/config/WebConfig.java`.

## License

This project is licensed under the MIT License.
