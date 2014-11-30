<h1>SkypeNet</h1>

Fun little Skype bot written in Java.
Sky[pe]Net will soon take over :O

<h3>Installation</h3>
Clone repo, then run mvn clean install.

Due to new Skype API limitations, SkypeNet currently uses a virtual keyboard to send messages. Because of this, you will need an instance of Skype open at all times, with the caret on the message box. Hopefully Microsoft reimplements messaging into the API, making the bot more useful.

<h3>Adding your own commands</h3>
For now, after cloning the repo, add your command class into the me.rotn.java.skypenet package. The command must implement IBotCommand, and it is self explanitory from there.

<h3>Features</h3>
<li>
  <ul>Easy implementation of new commands (External module planned for future)</ul>
  <ul>Commands can use all of Java to do their magic, virtually no limitations!</ul>
</li>

<h3>Credits</h3>
SkypeNet uses taksan's implemetation of the Skype API, found here: https://github.com/taksan/skype-java-api
The Cleverbot feature uses Pierre-David Bélanger's ChatterBot API: https://github.com/pierredavidbelanger
