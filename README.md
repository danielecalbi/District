**Entrypoints**

There are two controller classes in where are stored all the entrypoints: *SchoolController.java* and *StudentController.java*.

**How to run**

The project is built with Gradle and Docker. So you should have installed and configured both in your environment or in your IDE.

As first step you have to run the *build* Gradle task in order to generate the project distribution jar (district.jar).

After this, you should open a terminal and put your command terminal on the root of district project, running this command:

    docker-compose up

With this command, you will put in local all the required containers:

    - district (container about schools and students java project);
    - district_db (container about schools and students postgresql database);
    - container-pg-admin-district (container about a postgresql UI - not full tested!).

**Tests**

There is also a suite of tests, covering **Service.java* and **Mapper.java* classes. 