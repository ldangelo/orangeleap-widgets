package com.orangeleap.webtools.domain;

import java.io.Serializable;

import com.orangeleap.tangerine.util.AES;
import com.orangeleap.tangerine.util.AESException;

public class Site implements Serializable {
	private static final long serialVersionUID = -475713107356427586L;
	private String siteName;
    private String orangeLeapUserId;
    private String orangeLeapPassword;

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(final String siteName) {
		this.siteName = siteName;
	}

	public String getOrangeLeapUserId() {
		return orangeLeapUserId;
	}

	public void setOrangeLeapUserId(String orangeLeapUserId) {
		this.orangeLeapUserId = orangeLeapUserId;
	}

	public String getOrangeLeapPassword() {
		try {
			if (orangeLeapPassword != null && orangeLeapPassword.length() > 0) {
				return AES.decrypt(orangeLeapPassword);
			}
		} catch (AESException exception) {
			// passwords may not be encrypted initially after db upgrade scripts move the password to the SITE table
			// Do nothing and return the plain password
		}
		return orangeLeapPassword;
	}

	public String getOrangeLeapPasswordEncrypted() {
		return orangeLeapPassword;
	}

	public void setOrangeLeapPassword(String orangeLeapPassword) {
		if (orangeLeapPassword == null) {
			orangeLeapPassword = "";
		}
		// Check if value is already encrypted.  It will be encrypted if it's coming from the SITE table after previously being encrypted.  It will not be encrypted coming from the UI.
		try {
			AES.decrypt(orangeLeapPassword);
			// Incoming password was already encrypted.  Set the value to it.
			this.orangeLeapPassword = orangeLeapPassword;
		} catch (AESException exception) {
			// Value was not encrypted.  Encrypt it and set
			this.orangeLeapPassword = AES.encrypt(orangeLeapPassword);
		}
	}
}