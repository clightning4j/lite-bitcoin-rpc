# Lite Bitcoin RPC Wrapper

![GitHub Workflow Status](https://img.shields.io/github/workflow/status/clightning4j/lite-bitcoin-rpc/Java%20CI?style=flat-square)
![Maven Central](https://img.shields.io/maven-central/v/io.github.clightning4j/lite-bitcoin-rpc?style=flat-square)
![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/io.github.clightning4j/lite-bitcoin-rpc?server=https%3A%2F%2Foss.sonatype.org&style=flat-square)

A (another) Lite RPC wrapper for Bitcoin Core RPC 1.0, that permitted to have flexibility into making the request
with different versions of Bitcoin Core without lost compatibility during the update.

**Why you developed another one RPC wrapper for Bitcoin Core?**

__All the java implementation for Bitcoin core are old and not longer updated, and also are written with a specific version of Bitcoin core
this means that there are java implementations well tested that support the last version of Bitcoin as well as an old version of Bitcoin Core.__

## How Use it
 __TODO__

## Maven Repository
__TODO__

## Used by

__TODO__

## Code Style
> We live in a world where robots can drive a car, so we shouldn't just write code, we should write elegant code.

This repository use [google-java-format](https://github.com/sherter/google-java-format-gradle-plugin) to maintains the code of the repository elegant, so
before submit the code check the Java format with the following command on the root of the directory

```bash
./gradlew verifyGoogleJavaFormat
```

It any error are reported please run the following command to try to fix it

```bash
./gradlew googleJavaFormat
```

p.s: The gradle plugin work with all the JDK version >= 9 (or better with java byte code version compatible with the version  55.0)

For more details about the JDK support see the [this issue](https://github.com/sherter/google-java-format-gradle-plugin/issues/58)
and to know more about the Google Java code Style see the [this reference](https://google.github.io/styleguide/javaguide.html)

## Support

## License

<div align="center">
  <img src="https://opensource.org/files/osi_keyhole_300X300_90ppi_0.png" width="150" height="150"/>
</div>

A (another) Lite RPC wrapper for Bitcoin Core RPC 1.0, that permitted to have flexibility into making the request
with different versions of Bitcoin Core without lost compatibility during the update.

Copyright (C) 2021 Vincenzo Palazzo vincenzopalazzodev@gmail.com

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License along
with this program; if not, write to the Free Software Foundation, Inc.,
51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.