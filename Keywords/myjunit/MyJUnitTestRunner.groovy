package myjunit

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import org.junit.runner.JUnitCore
import org.junit.runner.Result
import org.junit.internal.TextListener

/**
 * https://stackoverflow.com/questions/26411163/how-to-generate-html-report-if-my-junit-is-not-run-by-ant-but-by-junitcore-run
 * 
 * @author kazurayam
 *
 */
public class MyJUnitTestRunner {

	private Path outdir_
	protected List<Class> myTestToRunTab = new ArrayList<Class>()

	MyJUnitTestRunner(Path outdir) {
		outdir_ = outdir
		Files.createDirectories(outdir_)
	}

	public MyJUnitTestRunner addTestToRun(Class clazz) {
		myTestToRunTab.add(clazz)
		return this
	}

	public Result runTest() {
		try {
			JUnitCore jUnitCore = new JUnitCore()
			jUnitCore.addListener(new TextListener(System.out))
			jUnitCore.addListener(new MyRunListener(myTestToRunTab.size()))
			//
			Result result = jUnitCore.run(myTestToRunTab.toArray(new Class[myTestToRunTab.size()]))
			//
			StringBuffer myContent = getResultContent(result, myTestToRunTab.size())
			String filePath = outdir_.toFile()
			String reportFileName = "MyJUnitReport.html"
			writeReportFile(filePath + "/" + reportFileName, myContent)
			return result
		} catch (Exception e) {
			e.printStackTrace(System.out)
		}
	}

	private StringBuffer getResultContent(Result result, int numberOfTestFiles) {
		int numberOfTest = result.getRunCount()
		int numberOfTestFail = result.getFailureCount()
		int numberOfTestIgnore = result.getIgnoreCount()
		int numberOfTestSuccess = numberOfTest - numberOfTestFail - numberOfTestIgnore
		int successPercent = (numberOfTest != 0) ? numberOfTestSuccess * 100 / numberOfTest : 0
		double time = result.getRunTime()
		StringBuffer myContent = new StringBuffer("<h1>Junit Report</h1><h2>Result</h2><table border=\"1\"><tr><th>Test Files</th><th>Tests</th><th>Success</th>");
		if ((numberOfTestFail > 0) || (numberOfTestIgnore > 0)) {
			myContent.append("<th>Failure</th><th>Ignore</th>")
		}
		myContent.append("<th>Test Time (seconds)</th></tr><tr")
		if ((numberOfTestFail > 0) || (numberOfTestIgnore > 0)) {
			myContent.append(" style=\"color:red\" ")
		}
		myContent.append("><td>")
		myContent.append(numberOfTestFiles)
		myContent.append("</td><td>")
		myContent.append(numberOfTest)
		myContent.append("</td><td>")
		myContent.append(successPercent)
		myContent.append("%</td><td>")
		if ((numberOfTestFail > 0) || (numberOfTestIgnore > 0)) {
			myContent.append(numberOfTestFail)
			myContent.append("</td><td>")
			myContent.append(numberOfTestIgnore)
			myContent.append("</td><td>")
		}
		myContent.append(Double.valueOf(time/1000.0D))
		myContent.append("</td></tr></table>")
		return myContent
	}

	private void writeReportFile(String fileName, StringBuffer reportContent) {
		FileWriter myFileWriter = null
		try {
			myFileWriter = new FileWriter(fileName)
			myFileWriter.write(reportContent.toString())
		}
		catch (IOException e) {
			e.printStackTrace(System.out)
		}
		finally {
			if (myFileWriter != null) {
				try {
					myFileWriter.close()
				}
				catch (IOException e) {
					e.printStackTrace(System.out)
				}
			}
		}
	}

	/*
	 public static void main(String[] args) {
	 Path outdir = Paths.get('.').resolve("tmp")
	 MyJUnitTestRunner runner = new MyJUnitTestRunner(outdir)
	 runner.addTestToRun(IntegerEqualityTest.class)
	 runner.runTest()
	 }
	 */
}
