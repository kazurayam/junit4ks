package com.kazurayam.ksbackyard.junit

import com.kms.katalon.core.keyword.internal.KeywordMain
import com.kms.katalon.core.configuration.RunConfiguration


import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import org.junit.runner.JUnitCore;

/**
 *
 * learned from https://github.com/katalon-studio/katalon-studio-testing-framework/blob/master/Include/scripts/groovy/com/kms/katalon/core/cucumber/keyword/CucumberBuiltinKeywords.groovy
 *
 * @author kazurayam
 *
 */
public class JUnitCustomKeywords {

	@Keyword
	public static JUnitRunnerResult runWithJUnitRunner(Class junitRunnerClass, FailureHandling flowControl) {
		return KeywordMain.runKeyword({
			JUnitCore core = new JUnitCore()
			// TODO
		})	
	}
	
	@Keyword
	public static JUnitRunnerResult runWithJUnitRunner(Class junitRunnerClass) {
		return runWithJUnitRunner(junitRunnerClass,
			RunConfiguration.getDefaultFailureHandling())
	}
}
