package ufm.universalfinancemanager.unit_tests;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import ufm.universalfinancemanager.addeditaccount.AddEditAccountPresenter;
import ufm.universalfinancemanager.db.UserRepository;
import ufm.universalfinancemanager.login.LoginContract;
import ufm.universalfinancemanager.login.LoginPresenter;
import ufm.universalfinancemanager.net.LoginCallback;
import ufm.universalfinancemanager.net.LoginManager;
import ufm.universalfinancemanager.support.AccountType;
import ufm.universalfinancemanager.support.atomic.User;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Created by Areeba on 11/28/2018.
 */

public class LoginTest {
    @Mock
    private LoginManager loginManager;

    @Mock
    private LoginContract.View mView;

    String result ="";

    private LoginPresenter mPresenter;

    @Test
    public void testLoggingIn() {
        loginManager = Mockito.mock(LoginManager.class);
        mPresenter = new LoginPresenter(loginManager);
        mView = Mockito.mock(LoginContract.View.class);
        mPresenter.takeView(mView);
        mPresenter.login("testemail1@gmail.com", "password", false);
        verify(mView).showSuccessfulLogin();


        /*loginManager = Mockito.mock(LoginManager.class);

        loginManager.login("testingemail1@gmail.com", "password", new LoginCallback() {

            @Override
            public void onSuccessfulLogin() {
                result = "success";
            }

            @Override
            public void onFailedLogin() {
                result = "failed";
            }

            @Override
            public void onError() {
                result = "error";
            }
        });

        assertEquals(result, "failed");*/
    }

    @Test
    public void testFailLoggingIn() {
        loginManager = Mockito.mock(LoginManager.class);
        mPresenter = new LoginPresenter(loginManager);
        mView = Mockito.mock(LoginContract.View.class);
        mPresenter.takeView(mView);
        mPresenter.login("testemail2@gmail.com", "password", false);
        verify(mView).showLoginError();
    }

    @Test
    public void testSigningIn() {
        loginManager = Mockito.mock(LoginManager.class);
        mPresenter = new LoginPresenter(loginManager);
        mView = Mockito.mock(LoginContract.View.class);
        mPresenter.takeView(mView);
        mPresenter.signup("testemail2@gmail.com", "password", false);
        verify(mView).showSuccessfulSignup();
    }
}
