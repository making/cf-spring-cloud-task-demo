# Spring Cloud Task with [Cloud Foundry V3 API](http://v3-apidocs.cloudfoundry.org/)

Run Spring Cloud Task as a Cloud Foundry's short lived task

```
cf install-plugin https://github.com/cloudfoundry/v3-cli-plugin/releases/download/0.6.5/v3-cli-plugin.osx
./mvnw clean package -DskipTests=true
jar -uvf target/cf-spring-cloud-task-demo-0.0.1-SNAPSHOT.jar Procfile 
cf v3-push helloworld -b java_buildpack_offline -p target/cf-spring-cloud-task-demo-0.0.1-SNAPSHOT.jar
cf create-service p-mysql 100mb demo-db
cf v3-bind-service helloworld demo-db 
cf v3-run-task helloworld hello ".java-buildpack/open_jdk_jre/bin/java org.springframework.boot.loader.JarLauncher"
```

Tested with

* Pivotal Cloud Foundry: 1.8
* CC API Version: 2.58.0

## Run Task

Run task 1st

``` console
$ cf v3-run-task helloworld hello ".java-buildpack/open_jdk_jre/bin/java org.springframework.boot.loader.JarLauncher"
OK

Running task hello on app helloworld...

Tailing logs for app helloworld...

2016-10-28T01:13:20.94+0900 [APP/TASK/hello/0]OUT Creating container
2016-10-28T01:13:21.22+0900 [APP/TASK/hello/0]OUT Successfully created container
2016-10-28T01:13:24.17+0900 [APP/TASK/hello/0]OUT   .   ____          _            __ _ _
2016-10-28T01:13:24.17+0900 [APP/TASK/hello/0]OUT  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
2016-10-28T01:13:24.17+0900 [APP/TASK/hello/0]OUT ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
2016-10-28T01:13:24.17+0900 [APP/TASK/hello/0]OUT  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
2016-10-28T01:13:24.17+0900 [APP/TASK/hello/0]OUT   '  |____| .__|_| |_|_| |_\__, | / / / /
2016-10-28T01:13:24.17+0900 [APP/TASK/hello/0]OUT  =========|_|==============|___/=/_/_/_/
2016-10-28T01:13:24.18+0900 [APP/TASK/hello/0]OUT  :: Spring Boot ::        (v1.4.1.RELEASE)
2016-10-28T01:13:24.34+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:24.338  INFO 7 --- [           main] pertySourceApplicationContextInitializer : Adding 'cloud' PropertySource to ApplicationContext
2016-10-28T01:13:24.42+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:24.421  INFO 7 --- [           main] nfigurationApplicationContextInitializer : Adding cloud service auto-reconfiguration to ApplicationContext
2016-10-28T01:13:24.43+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:24.437  INFO 7 --- [           main] c.e.CfSpringCloudTaskDemoApplication     : Starting CfSpringCloudTaskDemoApplication on 257dc279-7671-4457-8b22-fdd30a589a64 with PID 7 (/home/vcap/app/BOOT-INF/classes started by vcap in /home/vcap/app)
2016-10-28T01:13:24.43+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:24.438  INFO 7 --- [           main] c.e.CfSpringCloudTaskDemoApplication     : The following profiles are active: cloud
2016-10-28T01:13:24.49+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:24.498  INFO 7 --- [           main] s.c.a.AnnotationConfigApplicationContext : Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@1698c449: startup date [Thu Oct 27 16:13:24 UTC 2016]; root of context hierarchy
2016-10-28T01:13:25.27+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:25.278  INFO 7 --- [           main] urceCloudServiceBeanFactoryPostProcessor : Auto-reconfiguring beans of type javax.sql.DataSource
2016-10-28T01:13:25.29+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:25.298  INFO 7 --- [           main] o.c.r.o.s.c.s.r.PooledDataSourceCreator  : Found Tomcat high-performance connection pool on the classpath. Using it for DataSource connection pooling.
2016-10-28T01:13:25.32+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:25.325  INFO 7 --- [           main] urceCloudServiceBeanFactoryPostProcessor : Reconfigured bean dataSource into singleton service connector org.apache.tomcat.jdbc.pool.DataSource@90f6bfd{ConnectionPool[defaultAutoCommit=null; defaultReadOnly=null; defaultTransactionIsolation=-1; defaultCatalog=null; driverClassName=com.mysql.jdbc.Driver; maxActive=4; maxIdle=100; minIdle=0; initialSize=0; maxWait=30000; testOnBorrow=true; testOnReturn=false; timeBetweenEvictionRunsMillis=5000; numTestsPerEvictionRun=0; minEvictableIdleTimeMillis=60000; testWhileIdle=false; testOnConnect=false; password=********; url=jdbc:mysql://10.0.0.67:3306/cf_1fe43b24_c117_4fe6_9ec1_aaee382309c3?user=Of5xdDWIWkuG7mg6&password=6q34ORgMePywJIzk; username=null; validationQuery=/* ping */ SELECT 1; validationQueryTimeout=-1; validatorClassName=null; validationInterval=3000; accessToUnderlyingConnectionAllowed=true; removeAbandoned=false; removeAbandonedTimeout=60; logAbandoned=false; connectionProperties=null; initSQL=null; jdbcInterceptors=null; jmxEnabled=true; fairQueue=true; useEquals=true; abandonWhenPercentageFull=0; maxAge=0; useLock=false; dataSource=null; dataSourceJNDI=null; suspectTimeout=0; alternateUsernameAllowed=false; commitOnReturn=false; rollbackOnReturn=false; useDisposableConnectionFacade=true; logValidationErrors=false; propagateInterruptState=false; ignoreExceptionOnPreLoad=false; }
2016-10-28T01:13:25.50+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:25.500  WARN 7 --- [           main] o.a.tomcat.jdbc.pool.ConnectionPool      : maxIdle is larger than maxActive, setting maxIdle to: 4
2016-10-28T01:13:25.85+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:25.856 DEBUG 7 --- [           main] o.s.c.t.c.SimpleTaskConfiguration        : Using org.springframework.cloud.task.configuration.DefaultTaskConfigurer TaskConfigurer
2016-10-28T01:13:25.97+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:25.971 DEBUG 7 --- [           main] o.s.c.t.r.s.TaskRepositoryInitializer    : Initializing task schema for mysql database
2016-10-28T01:13:25.97+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:25.975  INFO 7 --- [           main] o.s.jdbc.datasource.init.ScriptUtils     : Executing SQL script from class path resource [org/springframework/cloud/task/schema-mysql.sql]
2016-10-28T01:13:26.79+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:26.792  INFO 7 --- [           main] o.s.jdbc.datasource.init.ScriptUtils     : Executed SQL script from class path resource [org/springframework/cloud/task/schema-mysql.sql] in 817 ms.
2016-10-28T01:13:27.03+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:27.035  INFO 7 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2016-10-28T01:13:27.04+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:27.041  INFO 7 --- [           main] o.s.c.support.DefaultLifecycleProcessor  : Starting beans in phase 0
2016-10-28T01:13:27.06+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:27.061 DEBUG 7 --- [           main] o.s.c.t.r.support.SimpleTaskRepository   : Creating: TaskExecution{executionId=1, exitCode=null, taskName='helloworld:cloud', startTime=Thu Oct 27 16:13:27 UTC 2016, endTime=null, exitMessage='null', arguments=[]}
2016-10-28T01:13:27.07+0900 [APP/TASK/hello/0]OUT Hello World!
2016-10-28T01:13:27.07+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:27.077 DEBUG 7 --- [           main] o.s.c.t.r.support.SimpleTaskRepository   : Updating: TaskExecution with executionId=1 with the following {exitCode=0, endTime=Thu Oct 27 16:13:27 UTC 2016, exitMessage='null'}
2016-10-28T01:13:27.09+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:27.093  INFO 7 --- [           main] s.c.a.AnnotationConfigApplicationContext : Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@1698c449: startup date [Thu Oct 27 16:13:24 UTC 2016]; root of context hierarchy
2016-10-28T01:13:27.09+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:27.094  INFO 7 --- [           main] o.s.c.support.DefaultLifecycleProcessor  : Stopping beans in phase 0
2016-10-28T01:13:27.09+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:27.096  INFO 7 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Unregistering JMX-exposed beans on shutdown
2016-10-28T01:13:27.09+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:27.097  INFO 7 --- [           main] c.e.CfSpringCloudTaskDemoApplication     : Started CfSpringCloudTaskDemoApplication in 3.496 seconds (JVM running for 4.065)
2016-10-28T01:13:27.11+0900 [APP/TASK/hello/0]OUT Exit status 0
2016-10-28T01:13:27.14+0900 [APP/TASK/hello/0]OUT Destroying container
Task 257dc279-7671-4457-8b22-fdd30a589a64 successfully completed.
```

Run task 2nd

``` console
$ cf v3-run-task helloworld hello ".java-buildpack/open_jdk_jre/bin/java org.springframework.boot.loader.JarLauncher"
OK

Running task hello on app helloworld...

Tailing logs for app helloworld...

2016-10-28T01:13:41.66+0900 [APP/TASK/hello/0]OUT Creating container
2016-10-28T01:13:41.92+0900 [APP/TASK/hello/0]OUT Successfully created container
2016-10-28T01:13:44.86+0900 [APP/TASK/hello/0]OUT   .   ____          _            __ _ _
2016-10-28T01:13:44.86+0900 [APP/TASK/hello/0]OUT  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
2016-10-28T01:13:44.86+0900 [APP/TASK/hello/0]OUT ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
2016-10-28T01:13:44.86+0900 [APP/TASK/hello/0]OUT  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
2016-10-28T01:13:44.86+0900 [APP/TASK/hello/0]OUT   '  |____| .__|_| |_|_| |_\__, | / / / /
2016-10-28T01:13:44.86+0900 [APP/TASK/hello/0]OUT  =========|_|==============|___/=/_/_/_/
2016-10-28T01:13:44.87+0900 [APP/TASK/hello/0]OUT  :: Spring Boot ::        (v1.4.1.RELEASE)
2016-10-28T01:13:45.00+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:45.006  INFO 7 --- [           main] pertySourceApplicationContextInitializer : Adding 'cloud' PropertySource to ApplicationContext
2016-10-28T01:13:45.09+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:45.094  INFO 7 --- [           main] nfigurationApplicationContextInitializer : Adding cloud service auto-reconfiguration to ApplicationContext
2016-10-28T01:13:45.11+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:45.111  INFO 7 --- [           main] c.e.CfSpringCloudTaskDemoApplication     : Starting CfSpringCloudTaskDemoApplication on 289cf3cf-2544-4b83-aa79-872afa9036d8 with PID 7 (/home/vcap/app/BOOT-INF/classes started by vcap in /home/vcap/app)
2016-10-28T01:13:45.11+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:45.111  INFO 7 --- [           main] c.e.CfSpringCloudTaskDemoApplication     : The following profiles are active: cloud
2016-10-28T01:13:45.17+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:45.173  INFO 7 --- [           main] s.c.a.AnnotationConfigApplicationContext : Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@1698c449: startup date [Thu Oct 27 16:13:45 UTC 2016]; root of context hierarchy
2016-10-28T01:13:46.00+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:46.006  INFO 7 --- [           main] urceCloudServiceBeanFactoryPostProcessor : Auto-reconfiguring beans of type javax.sql.DataSource
2016-10-28T01:13:46.02+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:46.024  INFO 7 --- [           main] o.c.r.o.s.c.s.r.PooledDataSourceCreator  : Found Tomcat high-performance connection pool on the classpath. Using it for DataSource connection pooling.
2016-10-28T01:13:46.05+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:46.050  INFO 7 --- [           main] urceCloudServiceBeanFactoryPostProcessor : Reconfigured bean dataSource into singleton service connector org.apache.tomcat.jdbc.pool.DataSource@90f6bfd{ConnectionPool[defaultAutoCommit=null; defaultReadOnly=null; defaultTransactionIsolation=-1; defaultCatalog=null; driverClassName=com.mysql.jdbc.Driver; maxActive=4; maxIdle=100; minIdle=0; initialSize=0; maxWait=30000; testOnBorrow=true; testOnReturn=false; timeBetweenEvictionRunsMillis=5000; numTestsPerEvictionRun=0; minEvictableIdleTimeMillis=60000; testWhileIdle=false; testOnConnect=false; password=********; url=jdbc:mysql://10.0.0.67:3306/cf_1fe43b24_c117_4fe6_9ec1_aaee382309c3?user=Of5xdDWIWkuG7mg6&password=6q34ORgMePywJIzk; username=null; validationQuery=/* ping */ SELECT 1; validationQueryTimeout=-1; validatorClassName=null; validationInterval=3000; accessToUnderlyingConnectionAllowed=true; removeAbandoned=false; removeAbandonedTimeout=60; logAbandoned=false; connectionProperties=null; initSQL=null; jdbcInterceptors=null; jmxEnabled=true; fairQueue=true; useEquals=true; abandonWhenPercentageFull=0; maxAge=0; useLock=false; dataSource=null; dataSourceJNDI=null; suspectTimeout=0; alternateUsernameAllowed=false; commitOnReturn=false; rollbackOnReturn=false; useDisposableConnectionFacade=true; logValidationErrors=false; propagateInterruptState=false; ignoreExceptionOnPreLoad=false; }
2016-10-28T01:13:46.22+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:46.226  WARN 7 --- [           main] o.a.tomcat.jdbc.pool.ConnectionPool      : maxIdle is larger than maxActive, setting maxIdle to: 4
2016-10-28T01:13:46.58+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:46.583 DEBUG 7 --- [           main] o.s.c.t.c.SimpleTaskConfiguration        : Using org.springframework.cloud.task.configuration.DefaultTaskConfigurer TaskConfigurer
2016-10-28T01:13:46.62+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:46.627 DEBUG 7 --- [           main] o.s.c.t.r.s.TaskRepositoryInitializer    : Initializing task schema for mysql database
2016-10-28T01:13:46.63+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:46.632  INFO 7 --- [           main] o.s.jdbc.datasource.init.ScriptUtils     : Executing SQL script from class path resource [org/springframework/cloud/task/schema-mysql.sql]
2016-10-28T01:13:46.72+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:46.726  INFO 7 --- [           main] o.s.jdbc.datasource.init.ScriptUtils     : Executed SQL script from class path resource [org/springframework/cloud/task/schema-mysql.sql] in 93 ms.
2016-10-28T01:13:47.01+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:47.016  INFO 7 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2016-10-28T01:13:47.02+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:47.023  INFO 7 --- [           main] o.s.c.support.DefaultLifecycleProcessor  : Starting beans in phase 0
2016-10-28T01:13:47.04+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:47.043 DEBUG 7 --- [           main] o.s.c.t.r.support.SimpleTaskRepository   : Creating: TaskExecution{executionId=2, exitCode=null, taskName='helloworld:cloud', startTime=Thu Oct 27 16:13:47 UTC 2016, endTime=null, exitMessage='null', arguments=[]}
2016-10-28T01:13:47.05+0900 [APP/TASK/hello/0]OUT Hello World!
2016-10-28T01:13:47.06+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:47.060 DEBUG 7 --- [           main] o.s.c.t.r.support.SimpleTaskRepository   : Updating: TaskExecution with executionId=2 with the following {exitCode=0, endTime=Thu Oct 27 16:13:47 UTC 2016, exitMessage='null'}
2016-10-28T01:13:47.08+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:47.080  INFO 7 --- [           main] s.c.a.AnnotationConfigApplicationContext : Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@1698c449: startup date [Thu Oct 27 16:13:45 UTC 2016]; root of context hierarchy
2016-10-28T01:13:47.08+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:47.082  INFO 7 --- [           main] o.s.c.support.DefaultLifecycleProcessor  : Stopping beans in phase 0
2016-10-28T01:13:47.08+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:47.083  INFO 7 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Unregistering JMX-exposed beans on shutdown
2016-10-28T01:13:47.08+0900 [APP/TASK/hello/0]OUT 2016-10-27 16:13:47.084  INFO 7 --- [           main] c.e.CfSpringCloudTaskDemoApplication     : Started CfSpringCloudTaskDemoApplication in 2.837 seconds (JVM running for 3.429)
2016-10-28T01:13:47.10+0900 [APP/TASK/hello/0]OUT Exit status 0
2016-10-28T01:13:47.16+0900 [APP/TASK/hello/0]OUT Destroying container
Task 289cf3cf-2544-4b83-aa79-872afa9036d8 successfully completed.
```

## Delete Task


``` console
$ cf curl /v3/service_bindings
{
   "pagination": {
      "total_results": 1,
      "total_pages": 1,
      "first": {
         "href": "/v3/service_bindings?page=1&per_page=50"
      },
      "last": {
         "href": "/v3/service_bindings?page=1&per_page=50"
      },
      "next": null,
      "previous": null
   },
   "resources": [
      {
         "guid": "88684788-de01-41c7-bb27-d399c266350a",
         "type": "app",
         "data": {
            "credentials": {
               "redacted_message": "[PRIVATE DATA HIDDEN IN LISTS]"
            },
            "syslog_drain_url": null,
            "volume_mounts": []
         },
         "created_at": "2016-10-27T16:13:13Z",
         "updated_at": null,
         "links": {
            "self": {
               "href": "/v3/service_bindings/88684788-de01-41c7-bb27-d399c266350a"
            },
            "service_instance": {
               "href": "/v2/service_instances/1fe43b24-c117-4fe6-9ec1-aaee382309c3"
            },
            "app": {
               "href": "/v3/apps/d03d63d0-e5ce-4a06-adb2-e8646a2ada8d"
            }
         }
      }
   ]
}
$ cf curl -X DELETE /v3/service_bindings/88684788-de01-41c7-bb27-d399c266350a

$ cf v3-delete helloworld
Deleting app helloworld...
OK

```