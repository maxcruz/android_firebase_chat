package com.example.max.androidchat.register;

public class RegisterInteractorImpl implements RegisterInteractor {

    private RegisterRepository registerRepository;

    public RegisterInteractorImpl() {
        this.registerRepository = new RegisterRepositoryImpl();
    }

    @Override
    public void doSignUp(String email, String password) {
        this.registerRepository.signUp(email, password);
    }

}
