package com.example.max.androidchat.login;

public class LoginInteractorImpl implements LoginInteractor {

    private LoginRepository loginRepository;

    public LoginInteractorImpl() {
        this.loginRepository = new LoginRepositoryImpl();
    }

    @Override
    public void checkSession() {
        this.loginRepository.checkSession();
    }

    @Override
    public void doSignIn(String email, String password) {
        this.loginRepository.signIn(email, password);
    }

}
