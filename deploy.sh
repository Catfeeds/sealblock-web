#!/bin/sh
CURPATH=$(dirname $0)
echo "$(date +'%Y-%m-%d %H:%M') run $0" >> ${CURPATH}/log_deploy.log

echo "Runing With: svn up "
svn up

echo "Begin to deploy to ${TOMCAT_HOME}"
echo "Command: mvn clean package -Pprod"
mvn clean package -Pprod

echo "Kill Tomcat Task: ${TOMCAT_HOME}"
TOMCAT_PIDS=$(ps -ef|grep -E "tomcat|cronolog|catalina" |grep "${TOMCAT_HOME}" | grep -v grep| awk '{print $2}'| xargs echo)
echo "TOMCAT_PIDS: [kill -9 $TOMCAT_PIDS]"
kill -9 $TOMCAT_PIDS

echo "Clean Target: rm -rf ${TOMCAT_HOME}/webapps/sealblock-web*"
rm -rf ${TOMCAT_HOME}/webapps/sealblock-web*

echo "Copying Files: cp ${CURPATH}/target/sealblock-web.war ${TOMCAT_HOME}/webapps/"
cp ${CURPATH}/target/sealblock-web.war ${TOMCAT_HOME}/webapps/

echo "Startup Tomcat: sh ${TOMCAT_HOME}/bin/startup.sh"
sh ${TOMCAT_HOME}/bin/startup.sh

echo "Done with: ${TOMCAT_HOME}"
exit 0
