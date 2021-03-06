package searls.jasmine;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import searls.jasmine.fileio.DirectoryCopier;

@RunWith(MockitoJUnitRunner.class)
public class ProcessTestResourcesMojoTest {

	@InjectMocks private ProcessResourcesMojo processResourcesMojo = new ProcessResourcesMojo();
	@Mock private DirectoryCopier directoryCopier;
	
	@Before
	public void before() {
		//eat logging
		processResourcesMojo.setLog(mock(Log.class));
	}
	
	@Test
	public void shouldUseDirectoryCopier() throws IOException, MojoExecutionException, MojoFailureException {
		String expectedSuffix = ".js";
		File srcDir = mock(File.class);
		when(srcDir.exists()).thenReturn(true);
		processResourcesMojo.jsSrcDir = srcDir;
		processResourcesMojo.srcDirectoryName = "blah";
		
		processResourcesMojo.execute();
		
		verify(directoryCopier).copyDirectory(eq(srcDir), isA(File.class), eq(expectedSuffix));
	}
	
	
	
}
