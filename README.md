# CAB302 Project: GroupUp!

## Description

*GroupUp!* is an innovative social networking and event organising app designed to connect
individuals with shared interests, hobbies, or activities. Unlike traditional event-hosting
platforms that primarily focus on connecting friends or established groups, GroupUp! introduces
a unique twist by facilitating events with random participants who share a common interest.

## Purpose

GroupUp! aims to promote digital wellbeing by facilitating meaningful connections among 
individuals with shared interests, hobbies, or activities. By connecting users based on common
interests, the app encourages social interactions that can contribute to users' overall
well-being. Based on secondary research, studies have shown that meaningful social connections
can have positive effects on mental health and emotional well-being, reducing feelings of
loneliness and isolation. Therefore, the purpose of this project must be to enhance real life
interactions amongst software users to reduce social isolation. Alongside this purpose, GroupUp!
must also foster interactions between a diverse range of individuals so that the app promotes
social inclusion and diversity. Of which will all contribute to a greater level of social
well-being for the users involved. As GroupUp! encourages users to engage in offline activities
and social events a further purpose of this project is to promote a balanced approach to
technology use for all users. By facilitating real-world social interactions and offline
activities, GroupUp! helps users maintain a greater level of wellbeing by indirectly guiding a
healthy balance between online and offline experiences. Finally by leveraging the social
networking and event organising features of GroupUp!, the project is expected to provide users
with opportunities to build social support networks and connections. Studies show that social
support plays a crucial role in promoting resilience and coping with stress, therefore
contributing to overall well-being. 

## Project Scope

### Inclusions:
- GroupUp! will include features such as event creation and discovery
- The platform will only be developed for PC 
- The project scope must/extend the requirements and project considerations below using JAVA/JAVAFX:
  - A visual interface using JavaFX, providing windows for key functions.
  - An authentication system for user sign-up and sign-in, integrated with both the graphical interface and data models.
  - A system for storing, retrieving, and updating user data, integrated with both the graphical interface and data models.
  - One or more application windows where users can perform the main tasks of the application, also integrated with the graphical interface and data models.
- Project Considerations (from lecture content):
  - Choose a legible font: Sans-serif fonts preferred (e.g. Arial, Helvetica, Vedana)
  - Use a reasonable font size: Ensure it is affected by system scale. Fonts appear smaller on high-DPI devices
  - One-handed Operation: Some users won’t be able to use two hands. Your application
  - should function with only a keyboard.
  - Keyboard Shortcuts: Assign keyboard shortcuts to common actions.
  - Clear Error Messages: When there is invalid user input, clearly highlight where it is and what is wrong.
  - Avoid Error Messages: Where possible, prevent errors from occurring or suggest solutions.
  - Constrain Input: Only allow input of the correct type to be entered – e.g. no text in a phone number.
  - Highlight Errors: If there is an error in the user input, highlight exactly where it is.
  - Clearly Label Required Fields

### Exclusions:
- GroupUp! does not extend to providing professional mental health services or counselling within the app.
- The project will not focus on advanced AI algorithms for personalised recommendations beyond basic interest matching.

### Limitations/Constraints:
- Development will be constrained by available resources, including time, and technical expertise.
- The app's effectiveness in promoting digital wellbeing may be limited by external factors such as user behaviour, preferences and the actual availability of users to genuinely appear at in person/registered events.
- GroupUp! Is expected to face challenges in ensuring both digital and physical user safety and privacy, as a result robust security measures must be considered for user data and compliance with relevant regulations.

## Features and Requirements

### Functionality:
**Host**: create, update, delete, confirm events, read guest information, event description, number of guests, confirm guests, decline guests \
**Guests**: read event, join event, add memo for the host 

### Backend Data:
- Event title
- Event description
- Time
- Location
- Number of guests
- Type of events
- Guest memo
- Events page
- My events page

![Data Flow Diagram](/img/datadia.png "Data Flow Diagram")

### Backend Plan:

Display Home Page (prompt login or sign up) 
  1. open window to login or sign up \
  &nbsp; a. Sign up page: \
  &nbsp; &nbsp; i. Email \
  &nbsp; &nbsp; ii. Username \
  &nbsp; &nbsp; iii. Password \
  &nbsp; b. Login page: \
  &nbsp; &nbsp; i. Email \
  &nbsp; &nbsp; ii. Password

### Frontend Plan:

#### Fonts:
Poppins or Roboto will be used consistently throughout the application for a cohesive visual identity and readability.

#### Pages:
- Login/Sign Up Page:
  - Users can either log in with existing credentials or sign up for a new account.
  - Includes input fields for username, email and password.
  - Option for account creation.
- Event Page:
  - Displays a list of available events.
  - Each event card shows key details like title, date, time, location, and type.
  - Allows users to filter events by type or search for specific events.
  - Option to click on an event card to view more details in a modal.
- My Events Page:
  - Shows a personalised list of events the user has created or joined.
  - Includes options to view upcoming events, past events, and event details.
  - Allows users to manage their events, such as editing or cancelling.

#### Modals:
- Event Detail Modal:
  - Provides an in-depth view of a specific event when clicked from the event page.
  - Shows all event details including title, date, time, location, description, type, and number of guests.
  - Allows users to join the event, view guest list, and add memo if applicable.
  - Option to close the modal and return to the event page.
- Inputs:
  - Event Title:
    - Text input field for users to enter the title of the event.
  - Time:
    - Dropdowns or date/time picker for selecting event start and end times.
  - Location:
    - Text input or dropdown for specifying the event location.
  - Number of Guests:
    - Numeric input field to set the maximum number of guests allowed for the event.
  - Event Description:
    - Text area for users to provide a detailed description of the event.
  - Type of Events:
    - Dropdown of checkboxes for users to select the category or type of event (e.g., study group, sports, party, etc.).
  - Images:
    - Allows users to upload images related to the event, such as flyers or location pictures.
- Validation Checks:
  - **Required Fields:** Ensure all mandatory fields (e.g., title, time, location) are filled out before allowing event creation or submission.
  - **Format Validation:** Validate input formats (e.g., email format for sign up, time format for event scheduling).
  - **Character Limits:** Enforce character limits for input fields, preventing excessively long entries.
  - **Unique Event Titles:** Check for uniqueness of event titles to avoid duplication.
  - **Date/Time Conflict:** Warn users if the selected event time overlaps with an existing event they are hosting or attending.

## Timeline and Milestones
### Version 1 (Week 6-7):
- Host Features:
  - Create Events: Hosts can create new events, specifying details such as title, date, time, location, description, and maximum number of guests.
  - Update Events: Hosts can modify event details as needed, such as changing the date or updating the description.
  - Delete Events: Hosts can cancel events they've created, removing them from the platform.
  - Confirm Events: Hosts can finalise event details and confirm the event, triggering notifications to guests.
- Guest Features:
  - Read Events: Guests can browse through the list of events, viewing details such as title, date, time, location, and description.
  - Join Events: Guests can indicate their interest in attending an event by joining it.
### Version 2 (Week 9-10):
- Host Features:
  - Read Guest Information: Hosts gain access to information provided by guests when they express interest in attending an event.
  - Confirm Guests: Hosts have the ability to approve guest requests to join an event, adding them to the guest list.
  - Decline Guests: Hosts can decline guest requests, providing reasons if necessary.
- Guest Features:
  - Add Memo for the Host: Guests can include a personal memo when requesting to join an event, introducing themselves or providing additional context.
### Version 3 (Week 12-13):
- Debugging and Finilasing Code

## Authors and Acknowledgement

The authors of *GroupUp!* are the Software Sisters:
- Daniel Paza
- Jenna George
- Jin Choi
- Kevin Nguyen
- Kundai Burekeni

We would like to acknowledge **QUT** and the **CAB302 Software Development** course teaching team
who are facilitating the learning required to develop *GroupUp!* and supporting its development for
the duration of Semester 1, 2024.

## Project Status

This project is in the design stage.
