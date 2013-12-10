package com.newray.base.web.router;

import com.newray.base.web.command.ListPageCommand;
import com.newray.base.web.list.extend.ListEditor;

/**
 * 在webContext.xml中，参见MVC list Router的配置
 * @author justin.jiang
 *
 */
public class MVCRouter {
	private String modelClass;
	private String jspPath;
	private ListPageCommand listPageCommand;
	private ListEditor listEditor;

	public String getModelClass() {
		return modelClass;
	}

	public void setModelClass(String modelClass) {
		this.modelClass = modelClass;
	}

	public String getJspPath() {
		return jspPath;
	}

	public void setJspPath(String jspPath) {
		this.jspPath = jspPath;
	}

	public ListPageCommand getListPageCommand() {
		return listPageCommand;
	}

	public void setListPageCommand(ListPageCommand listPageCommand) {
		this.listPageCommand = listPageCommand;
	}

	public ListEditor getListEditor() {
		return listEditor;
	}

	public void setListEditor(ListEditor listEditor) {
		this.listEditor = listEditor;
	}
}
