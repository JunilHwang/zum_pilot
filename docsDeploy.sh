#!/usr/bin/env sh

# abort on errors
set -e

git add .
git commit -m "$*"
git push origin master

cd docs

sh deploy.sh "$*"

cd -