CURR=$(basename $(pwd))

javac -d bin *.java
# java -classpath ./bin $CURR.Program --profile=production
java -classpath ./bin $CURR.Program --profile=dev
rm -rf ./bin