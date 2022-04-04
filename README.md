# FilmQueryProject
## Description
### This program is a basic search engine that browses a pretend movie database that is stored on my computer. The user can search via the film ID number or a keyword which searches through the films' titles and descriptions.

## Technologies Used
### In this program I use SQL and mysql for the first time. I downloaded a fake movie database from Skill Distillery and operated mysql through the Mac OS terminal. I wrote the code using Java using Eclipse as my IDE, Atom as my text editor, and Github as my repository.

## Lessons Learned
### Aside from learning how to embed SQL into Java, this program was a refresher on using ArrayLists, try-catch blocks, and loop logic. I ran into issues trying to nest my Cast ArrayList into my Film ArrayList for my filmKeywordSearch method and ultimately abandoned that approach while maintaining a simpler functionality by having the keyword search display a film's ID number and having the ID search display the cast. The user is automatically prompted to search by ID or keyword, or to exit the program after every result is displayed which helps smooth the seams between searches. I had to overcome a barrier from seeing logic work in my ID search method that didn't work in my keyword search method, but that was resolved after I was reminded about the isEmpty function native to List classes.
