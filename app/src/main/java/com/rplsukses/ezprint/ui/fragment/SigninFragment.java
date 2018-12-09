package com.rplsukses.ezprint.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.db.model.User;
import com.rplsukses.ezprint.bl.network.services.LoginServices;
import com.rplsukses.ezprint.bl.util.PrefUtil;
import com.rplsukses.ezprint.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SigninFragment extends Fragment {

    @BindView(R.id.signin_etEmail)EditText etEmail;
    @BindView(R.id.signin_etPassword)EditText etPassword;
    @BindView(R.id.signin_tvError)TextView tvError;

    private LoginServices loginServices;
    private String email;
    private String password;

    public SigninFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // if user logged in move to main activity
        if (isSessionLogin()){
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin, container, false);
        ButterKnife.bind(this, view);

        loginServices = new LoginServices(getActivity());

        return view;
    }

    @OnClick(R.id.signin_btnSignin) public void signin(View view){
        if (validation()) {
            loginAct();
        }
    }

    //method untuk validasi input user
    private boolean validation(){
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        if (email.isEmpty()){
            etEmail.requestFocus();
            tvError.setText(R.string.error_required);
            return false;
        }else if (password.isEmpty()){
            etPassword.requestFocus();
            tvError.setText(R.string.error_required);
            return false;
        }

        return true;
    }

    void loginAct(){
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        loginServices.doLogin(email, password, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                User user = (User) response.body();

                if (user != null){
                    if (!user.getError()){
                        PrefUtil.putUser(getActivity(), PrefUtil.USER_SESSION, user);
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    Toast.makeText(getActivity(), user.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getActivity(), "Error Login User!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // this method to check is user logged in ?
    boolean isSessionLogin(){
        return PrefUtil.getUser(getActivity(), PrefUtil.USER_SESSION) != null;
    }
}
