Estatio: an open source estate management system.
=================================================

Estatio is modern and flexible property management software. It offers real estate professionals and service providers the power and flexibility to manage their business in a superior, flexible and cost-effective manner.

## Screenshots ##

The following screenshots (taken 13 december 2014) correspond to the business logic in Estatio's [domain object model](
https://github.com/estatio/estatio/tree/master/dom/src/main/java/org/estatio/dom).

<img src="https://raw.github.com/estatio/estatio/master/docs/screenshots/AllProperties.png" width=600"/>

<img src="https://raw.github.com/estatio/estatio/master/docs/screenshots/AllProperties-Map.png" width=600"/>

<img src="https://raw.github.com/estatio/estatio/master/docs/screenshots/Lease.png" width=600"/>

<img src="https://raw.github.com/estatio/estatio/master/docs/screenshots/LeaseItem.png" width=600"/>

<img src="https://raw.github.com/estatio/estatio/master/docs/screenshots/Invoice.png" width=600"/>

## Building Estatio ##

### Prereqs ###

Estatio runs on Java and is built with [Maven](http://maven.apache.org).  The source code is managed using [git](https://help.github.com/articles/set-up-git), and is held on [github](http://github.com).

If you don't already have them installed, install Java (JDK 6 or later), Maven (3.0.4 or later), and git.

### Download and build Apache Isis ###

Estatio currently uses the snapshot version of [Apache Isis](http://isis.apache.org), and so Isis must be built from source.

Estatio also maintains its [own copy of the Isis codebase](http://github.com/estatio/isis), and so this is what you should download.  (Normally there is very little, if any, difference between the Estatio's copy of Isis and the [official Isis codebase](http://github.com/apache/isis))

Download using git:

    mkdir -p github/estatio
    cd github/estatio
    git clone https://github.com/estatio/isis.git
    cd isis

and build using maven:

    mvn clean install

The clone is approx 83Mb, and takes approx 10 minutes to build.

### Download and build Estatio ###

Estatio itself is also built using maven.

Download using git:
 
    cd ..
    git clone https://github.com/estatio/estatio.git
    cd estatio

and build using maven:

    mvn clean install -Pjetty-console

The clone is approx 3Mb, and takes approximately 1 minute to build.

## Configure Estatio (JDBC URL) ##

Before Estatio can be run, you must configure its JDBC URL; typically this lives in the `webapp/src/main/webapp/WEB-INF/persistor.properties` properties file.

You can do this most easily by copying a set of property entries from `webapp/src/main/webapp/WEB-INF/persistor-SAMPLE.properties`.

For example, to run against an in-memory HSQLDB, the `persistor.properties` file should consist of:

    isis.persistor.datanucleus.impl.javax.jdo.option.ConnectionDriverName=org.hsqldb.jdbcDriver
    isis.persistor.datanucleus.impl.javax.jdo.option.ConnectionURL=jdbc:hsqldb:mem:test
    isis.persistor.datanucleus.impl.javax.jdo.option.ConnectionUserName=sa
    isis.persistor.datanucleus.impl.javax.jdo.option.ConnectionPassword=
 
The JDBC driver for HSQLDB is on the classpath.  If you want to connect to some other database, be sure to update the `pom.xml` to add the driver as a `<dependency>`.

## Run Estatio ##

You can run Estatio either using the standalone (self-hosting) version of the WAR, or using `mvn jetty plugin`.

### Running as a self-hosting JAR

Run using:

    java -jar webapp/target/estatio-webapp-0.0.1-SNAPSHOT-jetty-console.war

and press the 'start button'.

Then browse to:
 
    http://localhost:8080/wicket/

### Running through Maven

Run using:

    cd webapp
    mvn jetty:run

Then browse to:

    http://localhost:8080/estatio-webapp/wicket/

## Use Estatio ##

* Login using estatio-admin/pass or estatio-user/pass.

* Install some demo fixtures:

    Administration > Install Demo Fixtures

* Run a script to setup invoices:

    Administration > Run script: GenerateTopModelInvoice

* Take a look around :-)

If you encounter any bugs, do [let us know](https://github.com/estatio/estatio/blob/master/pom.xml#L52).

## Implementation

This product uses [Apache Isis](http://isis.apache.org), a software framework 
developed at [The Apache Software Foundation](http://www.apache.org/).

## Support

You are free to adapt or extend Estatio to your needs.  If you would like assistance in doing so, go to [www.estatio.org](http://www.estatio.org).

You can find plenty of help on using Apache Isis at the [Isis mailing lists](http://isis.apache.org/support.html).  There is also extensive [online documentation](http://isis.apache.org/documentation.html).

## How to generate the AsciiDoc pages

Just run:

<pre>
    cd adocs/documentation
    mvn site
</pre>

The `.html` will be generated in `target/site`.  It should be possible to load the HTML straight from the directory.  Alternatively, load from a webserver, eg:

<pre>
    python -m SimpleHTTPServer
</pre>

and browse to http://localhost:8000.

The mvn script also generates docbook XML and PDF, but there are some caveats:

* the PDF currently does not include images at all.

* Using [asciidoctor-fopub](https://github.com/asciidoctor/asciidoctor-fopub) the XML can be converted to PDF; however there are currently some issues with images being scaled correctly.


## Thanks

Thanks to

* <img src="https://raw.github.com/estatio/estatio/master/codequality/logoClover.png" width="100"/> [Atlassian](https://www.atlassian.com) for providing an open source [Clover](https://www.atlassian.com/software/clover/overview/) license 
* [Headway Software](http://structure101.com/contact/) for providing an open source [Structure 101](http://structure101.com/) license

## Legal Stuff ##

Copyright 2012-2015 [Eurocommercial Properties NV](http://www.eurocommercialproperties.com) 

Licensed under [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)

