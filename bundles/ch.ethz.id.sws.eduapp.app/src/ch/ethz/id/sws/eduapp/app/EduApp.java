package ch.ethz.id.sws.eduapp.app;

import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.osgi.service.component.annotations.Component;

import ch.ethz.id.sws.swc.core.SWCApplication;

@Component(	service = { ApplicationConfiguration.class }, 
			name = "EduApp-Application", 
			property = { "contextName:String=swc" } 
		  )
public class EduApp extends SWCApplication {
	
	private static final String TITLE = "EduApp-Application";
	private static final String PATH = "/eduapp";

	{
		System.out.println( this.getClass().getCanonicalName() + " started!");
	}

	@Override
	public String getPath() {
		return PATH;
	}

	@Override
	public String getTitle() {
		return TITLE;
	}
}