# AlphaOthello [![Build Status](https://travis-ci.org/ITU-2019/AlphaOthello.svg?branch=develop)](https://travis-ci.org/ITU-2019/AlphaOthello)

A game AI for Othello.

![Othello](https://www.odense-marcipan.dk/imagegen.ashx?image=/media/986752/othellokage-1.jpg "Othello")


## Compile

```bash
javac *.java
```

## Run Commands

Playing human against DumAI:

```bash
java Othello human DumAI
```

Playing AI1 against AI2 on a 6x6 board:

```bash
java Othello AI1 AI2 6
```

### Gradle

Remove build folder

```bash
$ gradle clean
```

Build files and run test

```bash
$ gradle build
```

Build files and without running tests

```bash
$ gradle build -x test
```

Run game (change `build.gradle` to change starting parameters)

```bash
$ gradle run
```


## License

<a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/"><img alt="Creative Commons Licence" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-nd/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a>.
