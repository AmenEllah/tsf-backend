#! /bin/bash
which ssh-agent || ( apt-get update -y && apt-get install openssh-client -y ) > /dev/null

# Run ssh-agent (inside the build environment)
eval $(ssh-agent -s) > /dev/null

# Add the SSH key stored in SSH_PRIVATE_KEY variable to the agent store
ssh-add /tools/.ssh/id_rsa > /dev/null
mkdir -p ~/.ssh
ssh-keyscan gitlab.example.org > ~/.ssh/known_hosts 2>/dev/null
[[ -f /.dockerenv ]] && echo -e "Host *\n\tStrictHostKeyChecking no\n\n" > ~/.ssh/config

git config --global user.email 'gitlab-ci@example.org'
git config --global user.name 'Gitlab CI'

# Reconfigure push repository to use SSH
CI_PUSH_REPO=`echo $CI_BUILD_REPO | perl -pe 's#.*@(.+?(\:\d+)?)/#git@\1:#'`
git remote set-url --push origin "${CI_PUSH_REPO}"

# Maven doesn't like detach HEADs, attach
git checkout -B "$CI_BUILD_REF_NAME"
mvn $MAVEN_CLI_OPTS release:clean -DskipTests=true
mvn $MAVEN_CLI_OPTS release:prepare -DskipTests=true

# prepare done, pushing tags
git push origin --tags
mvn $MAVEN_CLI_OPTS release:perform -DskipTests=true
