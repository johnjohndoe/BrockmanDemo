[![Build Status](https://travis-ci.com/johnjohndoe/BrockmanDemo.svg?branch=master)](https://travis-ci.com/johnjohndoe/BrockmanDemo) [![Apache License](http://img.shields.io/badge/license-Apache%20License%202.0-lightgrey.svg)](http://choosealicense.com/licenses/apache-2.0/)

# Brockman demo

Android project to demonstrate the usage of the [Brockman library][brockman-library]

![Brockman demo app](gfx/brockman-demo-screenshot.jpg "Brockman demo app")


## Usage

There is an **app module** to checkout a working example.

This project contains two product flavors: _dev_ and _live_ which are configured in _app/build.gradle_.
In both flavors a `STREAMING_API_BASE_URL` and a `STREAMING_API_OFFERS_PATH` are configured.

- _dev_: Configures the app to load data from a static file - in case the API is not online
- _live_: Configures the app to load data from the live API of the CCC


### Gradle build

To install the demo application to your device run the following task:

```bash
$ ./gradlew installDebug
```



## Author

* [Tobias Preuss][tobias-preuss]

## License

    Copyright 2015 - 2021 Tobias Preuss

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[brockman-library]: https://github.com/johnjohndoe/Brockman
[tobias-preuss]: https://github.com/johnjohndoe
