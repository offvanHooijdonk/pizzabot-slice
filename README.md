# pizzabot

## Prerequisites
You need Gradle installed; tested to work with v6.7 .
Initialize Gradle wrapper in the project folder - run in the source code dir:
`gradle wrapper --gradle-version 6.7`
this will load the wrapper files into the directory.

## How to run
To run the app please use the following scripts:
* run `buildRun.sh` to rebuild the jar and execute with the arguments provided inside the script.
* run `run.sh` to just perform a run of a _pre-built_ jar with the arguments provided inside the script.

**Note!** Please follow the arguments format as in the scripts provided: use double-quotes `"` for **each** location coordinates, which makes single location a single input argument, as bash confronts using brackets in arguments.
