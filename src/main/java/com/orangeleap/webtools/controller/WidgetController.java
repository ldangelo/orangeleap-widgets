package com.orangeleap.webtools.controller;

import com.orangeleap.webtools.domain.Style;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.service.OrangeLeapClientService;
import com.orangeleap.webtools.service.StyleService;
import com.orangeleap.webtools.service.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Locale;

public class WidgetController extends MultiActionController {
    @Autowired
    WidgetService widgetService;

    @Autowired
    OrangeLeapClientService orangeLeapClientService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    StyleService styleService;

    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String guid = request.getParameter("guid");
        String constituentid = request.getParameter("constituentId");
        Style style = null;
        String appLocation = System.getProperty("webtools.applocation");
        String refererUrl = request.getHeader("Referer");

        final String jsCssSuffix = messageSource.getMessage("jsCssSuffix", null, Locale.getDefault());

        if (refererUrl == null || refererUrl.equals("")) refererUrl = "unknown";

        if (guid != null && !guid.equals("undefined")) {
            Widget w = widgetService.selectWidgetByGuid(guid);

            if (w != null) {

                if (w.getStyleId() != null)
                    style = styleService.selectById(w.getStyleId());


                w.setWidgetHtml(w.getWidgetHtml().replace("@REFERER@", refererUrl));
                w.setWidgetHtml(w.getWidgetHtml().replace("@APPLOCATION@", (appLocation != null) ? appLocation : ""));
                w.setWidgetHtml(w.getWidgetHtml().replace("@GUID@", w.getWidgetGuid()));
                w.setWidgetHtml(w.getWidgetHtml().replace("@SUFFIX@", jsCssSuffix));
                w.setWidgetHtml(w.getWidgetHtml().replace("@SUCCESSURL@", (w.getWidgetLoginSuccessURL() != null ? w.getWidgetLoginSuccessURL() : "")));
                w.setWidgetHtml(w.getWidgetHtml().replace("@AUTHENTICATE@", w.getWidgetAuthenticationRequired().toString()));
                w.setWidgetHtml(w.getWidgetHtml().replace("@LOGINURL@", (w.getWidgetAuthenticationURL() != null) ? w.getWidgetAuthenticationURL() : ""));
                w.setWidgetHtml(w.getWidgetHtml().replace("@PROJECTCODE@", (w.getProjectCode() != null) ? w.getProjectCode() : ""));
                w.setWidgetHtml(w.getWidgetHtml().replace("@MOTIVATIONCODE@", (w.getMotivationCode() != null) ? w.getMotivationCode() : ""));
                w.setWidgetHtml(w.getWidgetHtml().replace("@SPONSORSHIPFORMURL@", (w.getSponsorshipURL() != null) ? w.getSponsorshipURL() : ""));
                w.setWidgetHtml(w.getWidgetHtml().replace("@ARGS@", (request.getHeader("Referer") != null) ? request.getHeader("Referer") : ""));
                w.setWidgetHtml(w.getWidgetHtml().replace("@STYLE@", (style != null) ? style.getStyle() : ""));
                w.setWidgetHtml(w.getWidgetHtml().replace("@DONATIONURL@", (w.getDonationUrl() != null) ? w.getDonationUrl() : ""));
                w.setWidgetHtml(w.getWidgetHtml().replace("@REPLACETOPCONTENT@", w.isReplaceTopContents().toString()));

                response.setIntHeader("Content-Length", w.getWidgetHtml().length());
                response.setHeader("Content-Type", "text/html; charset=UTF-8");
                response.setHeader("p3p", "CP=\"ALL ADM DEV PSAi COM OUR OTRo STP IND ONL\"");

                OutputStream out = response.getOutputStream();
                out.write(w.getWidgetHtml().getBytes());
            }
        }

        return null;
    }

}
