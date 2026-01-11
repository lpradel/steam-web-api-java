Steam Web API for Java
==================

[![build](https://github.com/lpradel/steam-web-api-java/actions/workflows/build-maven.yml/badge.svg)](https://github.com/lpradel/steam-web-api-java/actions/workflows/build-maven.yml)
[![Coverage Status](https://coveralls.io/repos/lpradel/steam-web-api-java/badge.svg?branch=master)](https://coveralls.io/r/lpradel/steam-web-api-java?branch=master)
[![Maven Central](https://img.shields.io/maven-central/v/com.lukaspradel/steam-web-api)](https://mvnrepository.com/artifact/com.lukaspradel/steam-web-api)

This library provides access to the data available from Valve's Steam API for Java applications. The available systems/interfaces are documented in the [Valve Developer Community Wiki](https://developer.valvesoftware.com/wiki/Steam_Web_API).

The resulting data is mapped to POJOs with canonical accessors which can be consumed to evaluate the result data. The only requirement to use this library is a [Steam Web API Key](http://steamcommunity.com/dev/apikey). Once provided, it takes 3 lines of Java code to consume a Steam webservice with this library (see [Usage](#usage) below).

## Installation

The installation process of this library depends on your build system and dependency management. The necessary steps for the most common approaches are listed below.

### Maven

Include the following dependency in your `pom.xml`:

```xml
<dependency>
    <groupId>com.lukaspradel</groupId>
    <artifactId>steam-web-api</artifactId>
    <version>1.13.0</version>
</dependency>
```

### Gradle

Include the dependency in your `build.gradle` like so:

```
dependencies {
    implementation 'com.lukaspradel:steam-web-api:1.13.0'
}
```

### Ant

If your build system is ANT you will need to include the .jar in your classpath. An example could look like this:

```xml
[...]

<property name="src.dir" value="src"/>
<property name="lib.dir" value="../jars"/>

<path id="external.jars">
    <fileset dir="${lib.dir}" includes="**/*.jar"/>
</path>

<path id="project.classpath">
    <pathelement location="${src.dir}"/>
    <path refid="external.jars" />
</path>

[...]
```

Furthermore, you need to ensure that the .jar is present at runtime. This depends on your specific build and will vary for .jar artifacts, web applications and so forth.

### Manual

You can download the artifact manually from [Maven Central](https://repo1.maven.org/maven2/com/lukaspradel/steam-web-api/1.13.0/steam-web-api-1.13.0.jar).

### Dependencies

The library itself has the following dependencies:
- Jackson

You will have to ensure their presence if you installed manually or using ANT. Maven will take care of this for you.

## Usage

### Quick Start

In this section you will find a minimal code example to get started in a few minutes. If you are looking for more detailed information check out the next section.

```java
package com.lukaspradel.steamapi.webapi.demo;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.appnews.GetNewsForApp;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;

public class SteamWebApiDemo {

    public static void main(String[] args) throws SteamApiException {
    
        SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder("Your-Web-API-Key").build();
        GetNewsForAppRequest request = SteamWebApiRequestFactory.createGetNewsForAppRequest(570); // appId of Dota 2
        GetNewsForApp getNewsForApp = client.<GetNewsForApp> processRequest(request);
    }

}
```

The result data is in the `GetNewsForApp` POJO.

### In-depth guide

This library provides POJOs for all responses to the various requests a user might send to the Steam API. These are generated from [JSON Schema](http://json-schema.org/).

First, determine which interface/request you need from the [Valve Developer Community Wiki](https://developer.valvesoftware.com/wiki/Steam_Web_API).

Note that most requests require a Steam Web API Key. To use this library you must first request your key [here](http://steamcommunity.com/dev/apikey).

Once you have your key, the first step is to create an instance of `SteamWebApiClient`:

```java
SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder("Your-Web-API-Key").build();
```

Create an instance of the necessary request. There are two approaches to do this. For this example we are assuming that you want to get news for Dota 2, i.e. create a [GetNewsForApp](https://developer.valvesoftware.com/wiki/Steam_Web_API#GetNewsForApp_.28v0002.29) request with appid 570 (Dota 2).

Your first option is to invoke the relevant `SteamWebApiRequestFactory` for this purpose:

```java
GetNewsForAppRequest request = SteamWebApiRequestFactory.createGetNewsForAppRequest(appId);
```

Alternatively, you can use the relevant `GetNewsForAppRequestBuilder` of the request directly. For the purpose of illustration we create a `GetNewsForAppRequest` with a maximum of 10 news each with a maximum length of 500 here:

```java
GetNewsForAppRequest request = new GetNewsForAppRequestBuilder(570).count(10).maxLength(500).buildRequest();
```

Finally, invoke the `processRequest` method of `SteamWebApiClient`:
```java
GetNewsForApp getNewsForApp = client.<GetNewsForApp> processRequest(request);
```

The result data is in the `GetNewsForApp` POJO. You can access it using the accessors of the POJO in question.

## FAQ

**Q: `GetOwnedGames` request returns `null` values for game name, only `appId` is present in output?**

**A:** Make sure to set the designated request option `include_appinfo` in your request with the builder method
`includeAppInfo` like so:
```java
  GetOwnedGamesRequest req = new GetOwnedGamesRequest.GetOwnedGamesRequestBuilder( "steamId" ).includeAppInfo(true).buildRequest();
```

Or use the other factory method
```java
SteamWebApiRequestFactory.createGetOwnedGamesRequest(String steamId, boolean includeAppInfo, boolean includePlayedFreeGames, List<Integer> appIdsFilter)
```
which accepts `includeAppInfo`.

See [Steam API documentation](https://developer.valvesoftware.com/wiki/Steam_Web_API#Arguments_7) for `GetOwnedGames`
and [this StackOverflow question](https://stackoverflow.com/a/73028555/1055743) for detailed answer.


## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :)

## Releasing

### Automated Github action release

Run a new [release-to-maven-central](https://github.com/lpradel/steam-web-api-java/actions/workflows/release-to-maven-central.yml)
Github workflow and enter the desired release version.

If the release version contains the `-SNAPSHOT` qualifier,
then only a snapshot release will be uploaded to Sonatype nexus. Otherwise a full release with the given
version will be uploaded and staged and a
corresponding [Github release](https://github.com/lpradel/steam-web-api-java/releases)
with the same release version and auto-generated release notes will be created.

### Manual release

Use the following Maven `settings.xml` with valid GPG key / Sonatype user token:

```xml
<settings>
    <profiles>
        <profile>
            <id>release</id>
            <properties>
                <gpg.keyname>###</gpg.keyname>
                <gpg.passphrase>###</gpg.passphrase>
            </properties>
        </profile>
    </profiles>

    <servers>
        <server>
            <id>central</id>
            <username>###</username>
            <password>###</password>
        </server>
    </servers>
</settings>
```

Release to Sonatype Nexus with the following command:

```shell
mvn clean deploy -P release
```

## History

- Version 1.13.0: Make base URL configurable [#20](https://github.com/lpradel/steam-web-api-java/pull/82) and update all dependencies
- Version 1.12.1: Bump all dependencies
- Version 1.12.0: Fixed local IDE build issue with TestNG and exposed HTTP status code in `SteamApiException`
- Version 1.11.0: Added support for GetSchemaItems, GetSchemaOverview and GetUGCFileDetails APIs
- Version 1.10.0: Added support for GetPlayerItems (TF2) API
- Version 1.9.1: Bump all dependencies
- Version 1.9.0: Modularize project
- Version 1.8.0: Add support for `ResolveVanityURL` requests
- Version 1.7.0: Replace the internally used HTTP Client from Apache httpclient5 to java.net.http (needs Java 11)
- Version 1.6.0: Add support for `GetAppList` requests from `GetAppList` interface
- Version 1.5.0: Breaking change in this library: use Long return values over Integer to fix [#20](https://github.com/lpradel/steam-web-api-java/issues/20) and avoid possible integer overflows
- Version 1.4.2: Update dependencies
- Version 1.4.1: Update httpclient5, Jackson and Slf4j (fix CVE). Remove commons-io dependency.
- Version 1.4: Upgrade to httpclient5 and change Java version to 8 for broader compatibility and Android support
- Version 1.3: Upgraded dependencies and bump Java version to 11
- Version 1.2: Bugfix Release. Fixed #3, updated dependencies and fixed non-compiling code in docs.
- Version 1.1: Minor bugfix release. Fixed #1 and updated dependencies.
- Version 1.0: Initial release. Includes all functionality documented in the [Valve Developer Community Wiki](https://developer.valvesoftware.com/wiki/Steam_Web_API).

## Credits

- [Lukas Pradel](https://github.com/lpradel)
- [Pazus](https://github.com/Pazus)
- [41zu](https://github.com/41zu)
- [Joey Bleau](https://github.com/josephbleau)
- [Mihajlo Nesic](https://github.com/MihajloNesic)
- [Fugasss](https://github.com/Fugasss)
- [LSauce](https://github.com/LSauce)

## License


    Copyright 2015 Lukas Pradel
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
