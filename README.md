# Troubleshoot-Agent

## How to use

1.Get the Troubleshooting agent 

`https://github.com/ShehanPerera/Troubleshoot-Agent.git`

2.Install the package 

`mvn clean install`

3.Copy the `troubleshooting-agent-1.0-SNAPSHOT.jar` from `/Troubleshoot-Agent/troubleshooting-agent/target` and 
`troubleshooting-logger-1.0-SNAPSHOT.jar` from `/Troubleshoot-Agent/troubleshooting-logger/target` to your progarm runing directory.

4.run program with troubleshoot-agent 

`java -javaagent:troubleshooting-agent-1.0-SNAPSHOT.jar ** `
