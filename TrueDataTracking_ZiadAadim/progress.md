# Progress

## Checkpoint 1: Initial Setup
- Set up the Spring Boot project with Kotlin and Maven.
- Verified the application runs without errors and loads successfully.
- Decided on the next steps to set up the controller, views, and data tracking.

## Checkpoint 2: Interface and Styling Enhancements
- Designed the main page with clickable rectangles for courses (Anatomy, Perspective, Gesture, References).
- Added subpages for courses with consistent layout and styling.
- Implemented a "Go Back" button for subpages, aligning it below the "Select a Topic" heading and centering it.
- Updated the application to use a modern, clean font (**Poppins**) for a professional look.
- Improved responsiveness and hover effects for buttons and cards across all pages.

## Checkpoint 3: Pop-up for Terms of Use
- Created a pop-up to display terms of use after a 3-second delay.
- Styled the modal to appear in the center of the screen, above all other content.
- Ensured the modal requires users to accept the terms before continuing.
- Used **sessionStorage** to show the modal only once per session.
- Verified that the modal does not reappear on page reloads or navigation within the same session.

## Checkpoint 4: Backend Data Tracking
- Implemented backend tracking for user interactions with MongoDB.
- Tracked the following data:
    - Time spent on each page.
    - Clicks on cards (e.g., Anatomy, Perspective, Gesture, etc.).
    - Navigation patterns across the website.
    - User's session-based `userId`.
- Added functionality to generate a unique `userId` for each session using a 10-digit code.
- Fixed issues where cards were non-clickable, ensuring smooth interaction throughout the website.
- Updated database integration to log all events with the correct `userId`.
- Tested the system thoroughly to ensure data is accurately logged in MongoDB for every user interaction.