package com.shumin.movie.ui.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.app.Service;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by shumin on 4/3/16.
 */
public class BaseFragment extends Fragment {

    private ProgressDialog progressDialog;

    protected void showProgressDialog(int resId) {
        if (progressDialog != null) {
            dismissProgressDialog();
            progressDialog = null;
        }
        progressDialog = ProgressDialog.show(getActivity(), "", getString(resId), true);
    }

    protected void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    protected void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    protected void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }

}
