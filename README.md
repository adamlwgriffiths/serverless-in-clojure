Serverless In Clojure
=====================

Welcome! Welcome to City-17... I mean Serverless in Clojure!

## Overview

This repository is a part of a series of tutorials for building Clojure services in AWS Lambda, as well as integrating different AWS services together in one big happy family.

Each folder matches with a tutorial. I'll continually add new material as I write more.

I've avoided pulling too much of the development environment in the tutorials. Here is where I'll be explaining the tooling I've used.

## Docker

I use Docker Compose to handle external dependencies without installing them locally. This gives me a nice workflow environment where I can spin up services without the hassle of installing them on my machines.

You are free to skip this step and use whatever tools you like. I include this for convenience. The configuration of the tutorials explicitly uses `localhost` when pointing at external services. This can be changed in the `build.boot` files in each folder.

### Requirements

* Vanilla Docker (Linux) or [Docker For Mac][dfm]
* Docker Compose

### Dockerized Dependencies

Most of the services can be accessed through localhost if you run Docker natively on Linux or using Docker for Mac. I haven't tested on Windows, so YMMV.

From the root directory, start the docker containers in the background.

    docker-compose up -d

Tear down the running instances.

    docker-compose down

## Boot

I'll shoot straight with ya. I like my tools to be programmatic. If you like Leiningen, more power to you! Each tutorial *should* be simple enough to port over.

### Requirements

* [Boot][boot]

### Tasks

Boot calls commands to it's configuration *Tasks*. They are similar to Leiningen commands, except they can be strung together.

#### `local` task

Your machine, with all your cool gadgets, is configured exactly how you like. No problem! You follow the same steps as above to set up the external dependencies, then run this command:

    boot local repl

Every project supports the `local` Boot option with all environment variables. The `local` boot task sets up the environment for connecting to the dockerized dependencies. You can hack as you like from here!

#### `build` task

Like the `local` task above, the `build` task is included with every project. When building, a `target` directory will be created and all build artifacts will go inside.

The filename of the artifact follows this standard:

    projectname-X.Y.Z.jar

Where `projectname` is the project's name, and `X.Y.Z` corresponds to the VERSION file.

## License

MIT

See LICENSE file for more information

[dfm]: https://docs.docker.com/docker-for-mac/
[boot]: https://github.com/boot-clj/boot
