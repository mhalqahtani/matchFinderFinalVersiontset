# matchFinderFinalVersion

[![CircleCI](https://dl.circleci.com/status-badge/img/gh/mhalqahtani/matchFinderFinalVersiontset/tree/main.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/mhalqahtani/matchFinderFinalVersiontset/tree/main)

 The code coverage 
![image](https://github.com/mhalqahtani/matchFinderFinalVersiontset/assets/144021434/2058ac41-9932-4ddd-a17e-6e920bcc61bd)


Mohammed Alqahtani 2037206

Abdullah Alharthi 2035071
## Introduction

Through the Java command line, Our project uses the API-FOOTBALL from the Rapid website to fetch live football match data for major leagues. Using the Observer design pattern, updates are sent to users via SMS via the Twilio API. The Abstract Factory pattern is used for creating observers, enhancing modularity and extensibility.



 Abstract Factory Pattern is one of the creational design patterns and provides an interface for creating families of related or dependent objects without specifying their concrete classes. So we implemented the Abstract Factory pattern on our project to make the creation of objects more flexible while promoting code reuse and system scalability.
Abstract Subject Interface

The Subject interface defines the contract for subjects. and it declared the following methods:

addSubscriber (Observer observer):

This method permits observers to be added to the subscriber list. This method will be used by concrete subjects to register observers so they can get notifications.

removeSubscriber(Observer observer): This method allows observers to be deleted from the subscriber list. By using this method, concrete subjects will be able to unregister observers and stop them from getting notifications in the future.

notifySubscribers(String message): This method is in charge of informing all subscribers of any changes to the subject's state. Whenever a state change occurs, concrete subjects will call this method, which will cause each subscribed observer to call their update method.

## Observer
We used the Observer design pattern to enable real-time updates and isolate the components responsible for acquiring match data and sending SMS notifications. this design pattern increases our system's adaptability, which makes it simple to add or remove observers without compromising its essential functions.


## The main
here we create the observer and Also call the related methods to fetch info and send it via SMS to the user.

![image](https://github.com/mhalqahtani/matchFinderFinalVersiontset/assets/144021434/e4c4d35d-8edf-4b2a-928b-252f21242295)

## fetchMatchesForToday method
The fetchMatchesForToday method sends an HTTP GET request to the API-FOOTBALL service, querying information about football matches scheduled for the current date. It utilizes the RapidAPI key for authentication, and upon a successful request, it returns the response body containing details about today's matches. In case of an exception during the request, the method prints the stack trace and returns null

![image](https://github.com/mhalqahtani/matchFinderFinalVersiontset/assets/144021434/ebd89b2d-29dc-4595-9217-ca1d7511435b)



The parseMatches method extracts and organizes information about football matches from the API response. It filters matches based on predefined leagues and countries, creating a map (leagueMatches) where each entry represents a unique league and country combination. Match details, including team names, venue, and scores, are formatted and merged into the map. The method returns the map, providing a structured representation of today's matches organized by league and country.

![image](https://github.com/mhalqahtani/matchFinderFinalVersiontset/assets/144021434/a30f6b71-e296-4473-af26-08f777d3bb15)

The smsSender class facilitates sending SMS messages using the Twilio API. It includes Twilio account credentials, sender and recipient phone numbers, and a Send method that initializes Twilio, creates an SMS message with match information, and sends it to the recipient. The class provides a streamlined solution for SMS notifications in our project.
![image](https://github.com/mhalqahtani/matchFinderFinalVersiontset/assets/144021434/a4f625b5-e0cf-4c81-a73e-27dfca8f9c3c)



## output
![image](https://github.com/mhalqahtani/matchFinderFinalVersiontset/assets/144021434/92902cca-31d0-4b0c-9392-0c96e9e939de)



as we can see here are the matches for the most important leagues and it is updated it depends on the day you request the service.
