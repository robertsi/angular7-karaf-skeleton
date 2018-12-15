# Angular7 Frontend + Karaf 4.2.1 based backed with REST and Hibernate skeleton

Skeleton for Angular7 frontend with REST service and Hibernate for backed all running in Karaf OSGI container.

# Build

mvn clean install

# Installation

 Download and start [Karaf 4.2.1](http://www.apache.org/dyn/closer.lua/karaf/4.2.1/apache-karaf-4.2.1.zip).
 
 ```Shell
 karaf 
 feature:repo-add mvn:com.example.skeleton.angularkaraf/features/1.0.0-SNAPSHOT/xml/features
 feature:install angularkaraf
 ``` 
 
 # Test
 
 Open the UI in your browser <http://localhost:8181/angular-frontend> and work with the tasks.