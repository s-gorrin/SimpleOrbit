#!/bin/bash
mvn clean
mvn checkstyle:check
mvn spotbugs:spotbugs
mvn findbugs:findbugs
