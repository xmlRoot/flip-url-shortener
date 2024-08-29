# flip-url-shortener
A simple url shortener. Not production ready, the project will not be maintained in the future, use it only as a reference. 

# Tech stack
1. Kotlin
2. Kotlin kotest
3. Spring boot
   1. Spring boot data mongodb
   2. Spring open-api (openapi specs)
   3. Spring actuator
4. Build using gradle
5. MongodDB as persistence layer

# Project Structure
This project is implemented as monorepo using [gradle multi module project](https://docs.gradle.org/current/userguide/intro_multi_project_builds.html) setup following 
the feature architecture (design) style with a [hexagonal architecture twist](#the-hexagonal-twist). The features are separated in the [libs](./libs) folder.
The [service](./service) folder is where we place are [bootable](https://spring.io/projects/spring-boot) projects. 
Each feature is separated in : 
 - a feature folder
 - a model folder
 - `(optional)` a migration folder - if it makes sense for that feature

## The common folder 
  - there is where we place our cross-cutting concern objects (ex. [DocumentEntity](./libs/common/shortener-common/src/main/kotlin/com/flip/urlshorterner/common/model/document/DocumentEntity.kt)

## The hexagonal twist
In classical hexagonal architecture we need a god like facade to act as glue to our somewhat loosely coupled module, however, here
we create a delegate, which will act as our facade, by allowing us to decorate `feature` functionality or when it makes sense 
add more functionality by combining several feature's functionality. 
The defacto scope creep **is allowed only in the service delegates**.

## Adding functionality
To add a new feature follow these steps. We will add an account feature
1. Create a feature folder under `libs` called `account` 
   1. Go to [settings.gradle.kts](./settings.gradle.kts) and include your projects
2. Under account add subprojects 
   1. `account-model` 
   2. `account-feature`
   3. `(optional)` `account-migration` - if it makes sense for that feature, if we are adding new entities and/or indexes we need a migration
3. Add a dependency to the `shorter-api` monolith service
4. Add a package `com.flip.urlshorterner.account` under which add: 
   1. `api` - We will place our rest controllers here
      1. `view` - this is where our viewFacades will live
      2. `view.model` - this is where our view dto will live
   2. `services` - this is where our delegates live
   3. `model` - where our dtos will live (delegate dtos)

## libs
The location of our feature folders

## Services

Where we place our "runnable" projects. 


# How to run the project
In the [tools](./tools) you will find a [docker-compose](./tools/docker-compose.yml) file which when run using 
```shell
    docker-compose up -d
```
will launch our mongodb instance. (Our persistence layer). 

To run the project you can launch it from your favorite IDE, (as long as that IDE is InteliJ) as a spring boot project.

# What's left to do 
1. Make the README more readable and comprehensive.
2. Configure `jacaco` and fail the build if test coverage is under `80%`
3. Add more sophisticated logic to for url validation. 
4. Add url shortener links to expire.
5. Add url shortener links delete remove functionality.
6. Add security 
   1. Add Accounts - 
   2. Add anonymous links, which expire after x days 
7. Ensure all sensitive data is moved to a proper secure storage location 
    1. Add `vault` integration
8. Add some telemetry to determine if the mongo implementation makes sense, or it needs to be re-implemented using a distributed data grid 
such as `Redis` or `Hazelcast`.
   1. Add log aggregation and export
9. Add Deployment procedure 
   1. Given the simplicity of the project we can use a container as a service such as AWS ECS or GCP Cloud Run. 
10. Add gradle goodies
    1. convention plugins
    2. extension functions to help include `feature` dependencies.  
11. Add kotlin linter and enforce rules 