package ch.ethz.id.sws.eduapp.clicker.handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import ch.ethz.id.sws.swc.services.ISWCHandler;

public class NewClickerHandler implements ISWCHandler {

//	@Inject
//	private IEventBroker broker;

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) final Shell shell) {
		MessageDialog.openInformation(shell, "UseCase 1", "Alles ok!");
	}

	@CanExecute
	public boolean canExecute() {
		return true;
	}
}
