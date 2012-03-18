package org.openstack.ui.client.view.identity.tenant;

import org.openstack.model.identity.KeyStoneUser;
import org.openstack.model.identity.KeyStoneUserList;
import org.openstack.ui.client.OpenStackPlace;
import org.openstack.ui.client.api.DefaultAsyncCallback;
import org.openstack.ui.client.api.OpenStackClient;
import org.openstack.ui.client.api.RefreshableDataProvider;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.MultiSelectionModel;

public class UsersActivity extends AbstractActivity implements UsersView.Presenter {
	
	private static final UsersView VIEW = new UsersView();
	
	private OpenStackPlace place;
	
	private RefreshableDataProvider<KeyStoneUser> dataProvider;

	private MultiSelectionModel<KeyStoneUser> selectionModel = new MultiSelectionModel<KeyStoneUser>();

	private DefaultSelectionEventManager<KeyStoneUser> selectionManager = DefaultSelectionEventManager
			.<KeyStoneUser> createCheckboxManager(0);

	public UsersActivity(OpenStackPlace place) {
		this.place = place;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		VIEW.setPresenter(this);
		panel.setWidget(VIEW);
		VIEW.grid.setSelectionModel(selectionModel, selectionManager);
		dataProvider = new RefreshableDataProvider<KeyStoneUser>(VIEW.grid) {

			@Override
			protected void onRangeChanged(HasData<KeyStoneUser> display) {
				OpenStackClient.IDENTITY.listUsers(new DefaultAsyncCallback<KeyStoneUserList>() {

					@Override
					public void onSuccess(KeyStoneUserList result) {
						updateRowCount(result.getList().size(), true);
						updateRowData(0, result.getList());

					}
				});
			}

		};
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
