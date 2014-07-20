This folder contains four tools.

Each tool has a README-<tool>.txt file that tells you how to run the
tool.

Use the <tool>.properties file to change the parameters that are passed
to the tool.

You will need Ant to run the tool.

This folder also contains a ./aut/ folder with a "Hello World" example.
All the .properties files are designed for this example.

To get started, first run the JFCRipper. If you have Ant and Java
installed correctly, the tool should run without any problems.

For the "Hello World" example,
try the following sequence of commands:

ant -Dproperties=jfcripper.properties -f jfcripper.xml
ant -v -f GUIStructure2GraphConvert.xml
ant -v -f TestCaseGenerator.xml
ant -Dproperties=jfcreplayer.properties -f jfcreplayer.xml

