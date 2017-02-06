package ch.ethz.id.sws.eduapp.clicker;

import static ch.ethz.id.sws.eduapp.clicker.ClickerConstants.PART_ID_CLICKER_LIST;
import static ch.ethz.id.sws.eduapp.security.permission.Permissions.PN_CLICKER_MANAGE;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.id.sws.swc.core.parts.PartConfiguration;
import ch.ethz.id.sws.swc.services.IMenuItem;
import ch.ethz.id.sws.swc.services.IPartConfiguration;
import ch.ethz.id.sws.swc.services.IUseCase;

@org.osgi.service.component.annotations.Component(service = { IUseCase.class })
public class UseCaseClickerList implements IUseCase {

    private static final Logger LOG = LoggerFactory.getLogger(UseCaseClickerList.class);
    
	{
	   String msg = "UseCaseClickerList.init() *************************************************** ";	//$NON-NLS-1$
       LOG.debug(msg);  
	   System.out.println(msg);	
	}

	@Override
	public IMenuItem getMenu() {
        LOG.debug("getMenu() START");  //$NON-NLS-1$
        return null;// createMenu();
    }

	@Override
	public Collection<IPartConfiguration> getPartConfigurations() {
        LOG.debug("getPartConfigurations() START");  //$NON-NLS-1$
		final Collection<IPartConfiguration> out = new ArrayList<>(1);
		//out.add(new PartConfiguration(PART_ID_CLICKER_LIST, 10, PN_CLICKER_MANAGE));
		return out;
	}

	//	---
	
//	private IMenuItem createMenu() {
//		final MenuContainer menuNewClicker = (MenuContainer) new MenuContainer("%Menu_newClicker", 20).setPermission(PN_CLICKER_CREATE);
//		return menuNewClicker.add(new MenuItem("%MenuItem_newClicker", 10).setHandler(NewClickerHandler.class)); 
//	}
	
}
