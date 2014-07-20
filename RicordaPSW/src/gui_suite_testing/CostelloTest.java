package gui_suite_testing;

import java.io.File;

import junit.extensions.abbot.ScriptFixture;
import junit.extensions.abbot.ScriptTestSuite;
import junit.framework.Test;



public class CostelloTest extends ScriptFixture {
     public CostelloTest(String name) { super(name); }
     public static Test suite() {
         return new ScriptTestSuite(CostelloTest.class, "src/gui_testcase") {
             public boolean accept(File file) {
                 String name = file.getName();
                 return name.endsWith("_test.xml");
             }
         };
     }

 }