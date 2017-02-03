package ch.ethz.id.sws.eduapp.clicker.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.swt.widgets.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.id.sws.eduapp.clicker.MessageRegistry;
import ch.ethz.id.sws.eduapp.clicker.Messages;
import ch.ethz.id.sws.eduapp.clicker.part.ClickerListPart;
import ch.ethz.id.sws.eduapp.data.entity.Clicker;
import ch.ethz.id.sws.eduapp.data.service.EduAppDBHelperFactory;

public class DeleteHandler {
	
    private static final Logger LOG = LoggerFactory.getLogger(ClickerListPart.class);
    
    @Execute
    public void execute(final Shell shell, final IEventBroker broker, final ESelectionService selectionService,
            final EduAppDBHelperFactory dbHelperFactory, final MessageRegistry messageRegistry) {
    	DeleteHandler.deleteClicker((Clicker) selectionService.getSelection(), broker, shell, dbHelperFactory,
                messageRegistry.getMessages());
    }


    /** Static method to delete the specified <code>Clicker</code> entry.
    *
    * @param user {@link Clicker}
    * @param broker {@link IEventBroker}
    * @param shell {@link Shell}
    * @param dbHelperFactory {@link EduAppDBHelperFactory}
    * @param messages {@link Messages} */
	public static void deleteClicker(Clicker selection, IEventBroker broker, Shell shell,
			EduAppDBHelperFactory dbHelperFactory, Messages messages) {
		LOG.debug("deleteClicker(Clicker, IEventBroker, Shell, EduAppDBHelperFactory, Messages )");
		
	}

}
