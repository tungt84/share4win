package org.tloss.share4win;

import javax.servlet.http.HttpServletRequest;

import org.tloss.share4win.model.PageInfo;

public class Utils {
	public static void setPageTitle(HttpServletRequest request,String title) {
		PageInfo info = ((PageInfo) request.getAttribute("PAGEINFO"));
		if (info != null) {
			info.setTitle(title);
		}
	}
}
