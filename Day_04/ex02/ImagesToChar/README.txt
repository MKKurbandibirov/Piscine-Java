rm -rf lib && rm -rf target && mkdir lib && mkdir target
curl https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar --output lib/jcommander-1.82.jar
curl https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar --output lib/JCDP-4.0.2.jar
mkdir target/com/

javac -d target -sourcepath src/java -cp lib/JCDP-4.0.2.jar:lib/jcommander-1.82.jar:. `find . -name "*.java"` && cp -r src/resources target/resources


jar xf lib/jcommander-1.82.jar com/
jar xf lib/JCDP-4.0.2.jar com/
mv com target
rm -rf com/

jar cvfm target/images-to-chars-printer.jar src/manifest.txt -C target/ .
java -jar target/images-to-chars-printer.jar --white=BLUE --black=YELLOW