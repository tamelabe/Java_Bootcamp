javac -d ./target -sourcepath src/java src/java/edu/school21/printer/app/Program.java
cp -R src/resources target/.
jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .
chmod u+x target/images-to-chars-printer.jar
java -jar target/images-to-chars-printer.jar _ O
