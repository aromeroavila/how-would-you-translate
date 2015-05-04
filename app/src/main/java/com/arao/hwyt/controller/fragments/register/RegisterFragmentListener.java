package com.arao.hwyt.controller.fragments.register;

import com.arao.hwyt.model.User;

/**
 * User: angelromero
 * Date: 13/05/2014
 * Time: 13:07
 */
public interface RegisterFragmentListener {
    public void setEnableNextOrFinishButton(boolean enable);
    public User getUserToBeRegistered();
}
