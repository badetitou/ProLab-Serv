FROM centos
MAINTAINER Benoît Verhaeghe <badetitou@gmail.com>
RUN yum upgrade -y && yum clean all
RUN yum -y install java-1.7.0-openjdk && yum clean all
RUN yum -y install tar && yum clean all
RUN yum -y install sqlite && yum clean all

ADD http://apache.mirrors.ovh.net/ftp.apache.org//dist/tomcat/tomcat-7/v7.0.65/bin/apache-tomcat-7.0.65.tar.gz /tmp/
RUN mkdir /opt/tomcat
RUN tar -xzvf /tmp/apache-tomcat-7.0.65.tar.gz --directory /opt/tomcat/ --strip 1 && rm /tmp/apache-tomcat-7.0.65.tar.gz

ADD target/Prolab.war /opt/tomcat/webapps/

EXPOSE 8080

CMD /opt/tomcat/bin/catalina.sh start && tail -f /opt/tomcat/logs/catalina.out
