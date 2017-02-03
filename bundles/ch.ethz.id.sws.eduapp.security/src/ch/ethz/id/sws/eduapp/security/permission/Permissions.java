package ch.ethz.id.sws.eduapp.security.permission;

import org.osgi.service.component.annotations.Component;

import ch.ethz.id.sws.swc.core.util.PermissionsHelper;
import ch.ethz.id.sws.swc.services.IPermissionEntry;
import ch.ethz.id.sws.swc.services.IPermissions;

@Component
public class Permissions implements IPermissions {

	public static final IPermissionEntry PN_CLICKER_CREATE = PermissionsHelper.createPermission("eduapp_clicker_create",
			"Create a new clicker entry.", Permissions.class);
	public static final IPermissionEntry PN_CLICKER_MANAGE = PermissionsHelper.createPermission("eduapp_clicker_manage",
			"Manage existing clicker entries.", Permissions.class);

	@Override
	public IPermissionEntry[] getPermissions() {
		return new IPermissionEntry[] { PN_CLICKER_CREATE, PN_CLICKER_MANAGE };
	}
}
