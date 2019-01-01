package com.rplsukses.ezprint.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.bl.network.api.Api;
import com.rplsukses.ezprint.bl.network.config.RetrofitBuilder;
import com.rplsukses.ezprint.bl.network.model.User;
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
public class SignupFragment extends Fragment {

    @BindView(R.id.signup_tvError)TextView tvError;
    @BindView(R.id.signup_etPassword)TextView etPassword;
    @BindView(R.id.signup_etNama)TextView etNama;
    @BindView(R.id.signup_etEmail)TextView etEmail;

    private String nama, email, password;
    private Api mApi;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        ButterKnife.bind(this, view);

        mApi = RetrofitBuilder.builder(getActivity()).create(Api.class);
        return view;
    }

    @OnClick(R.id.signup_btnSignup) public void signup(View view){
        if (validation()){
            registerAct();
        }
    }

    private boolean validation(){
        nama = etNama.getText().toString();
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

        if (nama.isEmpty()){
            etNama.requestFocus();
            tvError.setText(R.string.error_required);
            return false;
        }else if (email.isEmpty()){
            etEmail.requestFocus();
            tvError.setText(R.string.error_required);
            return false;
        }else if (password.isEmpty()) {
            etPassword.requestFocus();
            tvError.setText(R.string.error_required);
            return false;
        }else if(password.length() < 8){
            etPassword.requestFocus();
            tvError.setText(R.string.error_password_length);
            return false;
        }

        return true;
    }

    private void registerAct(){
        nama = etNama.getText().toString();
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

        mApi.register(nama, email, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();

                if(user != null) {
                    if(!user.getError()) {
                        PrefUtil.putUser(getActivity(), PrefUtil.USER_SESSION, user);
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }

                    Toast.makeText(getActivity(), user.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
