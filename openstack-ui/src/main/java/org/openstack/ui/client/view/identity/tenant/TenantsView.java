package org.openstack.ui.client.view.identity.tenant;

import org.openstack.model.identity.KeyStoneTenant;
import org.openstack.ui.client.view.compute.LogoCell;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class TenantsView extends Composite {

	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, TenantsView> {
	}

	public interface Presenter {

		void refresh();
	}

	@UiField(provided = true)
	DataGrid<KeyStoneTenant> grid = new DataGrid<KeyStoneTenant>();

	private Presenter presenter;

	public TenantsView() {
		initWidget(uiBinder.createAndBindUi(this));
		initGrid();
	}

	private void initGrid() {
		Column<KeyStoneTenant, Boolean> checkboxColumn = new Column<KeyStoneTenant, Boolean>(new CheckboxCell()) {

			@Override
			public Boolean getValue(KeyStoneTenant object) {
				return false;
			}
		};
		grid.setColumnWidth(checkboxColumn, "40px");
		grid.addColumn(checkboxColumn, "");
		TextColumn<KeyStoneTenant> nameColumn = new TextColumn<KeyStoneTenant>() {
			@Override
			public String getValue(KeyStoneTenant object) {
				return object.getName();
			}
		};
		grid.setColumnWidth(nameColumn, "120px");
		grid.addColumn(nameColumn, "Name");
		TextColumn<KeyStoneTenant> descriptionColumn = new TextColumn<KeyStoneTenant>() {
			@Override
			public String getValue(KeyStoneTenant object) {
				return object.getName();
			}
		};
		grid.setColumnWidth(descriptionColumn, "120px");
		grid.addColumn(descriptionColumn, "Description");
		TextColumn<KeyStoneTenant> enabledColumn = new TextColumn<KeyStoneTenant>() {
			@Override
			public String getValue(KeyStoneTenant object) {
				return object.isEnabled() ? "ENABLED" : "DISABLED";
			}
		};
		grid.setColumnWidth(enabledColumn, "120px");
		grid.addColumn(enabledColumn, "STATUS");
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@UiHandler("refresh")
	void onRefresh(ClickEvent event) {
		presenter.refresh();
	}

}
