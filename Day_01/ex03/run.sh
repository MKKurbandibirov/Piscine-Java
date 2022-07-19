CURR=$(basename $(pwd))

javac -d bin *.java
java -classpath ./bin $CURR.Program
rm -rf ./bin