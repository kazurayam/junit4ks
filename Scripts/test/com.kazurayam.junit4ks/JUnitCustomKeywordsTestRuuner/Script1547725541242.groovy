import static com.kazurayam.junit4ks.JUnitCustomKeywords.runWithJUnitRunner

import com.kazurayam.junit4ks.JUnitCustomKeywordsTest
import com.kms.katalon.core.model.FailureHandling

runWithJUnitRunner(JUnitCustomKeywordsTest.class)  

// We are calling JUnitCustomKeywords to test JUnitCustomKeywords class itself recursively
// Strange?
// Yes, it is strange very much.