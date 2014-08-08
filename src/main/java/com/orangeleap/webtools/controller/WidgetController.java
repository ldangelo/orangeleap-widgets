package com.orangeleap.webtools.controller;

import com.orangeleap.webtools.domain.Javascript;
import com.orangeleap.webtools.domain.Style;
import com.orangeleap.webtools.domain.Widget;
import com.orangeleap.webtools.service.JavascriptService;
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

    @Autowired
    JavascriptService javascriptService;
    
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String guid = request.getParameter("guid");
        Style style = null;
        Javascript javascript = null;
        String appLocation = System.getProperty("webtools.applocation");
        String refererUrl = request.getHeader("Referer");

        final String jsCssSuffix = messageSource.getMessage("jsCssSuffix", null, Locale.getDefault());

        if (refererUrl == null || refererUrl.equals("")) refererUrl = "unknown";

        if (guid != null && !guid.equals("undefined")) {
            Widget w = widgetService.selectWidgetByGuid(guid);

            if (w != null) {

                if (w.getStyleId() != null)
                    style = styleService.selectById(w.getStyleId());

                if (w.getJavascriptId() != null) {
                    javascript = javascriptService.selectById(w.getJavascriptId());
                }

                w.setWidgetHtml(w.getWidgetHtml().replace("@REFERER@", refererUrl));
                w.setWidgetHtml(w.getWidgetHtml().replace("@APPLOCATION@", (appLocation != null) ? appLocation : ""));
                w.setWidgetHtml(w.getWidgetHtml().replace("@GUID@", w.getWidgetGuid()));
                w.setWidgetHtml(w.getWidgetHtml().replace("@SUFFIX@", jsCssSuffix));
                w.setWidgetHtml(w.getWidgetHtml().replace("@SUCCESSURL@", (w.getWidgetLoginSuccessURL() != null ? w.getWidgetLoginSuccessURL() : "")));
                w.setWidgetHtml(w.getWidgetHtml().replace("@AUTHENTICATE@", w.getWidgetAuthenticationRequired().toString()));
                w.setWidgetHtml(w.getWidgetHtml().replace("@LOGINURL@", (w.getWidgetAuthenticationURL() != null) ? w.getWidgetAuthenticationURL() : ""));
                // If the designation code was passed in the URL, use it instead of the default widget designation
                String paramDesignationCode = request.getParameter("gift_designation"); 
                if (paramDesignationCode != null && paramDesignationCode.length() > 0) {
                	w.setWidgetHtml(w.getWidgetHtml().replace("@PROJECTCODE@", paramDesignationCode));
                } else {
                	w.setWidgetHtml(w.getWidgetHtml().replace("@PROJECTCODE@", (w.getProjectCode() != null) ? w.getProjectCode() : ""));
                }
                // If the motivation code was passed in the URL, use it instead of the default widget motivation                
                String paramMotivationCode = request.getParameter("gift_motivation"); 
                if (paramMotivationCode != null && paramMotivationCode.length() > 0) {
                	w.setWidgetHtml(w.getWidgetHtml().replace("@MOTIVATIONCODE@", paramMotivationCode));
                } else {
                	w.setWidgetHtml(w.getWidgetHtml().replace("@MOTIVATIONCODE@", (w.getMotivationCode() != null) ? w.getMotivationCode() : ""));
                }

                if (request.getParameter("gift_bank") != null && request.getParameter("gift_bank").length() > 0) {
                    w.setWidgetHtml(w.getWidgetHtml().replace("@BANKCODE", request.getParameter("gift_bank")));
                } else {
                    w.setWidgetHtml(w.getWidgetHtml().replace("@BANKCODE@", (w.getBank() != null) ? w.getBank() :  ""));
                }
                w.setWidgetHtml(w.getWidgetHtml().replace("@SPONSORSHIPFORMURL@", (w.getSponsorshipURL() != null) ? w.getSponsorshipURL() : ""));
                w.setWidgetHtml(w.getWidgetHtml().replace("@ARGS@", (request.getHeader("Referer") != null) ? request.getHeader("Referer") : ""));
                w.setWidgetHtml(w.getWidgetHtml().replace("@STYLE@", (style != null) ? style.getStyle() : ""));
                w.setWidgetHtml(w.getWidgetHtml().replace("@JAVASCRIPT@", (javascript != null) ? javascript.getJavascript() : ""));
                w.setWidgetHtml(w.getWidgetHtml().replace("@DONATIONURL@", (w.getDonationUrl() != null) ? w.getDonationUrl() : ""));
                w.setWidgetHtml(w.getWidgetHtml().replace("@REPLACETOPCONTENT@", w.isReplaceTopContents().toString()));
                if (request.getParameter("pledge_id") != null) {
                	w.setWidgetHtml(w.getWidgetHtml().replace("@PARAM_PLEDGE_ID@", request.getParameter("pledge_id")));
                } else {
                	w.setWidgetHtml(w.getWidgetHtml().replace("@PARAM_PLEDGE_ID@", ""));
                }
                if (request.getParameter("transaction_firstDistributionLineAmount") != null) {
                	w.setWidgetHtml(w.getWidgetHtml().replace("@PARAM_TRANSACTION_FIRSTDISTRIBUTIONLINEAMOUNT@", request.getParameter("transaction_firstDistributionLineAmount")));
                } else {
                	w.setWidgetHtml(w.getWidgetHtml().replace("@PARAM_TRANSACTION_FIRSTDISTRIBUTIONLINEAMOUNT@", ""));	
                }
                if (paramDesignationCode != null) {
                	w.setWidgetHtml(w.getWidgetHtml().replace("@PARAM_GIFT_DESIGNATION@", paramDesignationCode));
                } else {
                	w.setWidgetHtml(w.getWidgetHtml().replace("@PARAM_GIFT_DESIGNATION@", ""));
                }
                if (paramMotivationCode != null) {
                	w.setWidgetHtml(w.getWidgetHtml().replace("@PARAM_GIFT_MOTIVATION@", paramMotivationCode));
                } else {
                	w.setWidgetHtml(w.getWidgetHtml().replace("@PARAM_GIFT_MOTIVATION@", ""));
                }
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
