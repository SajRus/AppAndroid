package blackbox_suite_testing;

import java.io.File;

import junit.extensions.abbot.ScriptFixture;
import junit.extensions.abbot.ScriptTestSuite;
import junit.framework.Test;


public class CoperturaClassiTest extends ScriptFixture{

	public CoperturaClassiTest(String name) { super(name); }
	public static Test suite() {
		return new ScriptTestSuite(CoperturaClassiTest.class, "src/blackbox_testcase") {
			public boolean accept(File file) {
				String name = file.getName();
				return name.endsWith("_test.xml");
			}
		};
	}
}
