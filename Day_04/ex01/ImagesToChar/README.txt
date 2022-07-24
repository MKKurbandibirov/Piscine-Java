rm -rf target && mkdir target && javac -sourcepath src/java `find . -name "*.java"` -d target && cp -r src/resources target/resources
jar cvfm target/images-to-chars-printer.jar src/manifest.txt -C target .
java -jar target/images-to-chars-printer.jar . 0