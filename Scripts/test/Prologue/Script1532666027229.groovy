import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors

/**
 * This Groovy script makes sure Groovy source files in the Keyword directory
 * compiled to classes files by Groovy compiler
 * 
 * @author kazurayam
 */
Path keywordsDir = Paths.get(System.getProperty('user.dir')).resolve('Keywords')
List<String> classNames = 
	Files.walk(keywordsDir)
		.filter { p -> Files.isRegularFile(p) }
		.filter { p -> p.toString().endsWith('.groovy') }
		.map { p -> keywordsDir.relativize(p).toString() }
		.map { s -> s.substring(0, s.lastIndexOf('.')) }
		.map { s -> s.replace('\\', '.').replace('/', '.') }
		.collect(Collectors.toList())

//println "classNames is ${classNames}"

for (String className : classNames) {
	def name = CustomKeywords."${className}.getClass"().getName()
	WebUI.comment("${name} is compiled")
}