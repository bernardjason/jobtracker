example project for next time I need it. Simple spring app with basic authentication either
with db or oauth. Login with user jason and password of password. Single table create a job, works single page after logon.

setup with
```
spring init --dependencies web,data-jpa,thymeleaf,h2 jobtracker
```

### SQLITE
needs to be run with sqlite profile

```
java -Dspring.profiles.active=sqlite -jar target/jobtracker-0.0.1-SNAPSHOT.jar
```

or
```
./mvnw spring-boot:run -Dspring-boot.run.profiles=sqlite
```

### test authentication server
to run the oauth server
```
cd springbootoauth
./mvnw spring-boot:run -Dspring-boot.run.profiles=sqlite
```

### authenticate against DB rather than dummy oath server
application.properties has value
oauth.enabled=true

if you want to authenticate against DB then set to false

if you want to authentcate against sqlite db then you'll need to apply schema.sql 
manually for authentication

### intellij
to get devtools working from Intellij
https://mkyong.com/spring-boot/intellij-idea-spring-boot-template-reload-is-not-working/

You need to turn on a couple of features in IntelliJ to make this work.
First, there's a project specific setting which you would need to apply on any project you want to use devtools in. Go to Preferences > Compiler and enable "Make project automatically."
The next setting is an IDEA registry setting that applies to all projects. 
In macOS (OSX), press Shift+Command+A (Shift+Ctrl+A in Windows) 
Type "Registry" in the search box that appears, and select the registry to open it.
Lookup compiler.automake.allow.when.app.running and enable it.
After that, restart your app. You will notice that the project keeps rebuilding with every change you make. When you check out the result in the browser, you will see both static files and code have been updated.

