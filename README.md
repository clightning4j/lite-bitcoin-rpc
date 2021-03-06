<div align="center">
  <h1>:zap: Lite Bitcoin RPC Wrapper :zap:</h1>

  <img src="https://github.com/clightning4j/icons/raw/main/org/ic_launcher/res/mipmap-xxxhdpi/ic_launcher.png" />

  <p>
    <strong> :zap: A (another) Lite RPC wrapper for Bitcoin Core RPC 1.0, that permitted to have flexibility into making the request
with different versions of Bitcoin Core without lost compatibility during the update. :zap: </strong>
  </p>

  <p>
    <img alt="GitHub Workflow Status" src="https://img.shields.io/github/workflow/status/clightning4j/lite-bitcoin-rpc/Java%20CI?style=flat-square">
    <img alt="GitHub Workflow Status" src="https://img.shields.io/maven-central/v/io.github.clightning4j/lite-bitcoin-rpc?style=flat-square">
   <img alt="GitHub Workflow Status" src="https://img.shields.io/nexus/s/io.github.clightning4j/lite-bitcoin-rpc?server=https%3A%2F%2Foss.sonatype.org&style=flat-square">
    <a href="https://codecov.io/gh/clightning4j/lite-bitcoin-rpc">
     <img alt="GitHub Workflow Status" src="https://codecov.io/gh/clightning4j/lite-bitcoin-rpc/branch/main/graph/badge.svg?token=KFIW2FXMBJ">
   </a>

</p>
</div>

## Table of Content

- Introduction
- How Use it
- Used by
- Code Style
- Support
- License

## Introduction

**Why you developed another RPC wrapper for Bitcoin Core?**

__All the java implementations for Bitcoin core are old and not longer updated, and also are written with a specific version of Bitcoin core.
This means that there are java implementations well tested that support the last version of Bitcoin as well as an old version of Bitcoin Core.__

## How Use it

- [btcli4j](https://github.com/clightning4j/btcli4j): A c-lightning plugin that give the possibility to run c-lightning with bitcoin pruning mode.

## Maven Repository
__TODO__

## Used by

__TODO__

## Code Style
> We live in a world where robots can drive a car, so we shouldn't just write code, we should write elegant code.

This repository use [google-java-format](https://github.com/sherter/google-java-format-gradle-plugin) to maintains the code of the repository elegant, so
before submitting the code check the Java format with the following command on the root of the directory

```bash
./gradlew verifyGoogleJavaFormat
```

If any error are reported please run the following command to try to fix it

```bash
./gradlew googleJavaFormat
```

p.s: The gradle plugin works with all the JDK versions >= 9 (or better with java byte code version compatible with the version  55.0)

For more details about the JDK support see [this issue](https://github.com/sherter/google-java-format-gradle-plugin/issues/58)
and to know more about the Google Java code Style see [this reference](https://google.github.io/styleguide/javaguide.html)

## Support
- [3BQ8qbn8hLdmBKEjt1Hj1Z6SiDsnjJurfU](bitcoin:3BQ8qbn8hLdmBKEjt1Hj1Z6SiDsnjJurfU)
- [liberapay.com/vincenzopalazzo](https://liberapay.com/vincenzopalazzo)
- [Github support](https://github.com/sponsors/vincenzopalazzo)
- [buymeacoffee](https://www.buymeacoffee.com/vincenzopalazzo)

P.S: Lightning donation is coming ;-)

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
