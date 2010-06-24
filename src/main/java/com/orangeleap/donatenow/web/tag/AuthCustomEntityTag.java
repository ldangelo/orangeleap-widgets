package com.orangeleap.donatenow.web.tag;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class AuthCustomEntityTag extends CustomEntityTag
{
  private static final Log logger = LogFactory.getLog(AuthCustomEntityTag.class);

  private String constituentId;

  public AuthCustomEntityTag() {
    super();
  }

  public int doEndTag() throws javax.servlet.jsp.JspTagException
  {
    if (constituentId != null && !constituentId.equals("")) super.doEndTag();
    else {
      try {
      pageContext.getOut().write("<h4 class='formSectionHeader'>You must be logged in to view this information.</h4>");
      } catch (IOException e) {
        throw new JspTagException("IO Error: " + e.getMessage());
      }
    }
    return EVAL_PAGE;
  }
 
  public void setConstituentId(String val) {
    this.constituentId=val;
  }
}
