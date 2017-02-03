package ch.ethz.id.sws.eduapp.authorization;

import java.util.Arrays;
import java.util.List;

import org.osgi.service.component.annotations.Component;

import ch.ethz.id.sws.swc.core.util.UserAdminHelper;
import ch.ethz.id.sws.swc.services.IAuthorizationGroup;
import ch.ethz.id.sws.swc.services.IAuthorizationGroups;

@Component(service = { IAuthorizationGroups.class })
public class AuthorizationGroups implements IAuthorizationGroups {

	public static final IAuthorizationGroup ADMIN = UserAdminHelper.createAuthorizationGroup("EduappAdmin", 1);
	public static final IAuthorizationGroup MEMBER = UserAdminHelper.createAuthorizationGroup("EduappMember", 2);

	@Override
	public List<IAuthorizationGroup> getGroups() {
		return Arrays.asList(ADMIN, MEMBER);
	}
}
