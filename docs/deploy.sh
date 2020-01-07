#!/usr/bin/env sh

# abort on errors
set -e

yarn build

cd .vuepress/dist
git clone -b gh-pages https://github.com/JunilHwang/zum_pilot/

cp -rf zum_pilot/.git ./.git
rm -rf zum_pilot

git add .
git commit -m "$1"
git push origin gh-pages

cd -