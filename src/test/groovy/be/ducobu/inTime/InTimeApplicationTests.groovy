package be.ducobu.inTime

import junit.framework.Test

class InTimeApplicationTests {

    static Test suite() {
        def allTests = new GroovyTestSuite()

        allTests.addTestSuite(WorkspaceTest.class)

        return allTests
    }

}
