# Automated Announcements
> :speech_balloon: **| Spigot plugin to say an announcement every x milliseconds.**

## Installing
Visit [here](https://github.com/auguwu/AutoAnnouncer/releases) and grab the latest JAR file.

Before you run the server, make a `config.yml` file in the `plugins/AutoAnnouncer`. Right now, it doesn't auto create it (make a PR if you have a solution!)

### Building
You will need Java 1.8 or higher on your machine.

- Clone the repository (``git clone https://github.com/auguwu/AutoAnnouncer.git``)
- Change the directory of the cloned repo
- Run `gradlew shadowJar` (or `./gradlew shadowJar`) to build the JAR file
- Grab the JAR file from `build/libs` and do the process in the [installation](#installing) section!

## Credits
- [KurozeroPB](https://github.com/KurozeroPB) **-** The timer core itself
- [WorldEdit](https://github.com/EngineHub) **-** The configuration loader

## License
**AutoAnnouncer** is released under the **MIT** License. Read [here](/LICENSE) for more information.