.DEFAULT_GOAL := build-run

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app

test:
	./gradlew test

lint:
	./gradlew spotlessCheck

.PHONY: build test lint