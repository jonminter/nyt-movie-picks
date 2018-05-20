#! /bin/bash
# Deploy only if it's not a pull request
if [ -z "$TRAVIS_PULL_REQUEST" ] || [ "$TRAVIS_PULL_REQUEST" == "false" ]; then
  # Deploy only if we're testing the master branch
  if [ "$TRAVIS_BRANCH" == "master" ]; then
    echo "Download ecs-deploy..."
    wget https://raw.githubusercontent.com/silinternational/ecs-deploy/master/ecs-deploy -O ./.travis/ecs-deploy.sh
    chmod 0755 ./.travis/ecs-deploy.sh
    which jq
    if [ $? -ne 0 ]; then
      echo "Downloading jq..."
      wget https://github.com/stedolan/jq/releases/download/jq-1.5/jq-linux64 -O ./.travis/jq
      chmod 0755 ./.travis/jq
      TRAVIS_BIN_DIR="$(pwd)/.travis"
      export PATH=$PATH:$TRAVIS_BIN_DIR
    fi
    echo $PATH
    echo "Deploying $TRAVIS_BRANCH on $ECS_CLUSTER"
    ./.travis/ecs-deploy.sh -c $ECS_CLUSTER -n $ECS_SERVICE -i $REMOTE_IMAGE_URL:latest
  else
    echo "Skipping deploy because it's not an allowed branch"
  fi
else
  echo "Skipping deploy because it's a PR"
fi
