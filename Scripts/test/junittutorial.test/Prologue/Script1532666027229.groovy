import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * This Groovy script makes sure Groovy source files in the Keyword directory
 * compiled to classes files by Groovy compiler
 * 
 * @author kazurayam
 */
def classNames = [
	'junittutorial.Calculator',
	'junittutorial.test.AllTests',
	'junittutorial.test.CalculatorTest',
	'junittutorial.test.MoreCalculatorTest'
]

for (String className : classNames) {
	def name = CustomKeywords."${className}.getClass"().getName()
	WebUI.comment("${name} is compiled")
}