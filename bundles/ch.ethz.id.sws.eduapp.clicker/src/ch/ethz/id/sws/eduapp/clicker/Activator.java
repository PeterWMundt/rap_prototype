package ch.ethz.id.sws.eduapp.clicker;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/** Activator class.
*
* @author peter Mundt */
public class Activator implements BundleActivator {

   private static BundleContext context;

   static BundleContext getContext() {
       return context;
   }

   @Override
   public void start(final BundleContext bundleContext) throws Exception {
       Activator.context = bundleContext;
   }

   @Override
   public void stop(final BundleContext bundleContext) throws Exception {
       Activator.context = null;
   }

}
