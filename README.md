# Ads Final
> Rían Errity [20333410]

[Specification](docs/spec.pdf) ·
[Design Document](docs/design-document.pdf) · 
[GitHub: Origin](https://github.com/ParadauxIO/csu22012-final)

#### Specification

> TLDR Edition. For the full specification see [here.](docs/spec.pdf)

- Due April 10th [Penalty-free submission]
- Concerns the implementation of a bus management system based on
Vancouver bus system data.
- Topics covered: graphs, searching, sorting and tries.
- Submission to blackboard only, No jUnit/webcat
- Worth 40%
- Must deliver code, a pre-recorded demonstration video and design document.

### Constituent Files
- `stops.txt` : List of all bus stops in the system.
- `transfers.txt`: List of possible transfers and transfer times between stops
- `stop_times.txt`: Daily schedule containing the trip times of all routes on all stops

### Functionality
- Ability to find the shortest paths between two bus stops, returning the list of stops visited as well as the associated cost.

### Setup and Execution

This project makes use of the [Gradle](https://gradle.org/) build tool. This permits the use and management of 
third-party dependencies, as well as the building, testing and deployment of Java archives (JAR.)

To run this project you may follow the following steps: 

```bash
./gradlew run
```

Which will compile and execute the project on your own machine, alternatively you can make use
of the gradle `application run` task to build a JAR file as follows:

```bash
./gradlew build
```

Which you can subsequently deploy and run elsewhere by moving the deployed jar which will 
have been generated at `build/libs/BusManagement-VERSION.jar`. Alternatively, you may run it in place as follows:

```bash
java -jar build/libs/BusManagement-1.0.0.jar
```

## Dependencies

Dependencies are defined in `build.gradle.kts`

- JUnit Jupiter (5)
- Checkerframework (3.21.3) [^1]


<hr>
[^1]: This is the default version at the time of writing, it is written to use the checkerframework gradle plugin which makes use of the latest version.