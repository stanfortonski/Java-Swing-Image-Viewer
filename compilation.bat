@echo off

javac ImageViewer.java
javac viewer/ViewerFrame.java
jar cfm ImageViewer.jar manifest.txt *.class viewer/*.class

pause