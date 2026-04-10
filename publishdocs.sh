#!/bin/bash

cd ./docs; ./adoc2md.sh; cd -
cp ./docs/index.md ./README.md

git add .
git status
#git commit -m "the docs"
#git push
