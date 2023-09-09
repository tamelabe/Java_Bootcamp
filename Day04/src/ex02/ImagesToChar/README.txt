cd target
jar xf ../lib/jcommander-1.72.jar
jar xf ../lib/JCDP-4.0.0.jar
rm -rf META-INF
cd ..

javac -d target -sourcepath src/java -cp lib/\* src/java/edu/school21/printer/app/Program.java src/java/edu/school21/printer/logic/*.java
cp -R src/resources ./target/resources
jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .
chmod u+x target/images-to-chars-printer.jar
java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN
