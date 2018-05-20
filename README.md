# nyt-movie-picks

Mashup of [NYT API](https://developer.nytimes.com/)/[OMDB API](http://omdbapi.com/).

Display most recent NYT critic movie picks and the associated IMDB/Metascore ratings.

## Demo

[View demo app](http://co.jonminter.nyt-movie-picks-www.s3-website-us-east-1.amazonaws.com/)

## Tech Stack

### Backend

- Java 8
- Spring Boot

### Frontend

- Angular 6
- Bootstrap 4

### CI/CD
Uses Travis CI ![travis build status](https://travis-ci.org/jonminter/nyt-movie-picks.svg?branch=master)

Backend
- Compiles backend Java code
- Builds docker container with JAR
- Pushes docker container to AWS ECR
- Deploys newly pushed container to AWS ECS

Frontend
- Installs npm dependencies
- Compiles/minifies/packages source code
- Deploys to S3 bucket setup to host website
