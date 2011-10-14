package com.orangeleap.webtools.controller;

import com.orangeleap.webtools.domain.Style;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.service.OrangeLeapClientService;
import com.orangeleap.webtools.service.StyleService;
import com.orangeleap.webtools.service.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

public class WidgetController extends MultiActionController {
    @Autowired
    WidgetService widgetService;

    @Autowired
    OrangeLeapClientService orangeLeapClientService;

    @Autowired
    StyleService styleService;

    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String guid = request.getParameter("guid");
        String constituentid = request.getParameter("constituentId");
        Style style = null;
        String appLocation = System.getProperty("webtools.applocation");
        String refererUrl = request.getHeader("Referer");

        if (constituentid == null || constituentid.equals("undefined") || constituentid.equals("")) {
            constituentid = "-1";
        }

        if (refererUrl == null || refererUrl.equals("")) refererUrl = "unknown";

        if (guid != null && !guid.equals("undefined")) {
            Widget w = widgetService.selectWidgetByGuid(guid);

            if (w != null) {
                String customentitytype = w.getCustomEntityName();

                if (w.getStyleId() != null)
                    style = styleService.selectById(w.getStyleId());

                w.setWidgetHtml(w.getWidgetHtml().replaceAll("@REFERER@", refererUrl).replaceAll("@APPLOCATION@", appLocation).replaceAll("@GUID@", w.getWidgetGuid()).replaceAll("@SUCCESSURL@", w.getWidgetLoginSuccessURL()).replaceAll("@AUTHENTICATE@", w.getWidgetAuthenticationRequired().toString()).replaceAll("@LOGINURL@", w.getWidgetAuthenticationURL()).replaceAll("@PROJECTCODE@", w.getProjectCode()).replaceAll("@MOTIVATIONCODE@", w.getMotivationCode()).replaceAll("@SPONSORSHIPFORMURL@", w.getSponsorshipURL()).replaceAll("@ARGS@", request.getHeader("Referer")).replaceAll("@STYLE@", (style != null) ? style.getStyle() : "").replaceAll("@DONATIONURL@",w.getDonationUrl()));


                response.setIntHeader("Content-Length", w.getWidgetHtml().length());
                response.setHeader("Content-Type", "text/html; charset=UTF-8");

                OutputStream out = response.getOutputStream();
                out.write(w.getWidgetHtml().getBytes());
            }
        }

        return null;
    }

};
