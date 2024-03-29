package com.orangeleap.client;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class PWCallbackHandler implements CallbackHandler
{

    // the username and password we expect incoming WS calls to use
    private String user;
    private String pwd;

    public PWCallbackHandler(String user, String pwd) {
	    this.user = user;
	    this.pwd = pwd;
    }

    public void handle (Callback[] callbacks) throws IOException, UnsupportedCallbackException
    {
        for (int i = 0; i < callbacks.length; i++)
        {
            if (callbacks[i] instanceof WSPasswordCallback)
            {
                WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];
                System.out.println("identifier: "+pc.getIdentifer()+", usage: "+pc.getUsage());

                if (pc.getUsage() == WSPasswordCallback.USERNAME_TOKEN)
                {
                    // for passwords sent in digest mode we need to provide the password,
                    // because the original one can't be un-digested from the message

                    // we can throw either of the two Exception types if authentication fails
                    if (! user.equals(pc.getIdentifer()))
                        throw new IOException("unknown user: "+pc.getIdentifer());

                    // this will throw an exception if the passwords don't match
                    pc.setPassword(pwd);

                } else if (pc.getUsage() == WSPasswordCallback.USERNAME_TOKEN_UNKNOWN)
                {
                    // for passwords sent in cleartext mode we can compare passwords directly

                    if (! user.equals(pc.getIdentifer()))
                        throw new IOException("unknown user: "+pc.getIdentifer());

                    // we can throw either of the two Exception types if authentication fails
                    if (! pwd.equals(pc.getPassword()))
                        throw new IOException("password incorrect for user: "+pc.getIdentifer());
                }
            } else
            {
                throw new UnsupportedCallbackException(callbacks[i], "Unrecognized Callback");
            }
        }
    }
}
