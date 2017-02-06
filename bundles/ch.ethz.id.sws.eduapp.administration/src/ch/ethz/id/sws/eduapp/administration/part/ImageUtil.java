package ch.ethz.id.sws.eduapp.administration.part;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;


public class ImageUtil {

  public static Image getImage( Device device, String path ) {
    ClassLoader classLoader = ImageUtil.class.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream( "resources/" + path );
    Image result = null;
    if( inputStream != null ) {
      try {
        result = new Image( device, inputStream );
      } finally {
        try {
          inputStream.close();
        } catch( IOException e ) {
          // ignore
        }
      }
    }
    return result;
  }

  private ImageUtil() {
	    // prevent instantiation
	  }

}
