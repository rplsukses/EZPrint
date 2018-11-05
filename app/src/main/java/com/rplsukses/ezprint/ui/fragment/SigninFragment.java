package com.rplsukses.ezprint.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.rplsukses.ezprint.R;
import com.rplsukses.ezprint.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class SigninFragment extends Fragment {

    @BindView(R.id.signin_etEmail)EditText etEmail;
    @BindView(R.id.signin_etPassword)EditText etPassword;
    @BindView(R.id.signin_tvError)TextView tvError;

    public SigninFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.signin_btnSignin) public void signin(View view){
        if (validation()) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    private boolean validation(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (email.isEmpty()){
            etEmail.requestFocus();
            tvError.setText(R.string.error_required);
            return false;
        }else if(!email.equals("admin@mail.com") || !password.equals("admin")){
            etEmail.requestFocus();
            tvError.setText(R.string.error_invalid);
            return false;
        }else if (password.isEmpty()){
            etPassword.requestFocus();
            tvError.setText(R.string.error_required);
            return false;
        }

        return true;
    }
}
