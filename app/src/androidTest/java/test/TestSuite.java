package test;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Runs all unit tests.
@RunWith(Suite.class)
@Suite.SuiteClasses({TestParsing1.class, TestParsing2.class, TestParsing3.class, TestParsingAlleAuchBus.class, TestIntegration.class, TestStationSearch.class})
public class TestSuite { }