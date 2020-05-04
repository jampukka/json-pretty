# json-pretty

Simple streaming library for pretty-printing large(ish) JSON files.
Powered by [Jackson](https://github.com/FasterXML/jackson)

# building

`mvn clean install`

# usage

## stdin to stdout
`java -jar json-pretty.jar < /path/file_in.json  > /path/file_out.json`

## File to stdout
`java -jar json-pretty.jar /path/file_in.json > /path/file_out.json`

## File to File
`java -jar json-pretty.jar /path/file_in.json /path/file_out.json`

