#! /bin/bash
# Push only if it's not a pull request
if [ -z "$TRAVIS_PULL_REQUEST" ] || [ "$TRAVIS_PULL_REQUEST" == "false" ]; then
  # Push only if we're testing the master branch
  if [ "$TRAVIS_BRANCH" == "master" ]; then

    # This is needed to login on AWS and push the image on ECR
    # Change it accordingly to your docker repo
    pip install --user awscli
    export PY_USER_BIN=$(python -c 'import site; print(site.USER_BASE + "/bin")')
    export PATH=$PATH:$PY_USER_BIN
    eval $(aws ecr get-login --no-include-email --region $AWS_DEFAULT_REGION)

    # Create application.properties
    echo "Creating application.properties..."
    eval "echo \"$(cat config/application.properties.example)\"" > ./config/application.properties

    # Build and push
    echo "Building IMAGE_NAME=$IMAGE_NAME"
    docker build --build-arg "JAR_FILE=build/libs/nyt-movie-picks-0.0.1-SNAPSHOT.jar" -t $IMAGE_NAME .
    echo "Tagging $IMAGE_NAME:latest $REMOTE_IMAGE_URL:latest"
    docker tag $IMAGE_NAME:latest "$REMOTE_IMAGE_URL:latest"
    echo "Pushing $IMAGE_NAME:latest"
    docker push "$REMOTE_IMAGE_URL:latest"
    echo "Pushed $IMAGE_NAME:latest"
  else
    echo "Skipping deploy because branch is not 'master'"
  fi
else
  echo "Skipping deploy because it's a pull request"
fi
