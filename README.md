# javaflagslogger

A very simple code to list the JVM configurations inside an AWS Lambda Function.

If one wants to add/change JMV flags, it is just necessary to add it to JAVA_TOOL_OPTIONS lambda environment variable.

For example, if we run this code to get the JVM flags before adding the JAVA_TOOL_OPTIONS, you will get something similar to this:

~~~
	
2020-07-27T14:58:52.978-04:00
	
### JVM PARAMETERS ###
	
2020-07-27T14:58:52.978-04:00
	
"-XX:MaxHeapSize\u003d445645k"
	
2020-07-27T14:58:52.978-04:00
	
"-XX:MaxMetaspaceSize\u003d52429k"
	
2020-07-27T14:58:52.978-04:00
	
"-XX:ReservedCodeCacheSize\u003d26214k"
	
2020-07-27T14:58:52.978-04:00
	
"-Xshare:on"
	
2020-07-27T14:58:52.978-04:00
	
"-XX:-TieredCompilation"
	
2020-07-27T14:58:52.978-04:00
	
"-XX:+UseSerialGC"
	
2020-07-27T14:58:52.978-04:00
	
"-Djava.net.preferIPv4Stack\u003dtrue"
	
2020-07-27T14:58:52.978-04:00
	
### ------ ###
~~~


If we set the environment variable like below:

Key | Vaue 
----|------
JAVA_TOOL_OPTIONS | -XX:+AlwaysPreTouch -XX:MaxHeapSize=40000k


we are then get these results (notice AlwaysPreTouch and MaxHeapSize)

~~~
2020-07-27T13:30:08.415-04:00
	
### JVM PARAMETERS ###
	
2020-07-27T13:30:08.415-04:00
	
"-XX:+AlwaysPreTouch"
	
2020-07-27T13:30:08.415-04:00
	
"-XX:MaxHeapSize\u003d40000k"
	
2020-07-27T13:30:08.415-04:00
	
"-XX:MaxHeapSize\u003d445645k"
	
2020-07-27T13:30:08.415-04:00
	
"-XX:MaxMetaspaceSize\u003d52429k"
	
2020-07-27T13:30:08.415-04:00
	
"-XX:ReservedCodeCacheSize\u003d26214k"
	
2020-07-27T13:30:08.415-04:00
	
"-Xshare:on"
	
2020-07-27T13:30:08.415-04:00
	
"-XX:-TieredCompilation"
	
2020-07-27T13:30:08.415-04:00
	
"-XX:+UseSerialGC"
	
2020-07-27T13:30:08.415-04:00
	
"-Djava.net.preferIPv4Stack\u003dtrue"
	
2020-07-27T13:30:08.415-04:00
	
### ------ ###
~~~

## To deploy
1. Build
~~~
mvn clean package shade:shade
~~~
2. Update the Lambda function code
From the target folder, run the following
~~~
aws lambda update-function-code --function-name <<YOUR LAMBDA FUNCION NAME>> --zip-file fileb://JavaFlagsLogger-1.0.jar
~~~
3. Update the Lambda handler with: `JavaFlagsLogger::handleRequest`

## Remarks
This was designed for Java 8
