package ch.ethz.id.sws.eduapp.administration;

import java.util.Locale;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;

import ch.ethz.id.sws.swc.core.services.SWCMessageRegistry;

/** The Administration bundle messages registry.
 *
 * @author Peter Mundt */
@Creatable
public class MessageRegistry extends SWCMessageRegistry<Messages> {

    @Optional
    @Inject
    @Override
    protected void getNotified(@UIEventTopic(SWCMessageRegistry.LOCALE_CHANGE) final Locale locale) {
        super.getNotified(locale);
    }

    @Override
    protected Class<Messages> getType() {
        return Messages.class;
    }

}

