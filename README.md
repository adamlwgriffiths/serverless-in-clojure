Serverless In Clojure
=====================

Welcome! Welcome to City-17... I mean Serverless in Clojure!

## Overview

This repository is a part of a series of tutorials for building Clojure services in AWS Lambda, as well as integrating different AWS services together in one big happy family.

Each folder matches with a tutorial. I'll continually add new material as I write more.

## Technical details

I've avoided pulling too much of the development environment in the tutorials. Here is where I'll be explaining the tooling I've used.

### Docker

I use Docker Compose to handle external dependencies without installing them locally. This gives me a nice workflow environment where I can spin up services without the hassle of installing them on my machines.

You are free to skip this step and use whatever tools you like. I include this for convenience. The configuration of the tutorials explicitly uses `localhost` when pointing at external services. This can be changed in the `build.boot` files in each folder.

#### Requirements

* Vanilla Docker (Linux) or [Docker For Mac][dfm]
* Docker Compose

[dfm]: https://docs.docker.com/docker-for-mac/

### Boot

I'll shoot straight with ya. I like my tools to be programmatic. If you like Leiningen, more power to you! Each tutorial *should* be simple enough to port over.

#### Requirements

* [Boot][boot]

[boot]: https://github.com/boot-clj/boot

### License

MIT

See LICENSE file for more information
