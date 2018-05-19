docker build --build-arg "JAR_FILE=build/libs/nyt-movie-picks-0.0.1-SNAPSHOT.jar" -t co.jonminter/nyt-movie-picks .
docker run co.jonminter/nyt-movie-picks
